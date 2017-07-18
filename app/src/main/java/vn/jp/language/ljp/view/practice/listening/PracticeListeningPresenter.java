package vn.jp.language.ljp.view.practice.listening;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeListeningPresenter extends BasePresenter<PracticeListeningActivity> {

    int level;
    int idRef;

    PracticeListeningDao dao;

    public PracticeListeningPresenter(PracticeListeningActivity activity, int level, int idRef) {
        super(activity);
        this.level = level;
        this.idRef = idRef;
        dao = new PracticeListeningDao(activity, level);
    }

    public void load(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems(idRef);
            }
        });
    }

    public void updateAns(int num, int review) {
        dao.updateAnswer(num, review);
        dao.updateStatus(idRef);
    }
}
