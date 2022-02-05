package vn.jp.language.ljp.view.jlpt.listening;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.JlptListeningDetailTable;
import vn.jp.language.ljp.db.table.JlptListeningTable;
import vn.jp.language.ljp.entity.JlptListeningEntity;

/**
 * Created by Administrator on 7/17/2017.
 */

public class JlptListeningDao extends BaseDao<JlptListeningEntity> {

    int level;
    String test_date;
    int mondai;

    public JlptListeningDao(Context context, int level, String test_date, int mondai) {
        super(context);
        this.level = level;
        this.test_date = test_date;
        this.mondai = mondai;
    }

    @SuppressLint("Range")
    @Override
    protected JlptListeningEntity fetch(Cursor cursor) {
        JlptListeningEntity entity = new JlptListeningEntity();
//        entity.level = cursor.getInt(cursor.getColumnIndex(JlptListeningDetailTable.COL_LEVEL));
        entity.num = cursor.getInt(cursor.getColumnIndex(JlptListeningDetailTable.COL_NUM));
        entity.title = cursor.getString(cursor.getColumnIndex(JlptListeningDetailTable.COL_TITLE));
        entity.q1 = cursor.getString(cursor.getColumnIndex(JlptListeningDetailTable.COL_Q1));
        entity.q2 = cursor.getString(cursor.getColumnIndex(JlptListeningDetailTable.COL_Q2));
        entity.q3 = cursor.getString(cursor.getColumnIndex(JlptListeningDetailTable.COL_Q3));
        entity.q4 = cursor.getString(cursor.getColumnIndex(JlptListeningDetailTable.COL_Q4));
        entity.ans = cursor.getInt(cursor.getColumnIndex(JlptListeningDetailTable.COL_ANS));
        entity.explain = cursor.getString(cursor.getColumnIndex(JlptListeningDetailTable.COL_EXPLAIN));

        return entity;
    }

    public List<JlptListeningEntity> getItems() {
        String sql = "SELECT * "
                + " From " + JlptListeningDetailTable.TABLE_NAME
                + " Where Level = " + level
                + " AND Test_date = '" + test_date + "'"
                + " AND Mondai = " + mondai
                + " Order by num asc";
        return fetchAll(sql);
    }
}
