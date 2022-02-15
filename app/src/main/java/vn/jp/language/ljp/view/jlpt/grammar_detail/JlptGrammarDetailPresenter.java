package vn.jp.language.ljp.view.jlpt.grammar_detail;

import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.jlpt.grammar.JlptGrammarListDao;

/**
 * Created by Administrator on 7/17/2017.
 */

public class JlptGrammarDetailPresenter extends BasePresenter<JlpGrammarDetailActivity> {

    int level;
    String test_date;
    int kind;

    JlptGrammarDetailDao dao;
    JlptGrammarListDao listDao;

    public JlptGrammarDetailPresenter(JlpGrammarDetailActivity activity, int level, String test_date, int kind) {
        super(activity);
        this.level = level;
        this.test_date = test_date;
        this.kind = kind;
        dao = new JlptGrammarDetailDao(activity, level, test_date, kind);
        listDao = new JlptGrammarListDao(activity, level, kind);
    }



    public void loadList(String test_date, ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return listDao.getItems(test_date);
            }
        });
    }

    public void loadItem(int question_id, ICallback iCallback){
        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems(question_id);
            }
        });
    }
}
