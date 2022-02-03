package vn.jp.language.ljp.view.practice.list;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

public class PracticeBookmarkActivity extends PurchaseActivity<PracticeBookmarkActivity> implements IClickListener {
    private final String TAG = "PracticeBookmarkActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<PracticeEntity> items;
    PracticeListAdapter adapter;
    PracticeListPresenter presenter;
    int level;
    int kind;
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activity.setResult(AppCompatActivity.RESULT_OK);
                activity.finish();
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

    //  IPracticeInterface -- dialog clicked
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
            item.setReview(value);
            int correct = presenter.countCorrect();
            setTitleQ(correct);
            adapter.notifyItemChanged(pos);
        }
    };


    private void loadData() {
        presenter.getBookmark(new ICallback<List<PracticeEntity>>() {
            @Override
            public void onCallback(List<PracticeEntity> data) {
                if (data == null || data.size() == 0) {
                    Log.i(TAG, "data bookmark not found");
                    return;
                }
                items = data;
                adapter = new PracticeListAdapter(data);
                recyclerView.setAdapter(adapter);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                int correct = presenter.countCorrect();
                setTitleQ(correct);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail err:" + err);
            }
        });
    }

    private void setTitleQ(int value) {
        setTitle(presenter.getTitle(value, v2));
    }

//    private void setTitleQ(int v1, int v2) {
//        setTitle(presenter.getTitle(v1, v2));
//    }

}
