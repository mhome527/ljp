package vn.jp.language.ljp.view.practice.list;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListPresenter extends BasePresenter<BaseActivity> {
    private final String TAG = "PracticeListPresenter";

    BaseActivity activity;
    PracticeDao dao;

    int level, kind;

    public PracticeListPresenter(BaseActivity activity, int level, int kind) {
        super(activity);
        this.activity = activity;
        this.level = level;
        this.kind = kind;
        dao = new PracticeDao(activity, level, kind);
    }

    public String getTitle() {
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

    public void getItems(final boolean isSort, ICallback callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems(isSort);
            }
        });
    }

    public void getBookmark(final boolean isSort, ICallback callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getBookmark(isSort);
            }
        });
    }

    public void getItems(ICallback callback) {
        getItems(false, callback);
    }

//    public void getBookmark(ICallback callback) {
//        loadData(callback, new ILoadData() {
//            @Override
//            public Object onBackground() {
//                return dao.getBookmark();
//            }
//        });
//    }

    public void updateAnswer(int num, int ans) {
        dao.updateAnswer(num, ans);
    }

    public void updateBookmark(int num, int bookmark) {
        dao.updateBookmark(num, bookmark);
    }


}
