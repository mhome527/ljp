package vn.jp.language.ljp.view.date;

import android.view.View;
import android.widget.GridView;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DateEntity;
import vn.jp.language.ljp.view.BaseFragment;

/**
 * Created by Administrator on 10/18/2016.
 */

public class DateFragment extends BaseFragment {

    private String TAG = "DateFragment";
//    private View root;
    public int page;
    GridView gridView;
    List<DateEntity> list;

    @Override
    public int getLayout() {
        return R.layout.date_sub_layout;
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
