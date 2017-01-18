package vn.jp.language.ljp.view.grammar.search;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.grammar.GrammarDao;

/**
 * Created by Administrator on 1/17/2017.
 */
public class GrammarSearchPresenter extends BasePresenter<GrammarSearchActivity> {

    public GrammarSearchPresenter(GrammarSearchActivity activity) {
        super(activity);
    }

    public void searchData(final String text, ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        return GrammarDao.searchData(activity, text);
                    }
                }
        );

    }
}
