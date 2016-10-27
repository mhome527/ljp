package vn.jp.language.ljp.view.phrases;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PhraseEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;
import vn.jp.language.ljp.view.custom.DividerItemDecoration;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class PhraseActivity extends BaseActivity<PhraseActivity> {

    private static String TAG = "PhraseActivity";

    RecyclerView recyclerView;
    List<PhraseEntity> list;

    @Override
    protected int getLayout() {
        return R.layout.phrase_layout;
    }

    @Override
    protected void initView() {
        initControl();
        loadData();
    }

    private void initControl() {
        recyclerView = getView(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Adding RecyclerView Divider / Separator
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i(TAG, "onClick row pos:" + position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.i(TAG, "onLongClick row pos:" + position);
            }
        }));
    }

    private void loadData() {
        PhrasePresenter presenter = new PhrasePresenter(this);
        presenter.loadData(new ICallback() {
            @Override
            public void onCallback(List list) {
                activity.list = list;
                recyclerView.setAdapter(new PhraseAdapter(activity.list));
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail!!!!!!" + err);
            }
        });
    }
}
