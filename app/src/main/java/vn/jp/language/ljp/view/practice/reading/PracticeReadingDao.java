package vn.jp.language.ljp.view.practice.reading;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.practice.BasePracticeDao;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeReadingDao extends BasePracticeDao {

    @Override
    protected int getLevel() {
        return level;
    }

    @Override
    protected int getKind() {
        return PracticeTable.TYPE_READING;
    }

    public PracticeReadingDao(Context context, int level) {
        super(context);
        this.level = level;
    }

//    @Override
//    protected PracticeEntity fetch(Cursor cursor) {
//        PracticeEntity entity = new PracticeEntity();
//        entity.setNum(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_NUM)));
//        entity.setNumId(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_NUM_ID)));
//        entity.setBookmarks(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_BOOKMARKS)));
//        entity.setKind(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_KIND)));
//        entity.setQuestion(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_QUESTION)));
//        entity.setQ1(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q1)));
//        entity.setQ2(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q2)));
//        entity.setQ3(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q3)));
//        entity.setQ4(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q4)));
//        entity.setAns(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_ANS)));
//        entity.setReview(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_REVIEW)));
//        entity.setRef(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_REF)));
//        return entity;
//    }

    @Override
    protected PracticeEntity fetch(Cursor cursor, PracticeEntity entity) {
        return entity;
    }

    public List<PracticeEntity> getItems(int idRef) {
        String sql = "Select * From " + getTableName() + " where "
                + PracticeTable.COL_KIND + "=" + getKind()
                + " And " + PracticeTable.COL_REF + "=" + idRef;
        return fetchAll(sql);
    }

    public PracticeEntity getItemTitle(int num) {
        String sql = "Select * From " + getTableName() + " where "
                + PracticeTable.COL_KIND + "=" + getKind()
                + " And " + PracticeTable.COL_NUM + " = " + num
                + " And " + PracticeTable.COL_REF + "< 100";
        return fetch(sql);
    }


    public void updateStatus(int numId) {
        updateReview(getKind(), numId);
    }

}
