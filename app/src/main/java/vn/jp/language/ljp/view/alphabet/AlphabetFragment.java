package vn.jp.language.ljp.view.alphabet;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.AlphabetEntity;
import vn.jp.language.ljp.sound.AudioManager;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Utility;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;
import vn.jp.language.ljp.view.custom.SpacesItemDecoration;

/**
 * Created by Administrator on 10/18/2016.
 */

public class AlphabetFragment extends BaseFragment<AlphabetActivity> {

    private String TAG = "AlphabetFragment";
    //    private View root;
    public Constant.TYPE_ALPHABET alphabet;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    AlphabetContentAdapter adapter;
    AlphabetPresenter presenter;
    List<AlphabetEntity> listData;
    AudioManager audio;
    private final String folder = "alphabet/";

    @Override
    public int getLayout() {
        return R.layout.alphabet_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new AlphabetPresenter(activity);
        audio = new AudioManager(activity);

        setupView();
        loadData();

    }

    public void setupView() {
        GridLayoutManager lLayout = new GridLayoutManager(activity, 5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = Utility.dpToPx(2);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i(TAG, "onClick row pos:" + listData.get(position).getSound());
                if (!listData.get(position).getOt().equals("-"))
                    audio.play(folder + listData.get(position).getSound());
//                    audio.play(folder + listData.get(position).getOt());
//                audio.play(folder + "call2.mp3");
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.i(TAG, "onLongClick row pos:" + position);
            }
        }));

    }

    public void loadData() {
        Log.i(TAG, "loadData");
        presenter.loadData(new ICallback() {
            @Override
            public void onCallback(Object list) {
                listData = (List<AlphabetEntity>) list;
                adapter = new AlphabetContentAdapter(alphabet, listData);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail");
            }
        });

    }
}
