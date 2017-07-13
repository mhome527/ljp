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
    public PracticeDao(Context context) {
        super(context);
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

    public List<PracticeEntity> getGrammar(int level, int kind) {
        String sql = "Select * from " + PracticeTable.getTableName(level) + " where kind =" + kind
                + " Order by " + PracticeTable.COL_REVIEW + " desc, " + PracticeTable.COL_NUM + " asc ";
        return fetchAll(sql);
    }

    public static List<PracticeEntity> getGrammar(Context context, int level, int kind) {
        PracticeDao dao = new PracticeDao(context);
        return dao.getGrammar(level, kind);
    }

    private void updateAnswer(int level, int kind, int num, int review) {
        ContentValues value = new ContentValues();
        value.put(PracticeTable.COL_REVIEW, review);
        String where = PracticeTable.COL_KIND + " = " + kind + " AND "
                + PracticeTable.COL_NUM + " = " + num;
        updateRow(PracticeTable.getTableName(level), value, where);
    }

    public static void updateAnswer(Context context, int level, int kind, int num, int review) {
        PracticeDao dao = new PracticeDao(context);
        dao.updateAnswer(level, kind, num, review);
    }

    private void updateBookmark(int level, int kind, int num, int bookmark) {
        ContentValues value = new ContentValues();
        value.put(PracticeTable.COL_BOOKMARKS, bookmark);
        String where = PracticeTable.COL_KIND + " = " + kind + " AND "
                + PracticeTable.COL_NUM + " = " + num;
        updateRow(PracticeTable.getTableName(level), value, where);
    }

    public static void updateBookmark(Context context, int level, int kind, int num, int bookmark) {
        PracticeDao dao = new PracticeDao(context);
        dao.updateBookmark(level, kind, num, bookmark);
    }

}
