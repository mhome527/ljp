package vn.jp.language.ljp.view.ono;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 9/22/2017.
 */

public class OnoPresenter extends BasePresenter<OnoActivity> {

    OnoDao dao;

    public OnoPresenter(OnoActivity activity) {
        super(activity);
        dao = new OnoDao(activity);
    }

    public void getItems(ICallback callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems();
            }
        });
    }

    public void getBookmark(ICallback callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getBookmark();
            }
        });
    }


}
