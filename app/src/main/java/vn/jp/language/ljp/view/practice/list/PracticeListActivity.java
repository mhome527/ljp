package vn.jp.language.ljp.view.practice.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
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

    @Override
    protected int getLayout() {
        return R.layout.practice_list_layout;
    }

    @Override
    protected void initView() {
        Common.setupRecyclerView(activity, recyclerView, this);
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, 1);
        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 5);
        presenter = new PracticeListPresenter(this);
        loadData();

    }

    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {

    }

    @Override
    protected void dealWithIabSetupFailure() {

    }
    //    ========================== END PURCHASE ==============

    //   ==============  IClickListener
    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
//   ============= END IClickListener

    private void loadData() {
        presenter.getItems(new ICallback<List<PracticeEntity>>() {
            @Override
            public void onCallback(List<PracticeEntity> data) {
                items = data;
                adapter = new PracticeListAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail err:" + err);
            }
        });
    }

}
