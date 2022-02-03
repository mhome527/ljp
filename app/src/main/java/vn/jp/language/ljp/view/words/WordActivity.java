package vn.jp.language.ljp.view.words;


import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.purchase.PurchaseActivity;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class WordActivity extends PurchaseActivity<WordActivity> {

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

    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;

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

            /// Test only
//            clearPurchaseTest();

        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {
        Log.i(TAG, "dealWithIabSetupFailure...");
    }
    // ================ Purchase ===========

}
