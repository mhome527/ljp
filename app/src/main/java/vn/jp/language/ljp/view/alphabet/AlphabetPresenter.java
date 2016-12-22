package vn.jp.language.ljp.view.alphabet;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 12/21/2016.
 */

public class AlphabetPresenter extends BasePresenter<AlphabetActivity> {
    public AlphabetPresenter(AlphabetActivity activity) {
        super(activity);
    }

    public void loadData(ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        AlphabetDao dao = new AlphabetDao(activity);
                        return dao.getListData();
                    }
                }
        );

    }
}
