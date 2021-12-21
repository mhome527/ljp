package vn.jp.language.ljp.view.ono;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.OnoEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by Administrator on 9/22/2017.
 */

public class OnoActivity extends BaseActivity<OnoActivity> implements IClickListener, SearchView.OnQueryTextListener {

    private final String TAG = "OnoActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //    List<OnoEntity> items;
    OnoAdapter adapter;
    OnoPresenter presenter;

    SearchView searchView;
    String textSearch = "";
    List<OnoEntity> items;
    ICallback iCallback;

    @Override
    protected int getLayout() {
        return R.layout.practice_list_layout;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.title_ono));
        Common.setupRecyclerView(activity, recyclerView, this);
        presenter = new OnoPresenter(this);
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view, int position) {
        OnoEntity item = items.get(position);
        OnoDialog dialog = new OnoDialog(activity, item, new OnoInterface() {
            @Override
            public void onBookmark(OnoEntity item) {
                presenter.updateBookmark(item.getNum(), item.getBookmarks());
            }
        });
        dialog.show();
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ono, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.onActionViewExpanded();
        if (android.os.Build.VERSION.SDK_INT >= 17)
            searchView.setPaddingRelative(30, 0, 0, 0);
        else
            searchView.setPadding(15, 0, 0, 0);

        searchView.setQueryHint(getString(R.string.menu_search_hint)); // fill in the search term by default

        searchView.clearFocus();
        searchView.onActionViewCollapsed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!textSearch.equals("")) {
                    searchView.clearFocus();
                    searchView.onActionViewCollapsed();
                    textSearch = "";

                } else {

                    onBackPressed();
                }
                return true;

            case R.id.menuBookmark:
                Intent i = new Intent(activity, OnoBookmarkActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadData() {
        iCallback = new ICallback<List<OnoEntity>>() {
            @Override
            public void onCallback(List<OnoEntity> data) {
                items = data;
                adapter = new OnoAdapter(items);

                recyclerView.setAdapter(adapter);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail err:" + err);
            }
        };

        presenter.getItems(iCallback);
    }

    //// OnQueryTextListener
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
    //=============================

    public void filter(String text) {
//        if (text.trim().equals("")) {
//            adapter.clearData();
//            adapter.notifyDataSetChanged();
//            return;
//        }
        textSearch = text;
        presenter.searchData(text, new ICallback<List<OnoEntity>>() {
            @Override
            public void onCallback(List<OnoEntity> data) {
                items = data;
                adapter.setData(items, textSearch);
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
