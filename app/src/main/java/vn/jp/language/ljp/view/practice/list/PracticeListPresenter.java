package vn.jp.language.ljp.view.practice.list;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

import static android.R.attr.level;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListPresenter extends BasePresenter<PracticeListActivity> {
    private final String TAG = "PracticeListPresenter";

    PracticeListActivity activity;

    public PracticeListPresenter(PracticeListActivity activity) {
        super(activity);
        this.activity = activity;
    }

    public String getTitle(int kind) {
        switch (kind) {
            case PracticeTable.TYPE_GRAMMAR:
                return activity.getString(R.string.title_n_grammar);
            case PracticeTable.TYPE_READING:
                return activity.getString(R.string.title_n_reading);
            case PracticeTable.TYPE_KANJI:
                return activity.getString(R.string.title_n_kanji);
            default:
                return activity.getString(R.string.title_n_vocabulary);
        }
    }

    public void getItems(ICallback callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return PracticeDao.getGrammar(activity, activity.level, activity.kind);
            }
        });
    }

    public void updateAnswer(int num, int ans) {
        PracticeDao.updateAnswer(activity, activity.level, activity.kind, num, ans);
    }

    public void updateBookmark(int num, int bookmark) {
        PracticeDao.updateBookmark(activity, activity.level, activity.kind, num, bookmark);
    }


}
