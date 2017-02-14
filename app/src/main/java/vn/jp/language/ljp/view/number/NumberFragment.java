package vn.jp.language.ljp.view.number;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.NumberEntity;
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.custom.DividerItemDecoration2;

/**
 * Created by Administrator on 10/18/2016.
 */

public class NumberFragment extends BaseFragment<NumberActivity> implements IClickListener {

    private String TAG = "NumberFragment";
    //    private View root;
    private final String FOLDER = "number/";

    Constant.TYPE_NUMBERS numbers;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    NumberPresenter presenter;
    AudioPlayerManager audio;
    List<NumberEntity> listData;

    @Override
    public int getLayout() {
        return R.layout.number_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new NumberPresenter(activity);

        setupView();
        audio = new AudioPlayerManager(activity);
//        numbers = Constant.TYPE_NUMBERS.NUMBER;

        loadData();
    }

    public void setupView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration2(getActivity(), R.drawable.line_divider));
        Common.setupRecyclerView(activity, recyclerView, this);

    }

    public void loadData() {
        Log.i(TAG, "loadData");
        presenter.loadData(numbers, new ICallback() {
            @Override
            public void onCallback(Object list) {
                listData = (List<NumberEntity>) list;
                NumberContentAdapter adapter = new NumberContentAdapter(listData);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail");
            }
        });

    }

    // =========== IClickListener ==============
    @Override
    public void onClick(View view, int position) {
        switch (numbers){
            case NUMBER:
                audio.play(FOLDER + listData.get(position).getSound());
                break;
        }
    }

    @Override
    public void onLongClick(View view, int position) {

    }
    // ========= END IClickListener =============
}
