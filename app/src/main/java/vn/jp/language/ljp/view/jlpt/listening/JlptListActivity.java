package vn.jp.language.ljp.view.jlpt.listening;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.JlptEntity;
import vn.jp.language.ljp.entity.JlptMstEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Toaster;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IJlptClickListener;
import vn.jp.language.ljp.view.jlpt.listening_detail.JlptListeningActivity;
import vn.jp.language.ljp.view.purchase.IPurchase;
import vn.jp.language.ljp.view.purchase.PurchaseNewActivity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class JlptListActivity extends PurchaseNewActivity<JlptListActivity> implements IJlptClickListener, IPurchase {
    private final String TAG = "JlptListActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };

    List<JlptMstEntity> items;
    JlptListAdapter adapter;
    JlptListPresenter presenter;
    int level;
    int kind;
    boolean isClicked = false;
    int position;
    int mondai;


    // declare the dialog as a member field of your activity
    ProgressDialog mProgressDialog;

    @Override
    protected int getLayout() {
        return R.layout.jlpt_listening_list_layout;
    }

    @Override
    protected void initView() {
        Common.setupRecyclerView(activity, recyclerView, null);
        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, PracticeTable.LEVEL_N5);
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, 1);
        presenter = new JlptListPresenter(activity, level, kind);
        setTitle("聴解ーN" + level);

        Common.verifyStoragePermissions(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isClicked = false;
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
    }

    //   ==============  IJlptClickListener - item click
    @Override
    public void onClick(int position, int mondai) {
        Log.i(TAG, "item click:" + position + "; mondai:" + mondai);
        this.position = position;
        this.mondai = mondai;
        if (!isClicked) {
            isClicked = true;
        } else {
            return;
        }

        if (!isPurchased && mondai != 1) {
            //check if service is already connected
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
                        isClicked = false;
                    }

                    @Override
                    public void onBillingServiceDisconnected() {
                        isClicked = false;
                    }
                });
            }
        } else {
            presenter.loadMondai(items.get(position).test_date, mondai, new ICallback() {
                @Override
                public void onCallback(Object data) {
                    JlptEntity item = (JlptEntity) data;
                    if (!Common.isPermission(activity)) {
                        new Toaster(activity).popToast("Permission denied!!!");
                        Common.verifyStoragePermissions(activity);
                    } else {
                        if (item.filename == null || item.filename.equals("")) {
                            Log.i(TAG, "File name is null");
                            return;
                        }
                        String path_file = Environment.getExternalStorageDirectory().toString() + Constant.FOLDER_JLPT + "/" + item.filename;
                        if (Common.isExistFile(path_file)) {
                            startJlptListening(item);
                        } else {
                            Log.i(TAG, "file not exist, ->download");
                            // instantiate it within the onCreate method
                            mProgressDialog = new ProgressDialog(activity);
//                mProgressDialog.setMessage("A message");
                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            mProgressDialog.setCancelable(false);
                            presenter.downloadFile(item);
                            isClicked = false;
                        }
                    }
                }

                @Override
                public void onFail(String err) {
                    isClicked = false;
                }
            });
        }

    }
//   ============= END IJlptClickListener

    public void startJlptListening(JlptEntity item) {
        Intent i = new Intent(activity, JlptListeningActivity.class);
        i.putExtra(Constant.INTENT_LEVEL, item.level);
        i.putExtra(Constant.INTENT_TEST_DATE, item.test_date);
        i.putExtra(Constant.INTENT_MONDAI, item.mondai);
        i.putExtra(Constant.INTENT_TITLE_Q, item.title);
        i.putExtra(Constant.INTENT_FILENAME, item.filename);
        startActivity(i);
    }

    private void loadData() {
        presenter.loadData(new ICallback() {
            @Override
            public void onCallback(Object data) {
                items = (List<JlptMstEntity>) data;
                adapter = new JlptListAdapter(items, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> purchases) {
        Log.i(TAG, "onPurchasesUpdated....");
        //if item newly purchased
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && purchases != null) {
            Log.i(TAG, "onPurchasesUpdated....Da mua");

            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
            isPurchased = false;
            Log.i(TAG, "onPurchasesUpdated....USER_CANCELED");
        } else {
            // Handle any other error codes.
//            isPurchased = false;
            Log.i(TAG, "onPurchasesUpdated....Error");

        }
    }


    //interface IPurchase
    @Override
    public void onCheckPurchase(boolean isPurchased) {
        if (isPurchased) {
            Log.i(TAG, "onCheckPurchase isPurchased:" + isPurchased);
            if (adapter != null) {
                adapter.setPurchased(isPurchased);
            }
        } else {
            Log.i(TAG, "onCheckPurchase chua mua, isPurchased:" + isPurchased);
        }

    }
}
