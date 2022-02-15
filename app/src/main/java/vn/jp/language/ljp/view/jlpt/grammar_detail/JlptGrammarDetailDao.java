package vn.jp.language.ljp.view.jlpt.grammar_detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.JlptGrammarDetailTable;
import vn.jp.language.ljp.entity.JlptGrammarDetailEntity;

/**
 * Created by Administrator on 7/17/2017.
 */

public class JlptGrammarDetailDao extends BaseDao<JlptGrammarDetailEntity> {

    int level;
    int kind;
    String test_date;

    public JlptGrammarDetailDao(Context context, int level, String test_date, int kind) {
        super(context);
        this.level = level;
        this.test_date = test_date;
        this.kind = kind;
    }

    @SuppressLint("Range")
    @Override
    protected JlptGrammarDetailEntity fetch(Cursor cursor) {
        JlptGrammarDetailEntity entity = new JlptGrammarDetailEntity();
        entity.level = cursor.getInt(cursor.getColumnIndex(JlptGrammarDetailTable.COL_LEVEL));
        entity.question_id = cursor.getInt(cursor.getColumnIndex(JlptGrammarDetailTable.COL_QUESTION_ID));
        entity.num = cursor.getInt(cursor.getColumnIndex(JlptGrammarDetailTable.COL_NUM));
        entity.title = cursor.getString(cursor.getColumnIndex(JlptGrammarDetailTable.COL_TITLE));
        entity.q1 = cursor.getString(cursor.getColumnIndex(JlptGrammarDetailTable.COL_Q1));
        entity.q2 = cursor.getString(cursor.getColumnIndex(JlptGrammarDetailTable.COL_Q2));
        entity.q3 = cursor.getString(cursor.getColumnIndex(JlptGrammarDetailTable.COL_Q3));
        entity.q4 = cursor.getString(cursor.getColumnIndex(JlptGrammarDetailTable.COL_Q4));
        entity.ans = cursor.getInt(cursor.getColumnIndex(JlptGrammarDetailTable.COL_ANS));

        return entity;
    }

    public List<JlptGrammarDetailEntity> getItems(int question_id) {
        String sql = "SELECT * "
                + " From " + JlptGrammarDetailTable.TABLE_NAME
                + " Where Level = " + level
                + " AND Test_date = '" + test_date + "'"
                + " AND Question_id = " + question_id
                + " AND Kind = " + kind
                + " Order by num asc";
        return fetchAll(sql);
    }
}
