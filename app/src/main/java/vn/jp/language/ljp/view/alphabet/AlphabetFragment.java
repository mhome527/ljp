package vn.jp.language.ljp.view.alphabet;

import android.view.View;
import android.widget.GridView;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseFragment;

/**
 * Created by Administrator on 10/18/2016.
 */

public class AlphabetFragment extends BaseFragment {

    private String TAG = "AlphabetFragment";
//    private View root;
    public int page;
    GridView gridView;

    @Override
    public int getLayout() {
        return R.layout.alphabet_sub1_layout;
    }

    @Override
    public void initView(View root) {
        gridView = getView(R.id.gridView);
        loadData();
    }


    public void loadData() {
        if (page == 1) {

        } else {

        }
    }
}
