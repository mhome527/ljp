package vn.jp.language.ljp.view.words;

import java.util.List;

import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class WordPresenter extends BasePresenter<WordActivity> {


    public WordPresenter(WordActivity activity) {
        super(activity);
    }

    public void loadData(final int kind, ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return WordDao.getListData(activity, kind);
                    }
                }
        );
    }

}
