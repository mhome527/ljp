package vn.jp.language.ljp.view.alphabet;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseActivity;

/**
 * Created by HuynhTD on 10/14/2016.
 */

public class AlphabetActivity extends BaseActivity<AlphabetActivity> {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.alphabet_layout;
    }

    @Override
    protected void initView() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_hiragana));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_katakana));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final AlphabetPagerAdapter adapter = new AlphabetPagerAdapter
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
}
