package vn.jp.language.ljp.view.words;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class WordFragment extends BaseFragment<WordActivity> {

    private final String TAG = "WordFragment";
    private final String FOLDER = "words/";
    //    private View root;
    public Constant.TYPE_WORD typeWord = Constant.TYPE_WORD.ANIMAL;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    WordContentAdapter adapter;
    WordPresenter presenter;
    AudioPlayerManager audio;

    List<WordEntity> listData;

    @Override
    public int getLayout() {
        return R.layout.word_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new WordPresenter(activity);
        adapter = new WordContentAdapter();
        audio = new AudioPlayerManager(activity);

        setupView();
        loadData();
    }

    public void setupView() {
        GridLayoutManager lLayout;
        if (Common.isTablet(activity))
         lLayout = new GridLayoutManager(activity, 3);
        else
         lLayout = new GridLayoutManager(activity, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setPurchased(activity.isPurchased);
//        int spacingInPixels = Utility.dpToPx(2);
//        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i(TAG, "onClick row pos:" + position);
                activity.setTitleCenter(listData.get(position).getOt());

                if (activity.isPurchased || position < Constant.TRIAL) {
                    audio.play(FOLDER + listData.get(position).sound);
                } else {
                    Log.i(TAG, "===> buy!!!");
                    activity.purchaseItem();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.i(TAG, "onLongClick row pos:" + position);
            }
        }));
    }

    public void loadData() {
        Log.i(TAG, "loadData getKind():" + getKind());
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

    private int[] getKind() {
        switch (typeWord) {
            case ANIMAL:
                return new int[]{4};

            case FRUIT:
                return new int[]{1};

            case OTHER:
                return new int[]{2, 5, 6, 7, 12, 13};

            default:
                return new int[]{1};
        }
    }
}
