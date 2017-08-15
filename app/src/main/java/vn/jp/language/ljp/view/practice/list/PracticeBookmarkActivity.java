package vn.jp.language.ljp.view.practice.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.BuildConfig;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.practice.dialog.PracticeDialog;
import vn.jp.language.ljp.view.practice.kanji.PracticeKanJiActivity;
import vn.jp.language.ljp.view.practice.listening.PracticeListeningActivity;
import vn.jp.language.ljp.view.practice.reading.PracticeReadingActivity;
import vn.jp.language.ljp.view.purchase.PurchaseActivity;

import static vn.jp.language.ljp.BaseApplication.mFirebaseAnalytics;

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

        if (level == PracticeTable.LEVEL_N5) // N5 is free
            isPurchased = true;

        presenter = new PracticeListPresenter(this, level, kind);
        setTitleQ(v1, v2);

        if (!BuildConfig.DEBUG) {
            Bundle params = new Bundle();

            if (kind == PracticeTable.TYPE_GRAMMAR)
                params.putString("PracticeBookmark", "GRAMMAR level: " + level);
            else if (kind == PracticeTable.TYPE_READING)
                params.putString("PracticeBookmark", "READING level: " + level);
            else if (kind == PracticeTable.TYPE_LISTENING)
                params.putString("PracticeBookmark", "LISTENING level: " + level);
            else if (kind == PracticeTable.TYPE_KANJI)
                params.putString("PracticeBookmark", "KANJI level: " + level);
            else
                params.putString("PracticeBookmark", "VOCABULARY level: " + level);

            mFirebaseAnalytics.logEvent("SCREEN", params);
        }
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
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (adapter == null)
                        return;

                    adapter.setPurchased(isPurchased);
                    adapter.notifyDataSetChanged();
                }
            }, 500);

        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {

    }
    //    ========================== END PURCHASE ==============

    //   ==============  IClickListener - item click
    @Override
    public void onClick(View view, int position) {
        PracticeEntity item = items.get(position);

        if (!isPurchased && level != PracticeTable.LEVEL_N5 && kind != PracticeTable.TYPE_KANJI) { //N5 FREE

            if (kind == PracticeTable.TYPE_LISTENING && item.getNum() > Constant.TRIAL_LISTENING) {
                Log.i(TAG, "===> buy TYPE_LISTENING!!!");
                purchaseItem();
                return;
            } else if (kind == PracticeTable.TYPE_READING && item.getNum() > Constant.TRIAL_READING) {
                Log.i(TAG, "===> buy TYPE_READING!!!");
                purchaseItem();
                return;

            } else if (item.getNum() > Constant.TRIAL_GRAMMAR) {
                Log.i(TAG, "===> buy TYPE OTHER!!!");
                purchaseItem();
                return;
            }
        }

        if (kind == PracticeTable.TYPE_READING) {
            presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
//            setPositionScroll2();
            Intent i = new Intent(activity, PracticeReadingActivity.class);
            i.putExtra(Constant.INTENT_LEVEL, level);
            i.putExtra(Constant.INTENT_NUM, item.getNum());
            i.putExtra(Constant.INTENT_BOOKMARK, item.getBookmarks());
            i.putExtra(Constant.INTENT_DETAIL_NUM, item.getNumId());
            i.putExtra(Constant.INTENT_TITLE_Q, item.getQuestion());
            i.putExtra(Constant.INTENT_V1, v1);
            i.putExtra(Constant.INTENT_V2, v2);

            startActivity(i);
        } else if (kind == PracticeTable.TYPE_LISTENING) {
            presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
            Intent i = new Intent(activity, PracticeListeningActivity.class);
            i.putExtra(Constant.INTENT_LEVEL, level);
            i.putExtra(Constant.INTENT_NUM, item.getRef());
            Log.i(TAG, "onClick numId:" + item.getNum());
            startActivity(i);
        } else {
            if (kind == PracticeTable.TYPE_KANJI
                    && item.getNumId() > 200) { //truong hop ngoai le la kanji co man hinh rieng
                presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
                Intent i = new Intent(activity, PracticeKanJiActivity.class);
                i.putExtra(Constant.INTENT_LEVEL, level);
                i.putExtra(Constant.INTENT_NUM, item.getNum());
                i.putExtra(Constant.INTENT_BOOKMARK, item.getBookmarks());
                i.putExtra(Constant.INTENT_DETAIL_NUM, item.getNumId());
                i.putExtra(Constant.INTENT_TITLE_Q, item.getQuestion());
                i.putExtra(Constant.INTENT_V1, v1);
                i.putExtra(Constant.INTENT_V2, v2);

                startActivity(i);
            } else {
                PracticeDialog dialog = new PracticeDialog(activity, position, items, iPracticeInterface);
                dialog.show();
            }
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
                if (level == PracticeTable.LEVEL_N5)
                    adapter.setPurchased(true);
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
        setTitleQ(value, items.size());
    }

    private void setTitleQ(int v1, int v2) {
        setTitle(presenter.getTitle(v1, v2));
    }

}
