package vn.jp.language.ljp.view.jlpt.listening;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.JlptMstTable;
import vn.jp.language.ljp.entity.JlptMstEntity;

/**
 * Created by Administrator on 02/04/2022.
 */

public class JlptMstDao extends BaseDao<JlptMstEntity> {

    int level;
    int kind;


    public JlptMstDao(Context context, int level, int kind) {
        super(context);
        this.level = level;
        if (kind != Constant.KIND_LISTENING)
            this.kind = Constant.KIND_VOCABULARY;
        else
            this.kind = Constant.KIND_LISTENING;
    }

    @SuppressLint("Range")
    @Override
    protected JlptMstEntity fetch(Cursor cursor) {
        JlptMstEntity entity = new JlptMstEntity();
        entity.level = cursor.getInt(cursor.getColumnIndex(JlptMstTable.COL_LEVEL));
        entity.test_date = cursor.getString(cursor.getColumnIndex(JlptMstTable.COL_TEST_DATE));
        if (cursor.getColumnIndex(JlptMstTable.COL_INSERTED) != -1)
            entity.isInserted = cursor.getInt(cursor.getColumnIndex(JlptMstTable.COL_INSERTED));

        return entity;
    }

    public List<JlptMstEntity> getItems() {
        String sql = "SELECT  Level, Test_date, IsInserted"
                + " From " + JlptMstTable.TABLE_NAME
                + " Where Level = " + level
                + " And kind = " + kind
                + " Order by IsInserted desc, substr(Test_date, 4,4) desc, substr(Test_date, 1,2) desc";
        return fetchAll(sql);
    }


}
