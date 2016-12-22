package vn.jp.language.ljp.view.number;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.NumberEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.custom.DividerItemDecoration2;

/**
 * Created by Administrator on 10/18/2016.
 */

public class NumberFragment extends BaseFragment<NumberActivity> {

    private String TAG = "NumberFragment";
    //    private View root;

    Constant.TYPE_NUMBERS numbers;
    public int pos = 1;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

   @BindView(R.id.tvMean)
   TextView tvMean;

//    NumberContentAdapter adapter;
    NumberPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.number_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new NumberPresenter(activity);

        setupView();
        numbers = Constant.TYPE_NUMBERS.NUMBER;
        loadData();
    }

    public void setupView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration2(getActivity(), R.drawable.line_divider));

    }

    public void loadData() {
        Log.i(TAG, "loadData");
        presenter.loadData(numbers, new ICallback() {
            @Override
            public void onCallback(Object list) {
                List<NumberEntity> listData = (List<NumberEntity>) list;
                NumberContentAdapter adapter = new NumberContentAdapter(listData);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail");
            }
        });

    }
}
