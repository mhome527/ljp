package vn.jp.language.ljp.view.words;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.purchase.IPurchase;
import vn.jp.language.ljp.view.purchase.PurchaseNewActivity;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class WordActivity extends PurchaseNewActivity<WordActivity> implements IPurchase {

    private static String TAG = "WordActivity";

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//
//    @BindView(R.id.toolbarTitle)
//    TextView toolbarTitle;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    WordPagerAdapter adapter;
    public boolean isPurchased = false; //  true: user has already bought product
    int currPage = 0;

    @Override
    protected int getLayout() {
        return R.layout.word_layout;
    }

    @Override
    protected void initView() {

        setTitleCenter(getString(R.string.title_word));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_animal));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_fruit));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_other));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new WordPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currPage = tab.getPosition();
                viewPager.setCurrentItem(currPage);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        this.isPurchased = isPurchased;
        if (isPurchased) {
            Log.i(TAG, "onCheckPurchase isPurchased:" + isPurchased);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (currPage == 0) {
                        if (((WordFragment) adapter.getItem(0)).adapter != null)
                            ((WordFragment) adapter.getItem(0)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(1)).adapter != null)
                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
                    } else if (currPage == 1) {
                        if (((WordFragment) adapter.getItem(0)).adapter != null)
                            ((WordFragment) adapter.getItem(0)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(1)).adapter != null)
                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(2)).adapter != null)
                            ((WordFragment) adapter.getItem(2)).adapter.setPurchased(isPurchased);
                    } else {
                        if (((WordFragment) adapter.getItem(2)).adapter != null)
                            ((WordFragment) adapter.getItem(2)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(1)).adapter != null)
                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
                    }
                }
            });
        } else {
            Log.i(TAG, "onCheckPurchase chua mua, isPurchased:" + isPurchased);
        }
    }
}
