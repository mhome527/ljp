package vn.jp.language.ljp.view.grammar;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.grammar.search.GrammarSearchActivity;

/**
 * Created by HuynhTran on 12/23/2016.
 */

public class GrammarActivity extends BaseActivity<GrammarActivity> {

    private final String TAG = "GrammarActivity";

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    GrammarPresenter presenter;
    ViewPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.grammar_layout;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.title_button_grammar));

        presenter = new GrammarPresenter(activity);

        setupViewPager();

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
                Intent iSearch = new Intent(activity, GrammarSearchActivity.class);
                startActivity(iSearch);
//                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
                return true;

            case android.R.id.home:
                activity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @OnClick(R.id.tvBack)
//    public void actionBack() {
//        finish();
//    }

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
//                actionBar.setTitle(tab.getText());
                resetScroll();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void resetScroll() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedFling(coordinator, appBar, null, 0, -1000, true);
    }


}

