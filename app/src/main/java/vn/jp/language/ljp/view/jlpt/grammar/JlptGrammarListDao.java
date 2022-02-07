package vn.jp.language.ljp.view.jlpt.grammar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.JlptGrammarTable;
import vn.jp.language.ljp.entity.JlptGrammarEntity;

/**
 * Created by Administrator on 02/04/2022.
 */

public class JlptGrammarListDao extends BaseDao<JlptGrammarEntity> {

    int level;
    int kind;

    public JlptGrammarListDao(Context context, int level, int kind) {
        super(context);
        this.level = level;
        this.kind = kind;
    }

    @SuppressLint("Range")
    @Override
    protected JlptGrammarEntity fetch(Cursor cursor) {
        JlptGrammarEntity entity = new JlptGrammarEntity();
        entity.level = cursor.getInt(cursor.getColumnIndex(JlptGrammarTable.COL_LEVEL));
        entity.test_date = cursor.getString(cursor.getColumnIndex(JlptGrammarTable.COL_TEST_DATE));
        if (cursor.getColumnIndex(JlptGrammarTable.COL_MONDAI) != -1)
            entity.mondai = cursor.getInt(cursor.getColumnIndex(JlptGrammarTable.COL_MONDAI));
        if (cursor.getColumnIndex(JlptGrammarTable.COL_QUESTION_ID) != -1)
            entity.question_id = cursor.getInt(cursor.getColumnIndex(JlptGrammarTable.COL_QUESTION_ID));
        if (cursor.getColumnIndex(JlptGrammarTable.COL_ARTICLE) != -1)
            entity.article = cursor.getString(cursor.getColumnIndex(JlptGrammarTable.COL_ARTICLE));

        return entity;
    }

    public List<JlptGrammarEntity> getItems() {
        String sql = "SELECT distinct Level, Test_date"
                + " From " + JlptGrammarTable.TABLE_NAME
                + " Where Level = " + level
                + " And Kind = " + kind
                + " Order by substr(Test_date, 4,4), substr(Test_date, 1,2) asc";
        return fetchAll(sql);
    }

    public List<JlptGrammarEntity> getItems(String test_date) {
        String sql = "SELECT  Level, Test_date, Question_id, Article, Mondai"
                + " From " + JlptGrammarTable.TABLE_NAME
                + " Where Level = " + level
                + " And kind = " + kind
                + " And Test_date = '" + test_date + "'"
                + " Order by Question_id asc";
        return fetchAll(sql);
    }


}
