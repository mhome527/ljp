package vn.jp.language.ljp.view.practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import vn.jp.language.ljp.BuildConfig;
import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.utils.Log;

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
//        ContentValues value = new ContentValues();
//        value.put(PracticeTable.COL_REVIEW, review);
//        String where = PracticeTable.COL_KIND + " = " + getKind() + " AND "
//                + PracticeTable.COL_NUM + " = " + num;
//        updateRow(getTableName(), value, where);
        updateAnswer(num, review, 0);
    }

    public void updateAnswer(int num, int review, int refId) {
        ContentValues value = new ContentValues();
        String where = PracticeTable.COL_KIND + " = " + getKind() + " AND "
                + PracticeTable.COL_NUM + " = " + num;

        value.put(PracticeTable.COL_REVIEW, review);

        if (refId > 0)
            where += " And " + PracticeTable.COL_REF + " = " + refId;

        updateRow(getTableName(), value, where);
    }

    public void updateBookmark(int num, int bookmark) {
        updateBookmark(num, bookmark, 0);
    }

    public void updateBookmark(int num, int bookmark, int numId) {
        ContentValues value = new ContentValues();
        value.put(PracticeTable.COL_BOOKMARKS, bookmark);
        String where = PracticeTable.COL_KIND + " = " + getKind() + " AND "
                + PracticeTable.COL_NUM + " = " + num;

        if (numId > 0)
            where += " And " + PracticeTable.COL_NUM_ID + " = " + numId;

        updateRow(getTableName(), value, where);
    }

//    protected void updateReview(int kind, int num) {
//        String minReview = "(Select min(" + PracticeTable.COL_REVIEW + ") " +
//                " From " + getTableName() + " Where " + PracticeTable.COL_KIND + " = " + kind +
//                " And " + PracticeTable.COL_REF + " = " + num + " ) ";
//
//        String sql = "Update " + getTableName()
//                + " Set " + PracticeTable.COL_REVIEW + " = " + minReview
//                + " Where  " + PracticeTable.COL_KIND + " = " + kind
//                + " And " + PracticeTable.COL_NUM + " = " + num;
//
//        Log.i(TAG, "update status:" + sql);
//        executeQuery(sql);
//
//    }

    protected void updateReview(int kind, int numId) {
        String minReview = "(Select min(" + PracticeTable.COL_REVIEW + ") " +
                " From " + getTableName() + " Where " + PracticeTable.COL_KIND + " = " + kind +
                " And " + PracticeTable.COL_REF + " = " + numId + " ) ";

        String sql = "Update " + getTableName()
                + " Set " + PracticeTable.COL_REVIEW + " = " + minReview
                + " Where  " + PracticeTable.COL_KIND + " = " + kind
                + " And " + PracticeTable.COL_NUM_ID + " = " + numId;

        Log.i(TAG, "update status:" + sql);
        executeQuery(sql);
    }

    protected int countItem(String sql) {
        int count = -1;
        try {
            Cursor cursor = query(sql);
            if (cursor != null) {
                Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    count = cursor.getInt(0);
                }
                cursor.close();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                e.printStackTrace();
        }
        return count;
    }

}
