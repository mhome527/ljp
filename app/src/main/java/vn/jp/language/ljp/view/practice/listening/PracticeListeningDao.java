package vn.jp.language.ljp.view.practice.listening;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.practice.BasePracticeDao;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeListeningDao extends BasePracticeDao {

    int level;

    @Override
    protected int getLevel() {
        return level;
    }

    @Override
    protected int getKind() {
        return PracticeTable.TYPE_LISTENING;
    }

    public PracticeListeningDao(Context context, int level) {
        super(context);
        this.level = level;
    }

    @SuppressLint("Range")
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
        entity.setTitle(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_TITLE)));
        entity.setSound(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_SOUND)));
        return entity;
    }

    /*

Select t.question title, t.q1 sound,  t.bookmarks bookmarks,
n.question question, n.num num, n.num_id num_id, n.kind kind, n.review review,
n.q1 q1, n.q2 q2, n.q3 q3, n.q4 q4, n.ans ans, n.id_ref id_ref
From (Select * From TblPracticeN3 Where kind=6 And num_id > 600) t,
(Select * From  TblPracticeN3 Where kind=6 And num_id < 600)  n
Where t.num_id = n.id_ref  Order by n.review asc, n.num asc
     */
    public List<PracticeEntity> getItems() {
        String sql = "Select t.question title, t.q1 sound,  t.bookmarks bookmarks, "
                + " n.question question, n.num num, n.num_id num_id, n.kind kind, n.review review, "
                + " n.q1 q1, n.q2 q2, n.q3 q3, n.q4 q4, n.ans ans, n.id_ref id_ref  "
                + " From "
                + "(Select * From " + getTableName()
                + " Where " + PracticeTable.COL_KIND + "=" + getKind()
                + " And num_id > 600) t, "
                + " (Select * From  " + getTableName()
                + " Where " + PracticeTable.COL_KIND + "=" + getKind()
                + " And num_id < 600)  n "
                + " Where t.num_id = n.id_ref "
                + " Order by " + PracticeTable.COL_REVIEW + " asc, " + PracticeTable.COL_NUM + " asc ";

        //        String sql = "Select * From " + getTableName() + " where "
//                + PracticeTable.COL_KIND + "=" + PracticeTable.TYPE_LISTENING
//                + " And " + PracticeTable.COL_REF + ">600 "
//                + " Order by " + PracticeTable.COL_NUM + " asc ";
        return fetchAll(sql);
    }

    public int countCorrect() {
        String sql = "SELECT Count(*) FROM " + getTableName()
                + " Where " + PracticeTable.COL_KIND + " = " + getKind()
                + " And " + PracticeTable.COL_REVIEW + " = 1 "
                + " And " + PracticeTable.COL_NUM_ID + " < 600";
        return countItem(sql);
    }

    public int countAll() {
        String sql = "SELECT Count(*) FROM " + getTableName()
                + " Where " + PracticeTable.COL_KIND + " = " + getKind()
                + " And " + PracticeTable.COL_NUM_ID + "< 600";
        return countItem(sql);
    }

    /*
    select * from tblpracticeN3
    where kind = 6
    and num_id < 600 and num = 2
    order by num asc
     */
    public PracticeEntity getItemDetail(int idRef) {
        String sql = "Select * From " + getTableName() + " Where "
                + PracticeTable.COL_KIND + "=" + getKind()
                + " And " + PracticeTable.COL_REF + "=" + idRef;
        return fetch(sql);
    }


//    public int coutListening() {
//        String sql = "SELECT Count(*) FROM " + getTableName()
//                + " Where " + PracticeTable.COL_KIND + " = " + PracticeTable.TYPE_LISTENING
//                + " And " + PracticeTable.COL_NUM_ID + " > 600";
//        return countItem(sql);
//    }

    public void updateStatus(int numId) {
        updateReview(getKind(), numId);
    }

}
