package vn.jp.language.ljp.view.kanji;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 1/17/2017.
 */
public class KanjiPresenter extends BasePresenter<KanjiActivity> {

    public KanjiPresenter(KanjiActivity activity) {
        super(activity);
    }

    public void getListData(ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        return KanjiDao.getListData(activity);
                    }
                }
        );

    }

    public void searchData(final String text, ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        return KanjiDao.searchData(activity, text);
                    }
                }
        );

    }
}
