package vn.jp.language.ljp.view.jlpt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.JlptListeningTable;
import vn.jp.language.ljp.entity.JlptEntity;

/**
 * Created by Administrator on 02/04/2022.
 */

public class JlptListDao extends BaseDao<JlptEntity> {

    int level;

    public JlptListDao(Context context, int level) {
        super(context);
        this.level = level;
    }

    @SuppressLint("Range")
    @Override
    protected JlptEntity fetch(Cursor cursor) {
        JlptEntity entity = new JlptEntity();
        entity.level = cursor.getInt(cursor.getColumnIndex(JlptListeningTable.COL_LEVEL));
        entity.test_date = cursor.getString(cursor.getColumnIndex(JlptListeningTable.COL_TEST_DATE));
        if (cursor.getColumnIndex(JlptListeningTable.COL_TITLE) != -1)
            entity.title = cursor.getString(cursor.getColumnIndex(JlptListeningTable.COL_TITLE));
        if (cursor.getColumnIndex(JlptListeningTable.COL_MONDAI) != -1)
            entity.mondai = cursor.getInt(cursor.getColumnIndex(JlptListeningTable.COL_MONDAI));
        if (cursor.getColumnIndex(JlptListeningTable.COL_FILENAME) != -1)
            entity.filename = cursor.getString(cursor.getColumnIndex(JlptListeningTable.COL_FILENAME));
        if (cursor.getColumnIndex(JlptListeningTable.COL_LINK_DOWNLOAD) != -1)
            entity.link_download = cursor.getString(cursor.getColumnIndex(JlptListeningTable.COL_LINK_DOWNLOAD));

        return entity;
    }

    public List<JlptEntity> getItems() {
        String sql = "SELECT distinct Level, Test_date"
                + " From " + JlptListeningTable.TABLE_NAME
                + " Where Level = " + level
                + " Order by substr(Test_date, 4,4), substr(Test_date, 1,2) asc";
        return fetchAll(sql);
    }

    public JlptEntity getItem(int mondai, String test_date) {
        String sql = "SELECT *  From " + JlptListeningTable.TABLE_NAME
                + " Where Level = " + level
                + " And Mondai = " + mondai
                + " And Test_date = '" + test_date + "'";
        return fetch(sql);
    }

}
