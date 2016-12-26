package vn.jp.language.ljp.view.grammar;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.GrammarEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by HuynhTran on 12/23/2016.
 */

public class GrammarFragment extends BaseFragment<GrammarActivity> implements IClickListener {

    private final String TAG = "GrammarFragment";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    GrammarPresenter presenter;
    int level = 5;


    @Override
    public int getLayout() {
        return R.layout.grammar_fragment;
    }

    @Override
    public void initView(View root) {
        presenter = new GrammarPresenter(activity);
        setupView();

        loadData(level);
    }

    public void setupView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration2(activity, R.drawable.line_divider));

    }

    public void loadData(int level) {
        Log.i(TAG, "loadData");
        presenter.loadData(level, new ICallback() {
            @Override
            public void onCallback(Object list) {
                List<GrammarEntity> listData = (List<GrammarEntity>) list;
                GrammarAdapter adapter = new GrammarAdapter(listData, GrammarFragment.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail");
            }
        });

    }

    @Override
    public void onClick(View view, int position) {
        //TODO: code something.
//        Intent i = new Intent(activity, G)
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}

