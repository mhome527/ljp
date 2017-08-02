package vn.jp.language.ljp.view.practice.listening;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeListeningPresenter extends BasePresenter<PracticeListeningActivity> {

    int level;
//    int idRef;

    PracticeListeningDao dao;

    public PracticeListeningPresenter(PracticeListeningActivity activity, int level) {
        super(activity);
        this.level = level;
//        this.idRef = idRef;
        dao = new PracticeListeningDao(activity, level);
    }


    public void load(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems();
            }
        });
    }

    public void loadDetail(final int idRef, ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItemDetail(idRef);
            }
        });
    }

    public int countCorrect() {
        return dao.countCorrect();
    }

    public int countAll() {
        return dao.countAll();
    }

    public void updateAns(int num, int idRef, int review) {
        dao.updateAnswer(num, review, idRef);
//        dao.updateStatus(idRef);
    }

    public void updateBookmark(int num, int bookmark, int numId) {
        dao.updateBookmark(num, bookmark, numId);
    }
}
