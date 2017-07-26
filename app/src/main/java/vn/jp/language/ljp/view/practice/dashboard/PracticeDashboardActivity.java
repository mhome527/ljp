package vn.jp.language.ljp.view.practice.dashboard;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Prefs;
import vn.jp.language.ljp.view.BaseActivity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticeDashboardActivity extends BaseActivity<PracticeDashboardActivity> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.practice_dashboard_layout;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
            actionBar.setDisplayShowTitleEnabled(true); // remove title
//            toolbarTitle.setText(getString(R.string.title_alphabet));
            actionBar.setTitle(getString(R.string.title_jlpt));
        }
        final Prefs pref = new Prefs(this.getApplicationContext());

        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N5));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N4));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N3));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N1));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PracticeDashboardAdapter adapter = new PracticeDashboardAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pref.putIntValue(tab.getPosition(), Constant.PREF_LEVEL);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        int level = pref.getIntValue(0, Constant.PREF_LEVEL);
        viewPager.setCurrentItem(level);
    }

}
