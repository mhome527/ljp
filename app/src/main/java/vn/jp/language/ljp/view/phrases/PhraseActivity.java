package vn.jp.language.ljp.view.phrases;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PhraseEntity;
import vn.jp.language.ljp.sound.AudioManager;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class PhraseActivity extends BaseActivity<PhraseActivity> implements SearchView.OnQueryTextListener {

    private final String TAG = "PhraseActivity";
    private final String FOLDER = "phrase/";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<PhraseEntity> listData;

    PhraseAdapter adapter;
    PhrasePresenter presenter;
    AudioManager audio;

    @Override
    protected int getLayout() {
        return R.layout.phrase_layout;
    }

    @Override
    protected void initView() {
        presenter = new PhrasePresenter(this);
        adapter = new PhraseAdapter();
        audio = new AudioManager(this);

        setTitle(getString(R.string.title_Phrase));
        initControl();
        loadData();
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

        presenter.searchData(text, new ICallback<List<PhraseEntity>>() {
            @Override
            public void onCallback(List<PhraseEntity> data) {
                listData = data;
                adapter.setData(listData, text);
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

    private void initControl() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // Adding RecyclerView Divider / Separator
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i(TAG, "onClick row pos:" + listData.get(position).sound);
                audio.play(FOLDER + listData.get(position).sound);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.i(TAG, "onLongClick row pos:" + listData.get(position).romaji);
            }
        }));
    }

    private void loadData() {
        presenter.loadData(new ICallback<List<PhraseEntity>>() {
            @Override
            public void onCallback(List list) {
                listData = list;
                adapter.setData(listData, "");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail!!!!!!" + err);
            }
        });
    }
}
