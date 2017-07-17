package vn.jp.language.ljp.view.practice;

import android.content.ContentValues;
import android.content.Context;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;

/**
 * Created by Administrator on 7/17/2017.
 */

abstract public class BasePracticeDao extends BaseDao<PracticeEntity> {
    abstract protected int getLevel();
    abstract protected int getKind();

    public BasePracticeDao(Context context) {
        super(context);
    }

    protected String getTableName() {
        return PracticeTable.getTableName(getLevel());
    }

    public void updateAnswer(int num, int review) {
        ContentValues value = new ContentValues();
        value.put(PracticeTable.COL_REVIEW, review);
        String where = PracticeTable.COL_KIND + " = " + getKind() + " AND "
                + PracticeTable.COL_NUM + " = " + num;
        updateRow(getTableName(), value, where);
    }

    public void updateBookmark(int num, int bookmark) {
        ContentValues value = new ContentValues();
        value.put(PracticeTable.COL_BOOKMARKS, bookmark);
        String where = PracticeTable.COL_KIND + " = " + getKind() + " AND "
                + PracticeTable.COL_NUM + " = " + num;
        updateRow(getTableName(), value, where);
    }

}
