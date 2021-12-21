package vn.jp.language.ljp.view.practice.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
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
import vn.jp.language.ljp.view.grammar.search.GrammarSearchActivity;
import vn.jp.language.ljp.view.practice.dialog.PracticeDialog;
import vn.jp.language.ljp.view.practice.kanji.PracticeKanJiActivity;
import vn.jp.language.ljp.view.practice.listening.PracticeListeningActivity;
import vn.jp.language.ljp.view.practice.reading.PracticeReadingActivity;
import vn.jp.language.ljp.view.purchase.PurchaseActivity;

import static vn.jp.language.ljp.BaseApplication.mFirebaseAnalytics;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticeListActivity extends PurchaseActivity<PracticeListActivity> implements IClickListener {
    private final String TAG = "PracticeListActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<PracticeEntity> items;
    PracticeListAdapter adapter;
    PracticeListPresenter presenter;
    int kind;
    boolean isSort = true;
    int v1;
    int v2;
    private int mTotalScrolled = 0;

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
        setTitleQ();

        ///////
        if (!BuildConfig.DEBUG) {
            Bundle params = new Bundle();

            if (kind == PracticeTable.TYPE_GRAMMAR)
                params.putString("GRAMMAR", "level: " + level);
            else if (kind == PracticeTable.TYPE_READING)
                params.putString("READING", "level: " + level);
            else if (kind == PracticeTable.TYPE_LISTENING)
                params.putString("LISTENING", "level: " + level);
            else if (kind == PracticeTable.TYPE_KANJI)
                params.putString("KANJI", "level: " + level);
            else
                params.putString("VOCABULARY", "level: " + level);

            mFirebaseAnalytics.logEvent("Practice", params);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
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

            case R.id.menu_search:
                Intent iSearch = new Intent(activity, GrammarSearchActivity.class);
                startActivity(iSearch);
                return true;

            case R.id.menuBookmark:
                presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
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
            presenter.putPosHistory(recyclerView.computeVerticalScrollOffset()); //scroll lai vi tri truoc do
//            setPositionScroll2();
            Intent i = new Intent(activity, PracticeReadingActivity.class);
            i.putExtra(Constant.INTENT_LEVEL, level);
            i.putExtra(Constant.INTENT_NUM, item.getNum());
            i.putExtra(Constant.INTENT_BOOKMARK, item.getBookmarks());
            i.putExtra(Constant.INTENT_DETAIL_NUM, item.getNumId());
            i.putExtra(Constant.INTENT_TITLE_Q, item.getQuestion());
            i.putExtra(Constant.INTENT_HINT, item.getHint());
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

                if (level == PracticeTable.LEVEL_N5)
                    isPurchased = true;

                PracticeDialog dialog = new PracticeDialog(activity, position, items, iPracticeInterface);
                dialog.show();
            }
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
            item.setReview(value);

            adapter.notifyItemChanged(pos);
        }
    };


    private void loadData() {
        presenter.getItems(isSort, new ICallback<List<PracticeEntity>>() {
            @Override
            public void onCallback(List<PracticeEntity> data) {
                items = data;
                adapter = new PracticeListAdapter(data);
                if (level == PracticeTable.LEVEL_N5)
                    adapter.setPurchased(true);
                else
                    adapter.setPurchased(isPurchased);

                recyclerView.setAdapter(adapter);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();

                    }
                });


//                setPositionScroll();
                mTotalScrolled = presenter.getPosHistory();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.scrollBy(0, mTotalScrolled);

                    }
                }, 20);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail err:" + err);
            }
        });
    }

    private void setTitleQ() {
        setTitle(presenter.getTitle());
    }

}
