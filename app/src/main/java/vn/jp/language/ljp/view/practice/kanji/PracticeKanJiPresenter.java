package vn.jp.language.ljp.view.practice.kanji;

import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeKanJiPresenter extends BasePresenter<PracticeKanJiActivity> {

    int level;
    int idRef;

    PracticeKanJiDao dao;

    public PracticeKanJiPresenter(PracticeKanJiActivity activity, int level, int idRef) {
        super(activity);
        this.level = level;
        this.idRef = idRef;
        dao = new PracticeKanJiDao(activity, level);
    }

    public void load(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems(idRef);
            }
        });
    }

    public void loadNext(int num, ICallback iCallback) {
        PracticeEntity entity = dao.getItemTitle(num);
        if (entity != null && entity.getQuestion() != null && !entity.getQuestion().equals("")) {
            idRef = entity.getNumId();
            activity.titleQ = entity.getQuestion();
            activity.num = entity.getNum();
            activity.idRef = entity.getNumId();
            activity.bookmark = entity.getBookmarks();

            loadData(iCallback, new ILoadData() {
                @Override
                public Object onBackground() {
                    return dao.getItems(idRef);
                }
            });
        } else {
            iCallback.onCallback(null);
        }
    }

    public void updateAns(int num, int review) {
        dao.updateAnswer(num, review, idRef);
        dao.updateStatus(idRef);
    }

    public void updateBookmark(int num, int bookmark, int numId) {
        dao.updateBookmark(num, bookmark, numId);
    }
}
