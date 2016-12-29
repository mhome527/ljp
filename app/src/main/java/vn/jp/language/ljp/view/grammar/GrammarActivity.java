package vn.jp.language.ljp.view.grammar;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.BuildConfig;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.test.AndroidDatabaseManager;

/**
 * Created by HuynhTran on 12/23/2016.
 */

public class GrammarActivity extends BaseActivity<GrammarActivity> {

    private final String TAG = "GrammarActivity";

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @BindView(R.id.tvBack)
    TextView tvBack;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    GrammarPresenter presenter;
    ViewPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.grammar_layout;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        toolbarTitle.setText(getString(R.string.title_button_grammar));

        presenter = new GrammarPresenter(activity);
        setupViewPager();

        ActionBar actionBar = getSupportActionBar();
//        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
            actionBar.setDisplayShowTitleEnabled(false); // remove title

        } else
            Log.e(TAG, "initView actionBar NULL!!!!");

        if (BuildConfig.DEBUG) {
            toolbarTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "click!!");
                    Intent i = new Intent(activity, AndroidDatabaseManager.class);
                    startActivity(i);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grammar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
                return true;

            case android.R.id.home:
                activity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.tvBack)
    public void actionBack(){
        finish();
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N5));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N4));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N3));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_N1));

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                resetScroll();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        setTabIcon();

    }

    private void resetScroll() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedFling(coordinator, appBar, null, 0, -1000, true);
    }


}

