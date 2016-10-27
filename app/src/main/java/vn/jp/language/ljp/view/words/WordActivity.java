package vn.jp.language.ljp.view.words;

import android.widget.GridView;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class WordActivity extends BaseActivity<WordActivity> {

    private static String TAG = "WordActivity";
    GridView gridView;
    List<WordEntity> listData;

    @Override
    protected int getLayout() {
        return R.layout.word_layout;
    }

    @Override
    protected void initView() {
        gridView = getView(R.id.gridView);
        loadData();
    }

    private void loadData() {
        WordPresenter presenter = new WordPresenter(this);
        presenter.loadData(1, new ICallback() {
            @Override
            public void onCallback(List list) {
                listData = list;
                gridView.setAdapter(new WordAdapter(activity, listData));
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail!!!!!!" + err);
            }
        });
    }
}
