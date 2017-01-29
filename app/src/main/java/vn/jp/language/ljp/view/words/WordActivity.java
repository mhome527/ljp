package vn.jp.language.ljp.view.words;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseActivity;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class WordActivity extends BaseActivity<WordActivity> {

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


    @Override
    protected int getLayout() {
        return R.layout.word_layout;
    }

    @Override
    protected void initView() {
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(true); // disable the button
//            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
//            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
//            actionBar.setDisplayShowTitleEnabled(true); // remove title
////            toolbarTitle.setText(getString(R.string.title_word));
//        }
        setTitleCenter(getString(R.string.title_word));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_animal));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_fruit));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_other));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final WordPagerAdapter adapter = new WordPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    @OnClick(R.id.tvBack)
//    public void actionBack() {
//        finish();
//    }

}
