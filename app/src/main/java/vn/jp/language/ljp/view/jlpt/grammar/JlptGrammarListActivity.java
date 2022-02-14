package vn.jp.language.ljp.view.jlpt.grammar;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.JlptMstEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IJlptClickListener;
import vn.jp.language.ljp.view.jlpt.grammar_detail.JlpGrammarDetailActivity;
import vn.jp.language.ljp.view.purchase.PurchaseActivity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class JlptGrammarListActivity extends PurchaseActivity<JlptGrammarListActivity> implements IJlptClickListener {
    private final String TAG = "JlptListActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };

    List<JlptMstEntity> items;
    JlptGrammarListAdapter adapter;
    JlptGrammarListPresenter presenter;
    int level;
    int kind;

    // declare the dialog as a member field of your activity
    ProgressDialog mProgressDialog;

    @Override
    protected int getLayout() {
        return R.layout.jlpt_listening_list_layout;
    }

    @Override
    protected void initView() {
        Common.setupRecyclerView(activity, recyclerView, null);
        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, PracticeTable.LEVEL_N5);
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, 1);
        presenter = new JlptGrammarListPresenter(activity, level, kind);
        if (kind == Constant.KIND_VOCABULARY)
            setTitle("文字 N" + level);
        else if (kind == Constant.KIND_GRAMMAR)
            setTitle("文法 N" + level);
        else if (kind == Constant.KIND_READING)
            setTitle("読解 N" + level);
        else if (kind == Constant.KIND_LISTENING)
            setTitle("聴解 N" + level);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_practice_list, menu);
//        return true;
//    }

    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;
            adapter.setPurchased(isPurchased);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

            /// Test only
//            if (BuildConfig.DEBUG)
//                clearPurchaseTest();

        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {

    }
    //    ========================== END PURCHASE ==============

    //   ==============  IJlptClickListener - item click
    @Override
    public void onClick(int position, int mondai) {
        Log.i(TAG, "item click:" + position + "; mondai:" + mondai);
        startJlptGrammar(items.get(position));
    }
//   ============= END IJlptClickListener

    public void startJlptGrammar(JlptMstEntity item) {
        Intent i = new Intent(activity, JlpGrammarDetailActivity.class);
        i.putExtra(Constant.INTENT_LEVEL, item.level);
        i.putExtra(Constant.INTENT_TEST_DATE, item.test_date);
        i.putExtra(Constant.INTENT_KIND, kind);
        startActivity(i);
    }

    private void loadData() {
        presenter.loadData(new ICallback() {
            @Override
            public void onCallback(Object data) {
                items = (List<JlptMstEntity>) data;
                JlptGrammarListAdapter adapter = new JlptGrammarListAdapter(items, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }


//    private void setTitleQ(int value) {
//        setTitleQ(value, items.size());
//    }
//
//    private void setTitleQ(int v1, int v2) {
//        setTitle(presenter.getTitle(v1, v2));
//    }

}
