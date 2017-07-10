package vn.jp.language.ljp.view.practice.list;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListPresenter extends BasePresenter<PracticeListActivity> {
    private final String TAG = "PracticeDetailPresenter";

    public PracticeListPresenter(PracticeListActivity activity) {
        super(activity);
    }

    public void getItems(ICallback callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return PracticeDao.getGrammar(activity, activity.level, activity.kind);
            }
        });

    }


}
