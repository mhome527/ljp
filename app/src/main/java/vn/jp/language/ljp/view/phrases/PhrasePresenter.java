package vn.jp.language.ljp.view.phrases;

import android.content.Context;

import java.util.List;

import vn.jp.language.ljp.db.dao.WordDao;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class PhrasePresenter extends BasePresenter {

    public PhrasePresenter(Context context) {
        super(context);
    }

    public void getData(final int kind, ICallback iCallback) {
        loadData(iCallback, new ILoadData() {
            @Override
            public List onBackground() {
                WordDao dao = new WordDao(context);
                return dao.getListData(kind);
            }
        });
    }

}
