package vn.jp.language.ljp.view.purchase;

import static com.android.billingclient.api.BillingClient.SkuType.INAPP;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;

/**
 * This class should be the parent of any Activity that wants to do in app purchases
 * it makes our life easier by wrapping up the talking to the IabHelper and just exposing what is needed.
 * <p>
 * When this activity starts it will bind to the Google Play IAB service and check for its availability
 * <p>
 * After that you can purchase items using purchaseItem(String sku) and listening for the result
 * by overriding dealWithPurchaseFailed(IabResult result) and dealWithPurchaseSuccess(IabResult result, Purchase info)
 *
 * @author Blundell
 */
public abstract class PurchaseNewActivity<T extends Activity> extends BaseActivity<T> implements PurchasesUpdatedListener {
    private String TAG = "PurchaseNewActivity";

    public static final String PREF_FILE = "MyPref";
    public static final String PURCHASE_KEY = Constant.BASE_64_KEY;
    public static final String PRODUCT_ID = "study.vn.jp";

    public boolean isPurchased = false; //  true: user has already bought product
    public BillingClient billingClient;
    IPurchase iPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        iPurchase = (IPurchase) activity;
        // Establish connection to billing client
        //check purchase status from google play store cache
        //to check if item already Purchased previously or refunded
        billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases().setListener(this).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                Log.i(TAG, "onBillingSetupFinished....");

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Purchase.PurchasesResult queryPurchase = billingClient.queryPurchases(INAPP);
                    List<Purchase> queryPurchases = queryPurchase.getPurchasesList();
                    if (queryPurchases != null && queryPurchases.size() > 0) {
                        isPurchased = true;
                        Log.i(TAG, "onBillingSetupFinished.... da mua");
                        iPurchase.onCheckPurchase(true);

                    }
                    //if purchase list is empty that means item is not purchased
                    //Or purchase is refunded or canceled
                    else {
//                        savePurchaseValueToPref(false);
                        isPurchased = false;
                        Log.i(TAG, "onBillingSetupFinished.... chua mua");
//                        if (BuildConfig.DEBUG)
//                            iPurchase.onCheckPurchase(true);
//                        else
                            iPurchase.onCheckPurchase(false);
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                Log.i(TAG, "onBillingServiceDisconnected....");
            }
        });
    }

    public void setCheckPurchase(IPurchase iPurchase) {
        this.iPurchase = iPurchase;
    }

    public void handlePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, ackPurchase);
            }
        }
    }


    AcknowledgePurchaseResponseListener ackPurchase = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
            Log.i(TAG, "onAcknowledgePurchaseResponse....");

            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                Log.i(TAG, "onAcknowledgePurchaseResponse....BillingResponseCode.OK");
                isPurchased = true;
                //if purchase is acknowledged
                // Grant entitlement to the user. and restart activity
//                savePurchaseValueToPref(true);
//                Toast.makeText(getApplicationContext(), "Item Purchased", Toast.LENGTH_SHORT).show();
//                MainActivity.this.recreate();
            }
        }
    };


    public void initiatePurchase() {
        List<String> skuList = new ArrayList<>();
        skuList.add(PRODUCT_ID);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<com.android.billingclient.api.SkuDetails> skuDetailsList) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Log.i(TAG, "onPurchasesUpdated....");
                            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(skuDetailsList.get(0))
                                    .build();
                            billingClient.launchBillingFlow(activity, flowParams);

                        } else {
                            iPurchase.onCheckPurchase(false);
                            Toast.makeText(getApplicationContext(),
                                    " Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    public void setBillingClient() {
        if (billingClient.isReady()) {
            initiatePurchase();
        }
        //else reconnect service
        else {
            billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        initiatePurchase();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onBillingServiceDisconnected() {
                    Log.i(TAG, "setBillingClient....onBillingServiceDisconnected");
                }
            });
        }
    }


}
