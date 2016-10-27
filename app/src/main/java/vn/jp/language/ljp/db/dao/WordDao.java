package vn.jp.language.ljp.db.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.table.WordTable;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 5/12/16.
 */
public class WordDao extends BaseDao<WordEntity> {

    private final String TAG = "WardDao";

    public WordDao(Context context) {
        super(context);
    }

    @Override
    public WordEntity fetch(Cursor cursor) {
        WordEntity entity = new WordEntity();
        entity.kind = cursor.getInt(cursor.getColumnIndex(WordTable.COL_KIND));
        entity.jp1 = cursor.getString(cursor.getColumnIndex(WordTable.COL_JP1));
        entity.jp2 = cursor.getString(cursor.getColumnIndex(WordTable.COL_JP2));
        entity.ot = cursor.getString(cursor.getColumnIndex(WordTable.COL_OT));
        entity.sound = cursor.getString(cursor.getColumnIndex(WordTable.COL_SOUND));
        entity.img = cursor.getString(cursor.getColumnIndex(WordTable.COL_IMG));
        return entity;
    }

    public List<WordEntity> getListData(int num) {
        String sql = "SELECT * FROM " + WordTable.TABLE_NAME + " w1, " + WordTable.TABLE_NAME_EN + " w2"
                + " WHERE " + WordTable.COL_NUM + " = " + num
                + " ORDER BY " + WordTable.COL_JP1;
        Log.i(TAG, "word: sql=" + sql);
        return fetchAll(sql);
    }


}
