package vn.jp.language.ljp.view.phrases;

import java.util.List;

import vn.jp.language.ljp.db.dao.PhraseDao;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class PhrasePresenter extends BasePresenter<PhraseActivity> {


    public PhrasePresenter(PhraseActivity activity) {
        super(activity);
    }

    public void loadData(ICallback iCallback) {
        loadData(iCallback, new ILoadData() {
            @Override
            public List onBackground() {
                PhraseDao dao = new PhraseDao(activity);
                return dao.getListData();
            }
        });
    }

}
