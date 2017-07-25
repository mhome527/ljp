package vn.jp.language.ljp.view.practice.list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.practice.dialog.PracticeDialog;
import vn.jp.language.ljp.view.practice.listening.PracticeListeningActivity;
import vn.jp.language.ljp.view.practice.reading.PracticeReadingActivity;
import vn.jp.language.ljp.view.purchase.PurchaseActivity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticeListActivity extends PurchaseActivity<PracticeListActivity> implements IClickListener {
    private final String TAG = "PracticeDetailActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<PracticeEntity> items;
    PracticeListAdapter adapter;
    PracticeListPresenter presenter;
    int level;
    int kind;
    boolean isSort = true;
    int v1;
    int v2;

    @Override
    protected int getLayout() {
        return R.layout.practice_list_layout;
    }

    @Override
    protected void initView() {
        Common.setupRecyclerView(activity, recyclerView, this);
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, PracticeTable.TYPE_GRAMMAR);
        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, PracticeTable.LEVEL_N5);

        v1 = getIntent().getIntExtra(Constant.INTENT_V1, 0);
        v2 = getIntent().getIntExtra(Constant.INTENT_V2, 0);


        presenter = new PracticeListPresenter(this, level, kind);
        setTitle(presenter.getTitle(v1, v2));

//        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_practice_list, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.menuBookmark:
                Intent i = new Intent(activity, PracticeBookmarkActivity.class);
                i.putExtra(Constant.INTENT_KIND, kind);
                i.putExtra(Constant.INTENT_LEVEL, level);
                i.putExtra(Constant.INTENT_V1, v1);
                i.putExtra(Constant.INTENT_V2, v2);
                startActivity(i);
                return true;

            case R.id.menuSort:
                isSort = !isSort;
                loadData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {

    }

    @Override
    protected void dealWithIabSetupFailure() {

    }
    //    ========================== END PURCHASE ==============

    //   ==============  IClickListener - item click
    @Override
    public void onClick(View view, int position) {
        if (kind == PracticeTable.TYPE_READING) {
            Intent i = new Intent(activity, PracticeReadingActivity.class);
            i.putExtra(Constant.INTENT_LEVEL, level);
            i.putExtra(Constant.INTENT_NUM, items.get(position).getNum());
            i.putExtra(Constant.INTENT_BOOKMARK, items.get(position).getBookmarks());
            i.putExtra(Constant.INTENT_DETAIL_NUM, items.get(position).getNumId());
            i.putExtra(Constant.INTENT_TITLE_Q, items.get(position).getQuestion());
            startActivity(i);
        } else if (kind == PracticeTable.TYPE_LISTENING) {
            Intent i = new Intent(activity, PracticeListeningActivity.class);
            i.putExtra(Constant.INTENT_LEVEL, level);
            i.putExtra(Constant.INTENT_NUM, items.get(position).getNumId());
            Log.i(TAG, "onClick numId:" + items.get(position).getNumId());
//            i.putExtra(Constant.INTENT_BOOKMARK, items.get(position).getBookmarks());
//            i.putExtra(Constant.INTENT_DETAIL_NUM, items.get(position).getNumId());
//            i.putExtra(Constant.INTENT_FILE_NAME, items.get(position).getQ1()); //file name
//            i.putExtra(Constant.INTENT_TITLE_Q, items.get(position).getQuestion());
            startActivity(i);
        } else {
            PracticeDialog dialog = new PracticeDialog(activity, position, items, iPracticeInterface);
            dialog.show();
        }
    }

    @Override
    public void onLongClick(View view, int position) {

    }
//   ============= END IClickListener

    //    IPracticeInterface -- dialog clicked
    IPracticeInterface iPracticeInterface = new IPracticeInterface() {

        @Override
        public void onBookmark(int pos, int value) {
            PracticeEntity item = items.get(pos);
            item.setBookmarks(value);
            presenter.updateBookmark(item.getNum(), value);
        }

        @Override
        public void onAns(int pos, int value) {
            PracticeEntity item = items.get(pos);
            presenter.updateAnswer(item.getNum(), value);

        }
    };


    private void loadData() {
        presenter.getItems(isSort, new ICallback<List<PracticeEntity>>() {
            @Override
            public void onCallback(List<PracticeEntity> data) {
                items = data;
                adapter = new PracticeListAdapter(data);
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
        });
    }

}
