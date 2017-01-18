package vn.jp.language.ljp.view.kanji;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.KanjiEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.kanji.detail.KanjiDetailActivity;

/**
 * Created by HuynhTD on 1/17/2017.
 */

public class KanjiActivity extends BaseActivity<KanjiActivity> implements IClickListener, SearchView.OnQueryTextListener {

    private final String TAG = "KanjiActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    KanjiAdapter adapter;
    KanjiPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.kanji_layout;
    }

    @Override
    protected void initView() {
        adapter = new KanjiAdapter(activity);
        presenter = new KanjiPresenter(activity);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
            actionBar.setDisplayShowTitleEnabled(false); // remove title
            toolbarTitle.setText(getString(R.string.title_kanji));

        } else
            Log.e(TAG, "initView actionBar NULL!!!!");
        setupView();
        presenter.getListData(new ICallback<List<KanjiEntity>>() {
            @Override
            public void onCallback(List<KanjiEntity> data) {
                adapter.setData(data, "");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "Error: " + err);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_kanji, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
//        searchView.onActionViewExpanded();
        if (android.os.Build.VERSION.SDK_INT >= 17)
            searchView.setPaddingRelative(30, 0, 0, 0);
        else
            searchView.setPadding(15, 0, 0, 0);

        searchView.setQueryHint(getString(R.string.menu_search_hint)); // fill in the search term by default
        searchView.clearFocus(); // close the keyboard on load
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.tvBack)
    public void actionBack() {
        finish();
    }

    public void setupView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration2(activity, R.drawable.line_divider));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(activity, KanjiDetailActivity.class);
        i.putExtra(Constant.INTENT_DETAIL_NUM, adapter.getItem(position).getNum());
        activity.startActivity(i);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    // ============ OnQueryTextListener =============
    @Override
    public boolean onQueryTextSubmit(String query) {
        filter(query.trim());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText.trim());
        return false;
    }
    /// ========= end OnQueryTextListener ===============

    public void filter(final String text) {
//        if (text.trim().equals("")) {
//            adapter.clearData();
//            adapter.notifyDataSetChanged();
//            return;
//        }
        presenter.searchData(text, new ICallback() {
            @Override
            public void onCallback(Object data) {
                adapter.setData((List<KanjiEntity>) data, text);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "fail err:" + err);
            }
        });
    }
}
