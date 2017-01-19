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
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class WordFragment extends BaseFragment<WordActivity> {

    private String TAG = "WordFragment";
    //    private View root;
    public Constant.TYPE_WORD typeWord = Constant.TYPE_WORD.ANIMAL;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    WordContentAdapter adapter;
    WordPresenter presenter;


    @Override
    public int getLayout() {
        return R.layout.word_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new WordPresenter(activity);
        adapter = new WordContentAdapter();

        setupView();
        loadData();
    }

    public void setupView() {
        GridLayoutManager lLayout = new GridLayoutManager(activity, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
//        int spacingInPixels = Utility.dpToPx(2);
//        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

    }

    public void loadData() {
        Log.i(TAG, "loadData");
        presenter.loadData(getKind(), new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> list) {
                adapter.setData(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail");
            }
        });
    }

    private int getKind() {
        switch (typeWord) {
            case ANIMAL:
                return 4;
            case OTHER:
            default:
                return 2;
        }
    }
}
