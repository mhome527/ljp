package vn.jp.language.ljp.view.jlpt.grammar;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;


/**
 * Created by Administrator on 7/17/2017.
 */

public class JlptGrammarListPresenter extends BasePresenter<JlptGrammarListActivity> {

    private static final String TAG = "JlptListPresenter";
    int level;
    int kind;

    JlptGrammarListDao dao;

    public JlptGrammarListPresenter(JlptGrammarListActivity activity, int level, int kind) {
        super(activity);
        this.level = level;
        this.kind = kind;
//        this.idRef = idRef;
        dao = new JlptGrammarListDao(activity, level, kind);
    }


    public void loadData(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems();
            }
        });
    }

//    public void loadMondai(String test_date, int mondai, ICallback iCallback) {
//        loadData(iCallback, new ILoadData() {
//            @Override
//            public Object onBackground() {
//                return dao.getItem(mondai, test_date);
//            }
//        });
//    }


}