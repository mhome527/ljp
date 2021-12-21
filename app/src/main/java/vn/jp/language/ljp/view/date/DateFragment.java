package vn.jp.language.ljp.view.date;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by Administrator on 10/18/2016.
 */

public class DateFragment extends BaseFragment<DateActivity> implements IClickListener {

    private String TAG = "DateFragment";
    private final String FOLDER = "date/";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public int page;

    List<WordEntity> listData;
    DatePresenter presenter;
    DateContentAdapter adapter;
    AudioPlayerManager audio;

    @Override
    public int getLayout() {
        return R.layout.date_content_layout;
    }

    @Override
    public void initView(View root) {
        presenter = new DatePresenter(activity);
        adapter = new DateContentAdapter();
        setupView();
        audio = new AudioPlayerManager(activity);

        loadData();
    }

    private void setupView() {
        recyclerView.setAdapter(adapter);
        Common.setupRecyclerViewGrid(activity, recyclerView, this);
    }

    public void loadData() {
        presenter.loadData(getKind(), new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> list) {
                listData = list;
                adapter.setData(listData);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail");
            }
        });
    }

    private int getKind() {
        if (page == 1)
            return 16;
        return 15;
    }

    // ========== IClickListener ==========
    @Override
    public void onClick(View view, int position) {
//        Log.i(TAG, "pos: " + position);
        Log.i(TAG, "onClick row pos:" + listData.get(position).sound);
        audio.play(FOLDER + listData.get(position).sound);
    }

    @Override
    public void onLongClick(View view, int position) {

    }
    //  ===== end IClickListener =========
}
