package vn.jp.language.ljp.view.practice.reading;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeReadingPresenter extends BasePresenter<PracticeReadingActivity> {

    int level;
    int idRef;

    PracticeReadingDao dao;

    public PracticeReadingPresenter(PracticeReadingActivity activity, int level, int idRef) {
        super(activity);
        this.level = level;
        this.idRef = idRef;
        dao = new PracticeReadingDao(activity, level);
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
        dao.updateAnswer(num, review, idRef);
        dao.updateStatus(idRef);
    }

    public void updateBookmark(int num, int bookmark, int numId) {
        dao.updateBookmark(num, bookmark, numId);
    }
}
