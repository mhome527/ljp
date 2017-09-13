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

    protected abstract PracticeEntity fetch(Cursor cursor, PracticeEntity entity);

    public int level;
    public int kind;

    @Override
    protected PracticeEntity fetch(Cursor cursor) {
        PracticeEntity entity = new PracticeEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_NUM)));
        entity.setNumId(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_NUM_ID)));
        entity.setBookmarks(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_BOOKMARKS)));
        entity.setKind(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_KIND)));
        entity.setQuestion(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_QUESTION)));
        entity.setQ1(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q1)));
        entity.setQ2(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q2)));
        entity.setQ3(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q3)));
        entity.setQ4(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q4)));
        entity.setAns(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_ANS)));
        entity.setReview(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_REVIEW)));
        entity.setRef(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_REF)));

        if (cursor.getColumnIndex(PracticeTable.COL_HINT) >= 0)
            entity.setHint(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_HINT)));

//        return entity;
        return fetch(cursor, entity);
    }

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
            where += " And " + PracticeTable.COL_REF + " = " + numId;
        else
            where += " And " + PracticeTable.COL_REF + " < 100 ";

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
