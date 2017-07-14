package vn.jp.language.ljp.view.practice.list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeDao extends BaseDao<PracticeEntity> {
    private final String TAG = "PracticeDao";

    int level;
    int kind;
    String table;

    public PracticeDao(Context context, int level, int kind) {
        super(context);
        this.level = level;
        this.kind = kind;
        table = PracticeTable.getTableName(level);
    }

    @Override
    protected PracticeEntity fetch(Cursor cursor) {
        PracticeEntity entity = new PracticeEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_NUM)));
        entity.setBookmarks(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_BOOKMARKS)));
        entity.setKind(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_KIND)));
        entity.setQuestion(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_QUESTION)));
        entity.setQ1(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q1)));
        entity.setQ2(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q2)));
        entity.setQ3(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q3)));
        entity.setQ4(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q4)));
        entity.setAns(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_ANS)));
        entity.setReview(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_REVIEW)));
        return entity;
    }

//    public List<PracticeEntity> getItems() {
//        return getItems(true);
//    }

    public List<PracticeEntity> getItems(boolean isSort) {
        String sort;
        if (isSort)
            sort = " Order by " + PracticeTable.COL_REVIEW + " desc, " + PracticeTable.COL_NUM + " asc ";
        else
            sort = " Order by " + PracticeTable.COL_NUM + " asc ";


        String sql = "Select * from " + table + " where "
                + PracticeTable.COL_KIND + " = " + kind + " " + sort;

        return fetchAll(sql);
    }

    public List<PracticeEntity> getBookmark(boolean isSort) {
        String sort;
        if (isSort)
            sort = " Order by " + PracticeTable.COL_REVIEW + " desc, " + PracticeTable.COL_NUM + " asc ";
        else
            sort = " Order by " + PracticeTable.COL_NUM + " asc ";


        String sql = "Select * from " + table + " where "
                + PracticeTable.COL_KIND + " = " + kind + " And "
                + PracticeTable.COL_BOOKMARKS + " = 1" + sort;
        return fetchAll(sql);
    }


    public void updateAnswer(int num, int review) {
        ContentValues value = new ContentValues();
        value.put(PracticeTable.COL_REVIEW, review);
        String where = PracticeTable.COL_KIND + " = " + kind + " AND "
                + PracticeTable.COL_NUM + " = " + num;
        updateRow(table, value, where);
    }

    public void updateBookmark(int num, int bookmark) {
        ContentValues value = new ContentValues();
        value.put(PracticeTable.COL_BOOKMARKS, bookmark);
        String where = PracticeTable.COL_KIND + " = " + kind + " AND "
                + PracticeTable.COL_NUM + " = " + num;
        updateRow(table, value, where);
    }

}
