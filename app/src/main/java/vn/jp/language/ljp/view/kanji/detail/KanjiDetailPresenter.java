package vn.jp.language.ljp.view.kanji.detail;

import vn.jp.language.ljp.entity.KanjiEntity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.kanji.KanjiDao;

/**
 * Created by HuynhTD on 01/18/2017.
 */

public class KanjiDetailPresenter extends BasePresenter<KanjiDetailActivity> {
    public KanjiDetailPresenter(KanjiDetailActivity activity) {
        super(activity);
    }


    public void getData(final int num, ICallback<KanjiEntity> callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                try {
                    KanjiEntity entity = KanjiDao.getData(activity, num);

                    return entity;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

}
