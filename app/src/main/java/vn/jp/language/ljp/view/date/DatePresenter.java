package vn.jp.language.ljp.view.date;

import java.util.List;

import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.words.WordDao;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class DatePresenter extends BasePresenter<DateActivity> {
    public DatePresenter(DateActivity activity) {
        super(activity);
    }

    public void loadData(final int kind, ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return DateDao.getListData(activity, kind);
                    }
                }
        );
    }
}
