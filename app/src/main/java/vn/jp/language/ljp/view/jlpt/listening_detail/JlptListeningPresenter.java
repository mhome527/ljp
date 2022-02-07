package vn.jp.language.ljp.view.jlpt.listening_detail;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/17/2017.
 */

public class JlptListeningPresenter extends BasePresenter<JlptListeningActivity> {

    int level;
    String test_date;
    int mondai;

    JlptListeningDao dao;

    public JlptListeningPresenter(JlptListeningActivity activity, int level, String test_date, int mondai) {
        super(activity);
        this.level = level;
        this.test_date = test_date;
        this.mondai = mondai;
        dao = new JlptListeningDao(activity, level, test_date, mondai);
    }



    public void loadData(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems();
            }
        });
    }
}
