package vn.jp.language.ljp.view.date;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 10/18/2016.
 */

public class DateActivity extends BaseActivity {

    private String TAG = "DateActivity";

    @Override
    protected int getLayout() {
        return R.layout.date_layout;
    }

    @Override
    protected void initView() {

    }

    private void loadData(){
        DatePresenter presenter = new DatePresenter(this);
        presenter.loadData(2, new ICallback() {
            @Override
            public void onCallback(List list) {

            }

            @Override
            public void onFail(String err) {

            }
        });
    }
}
