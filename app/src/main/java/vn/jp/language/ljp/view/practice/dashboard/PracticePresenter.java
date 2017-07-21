package vn.jp.language.ljp.view.practice.dashboard;

import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.practice.list.PracticeDao;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticePresenter extends BasePresenter<BaseActivity> {

    PracticeDao dao;

    public PracticePresenter(BaseActivity activity, int level, int kind) {
        super(activity);
        dao = new PracticeDao(activity, level, kind);
    }

    public int countAll(int kind) {
        dao.setKind(kind);
        return dao.countAll();
    }

    public int countCorrect(int kind) {
        dao.setKind(kind);
        return dao.countCorrect();
    }
}
