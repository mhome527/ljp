package vn.jp.language.ljp.view.practice.dashboard;

import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
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

    PracticePresenter presenter;

    public int level = 5;
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

        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N5));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N4));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N3));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N1));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        presenter = new PracticePresenter(activity, activity.level, -1);


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
                level = 5 - tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        final int level = pref.getIntValue(0, Constant.PREF_LEVEL);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(level);
            }
        }, 100);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.downloadVersion();
    }
}
