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
import vn.jp.language.ljp.view.words.WordPresenter;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class PhraseActivity extends BaseActivity {

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
        recyclerView = getView(R.id.gridView);

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
        WordPresenter presenter = new WordPresenter(this);
        presenter.loadData(1, new ICallback() {
            @Override
            public void onCallback(List list) {
                PhraseActivity.this.list = list;
                recyclerView.setAdapter(new PhraseAdapter(PhraseActivity.this.list));
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail!!!!!!" + err);
            }
        });
    }
}
