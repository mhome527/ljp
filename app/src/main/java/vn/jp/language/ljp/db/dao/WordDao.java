package vn.jp.language.ljp.db.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.db.table.WardTable;
import vn.jp.language.ljp.db.table.WordTable;
import vn.jp.language.ljp.entity.WordEntity;

/**
 * Created by huynhtran on 5/12/16.
 */
public class WordDao extends BaseDao<WordEntity>  {

    private final String TAG = "WardDao";

    public WordDao(Context context) {
        super(context);
    }

    @Override
    public WordEntity fetch(Cursor cursor) {
        WordEntity entity = new WordEntity();
        entity.kind = cursor.getInt(cursor.getColumnIndex(WordTable.COL_KIND));
        entity.jp1 = cursor.getString(cursor.getColumnIndex(WordTable.COL_JP1));
        entity.jp1 = cursor.getString(cursor.getColumnIndex(WordTable.COL_JP2));
        entity.other = cursor.getString(cursor.getColumnIndex(WordTable.COL_OT));
        return entity;
    }

    public List<WordEntity> getListData(int id) {
        List<WordEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM " + WardTable.TABLE_NAME + " WHERE " + WardTable.COL_DISTRICT_ID + " = " + id
                + " ORDER BY " + WardTable.COL_WARD_NAME;
        return fetchAll(sql);
    }


}
