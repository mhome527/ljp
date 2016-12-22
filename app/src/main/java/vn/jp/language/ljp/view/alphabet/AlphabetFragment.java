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
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Utility;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.ICallback;
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

    @Override
    public int getLayout() {
        return R.layout.alphabet_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new AlphabetPresenter(activity);

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

    }

    public void loadData() {
        Log.i(TAG, "loadData");
        presenter.loadData(new ICallback() {
            @Override
            public void onCallback(Object list) {
                List<AlphabetEntity> listData = (List<AlphabetEntity>) list;
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