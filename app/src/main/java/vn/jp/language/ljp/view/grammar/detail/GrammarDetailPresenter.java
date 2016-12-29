package vn.jp.language.ljp.view.grammar.detail;

import vn.jp.language.ljp.entity.GrammarEntity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.grammar.GrammarDao;

/**
 * Created by HuynhTD on 12/21/2016.
 */

public class GrammarDetailPresenter extends BasePresenter<GrammarDetailActivity> {
    public GrammarDetailPresenter(GrammarDetailActivity activity) {
        super(activity);
    }


    public void loadItem(final int level, final int num, ICallback<GrammarEntity> callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                try {
                    GrammarEntity grammarEntity = GrammarDao.getItemData(activity, level, num);
                    grammarEntity.formatData();
                    return grammarEntity;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

}
