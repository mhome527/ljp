package vn.jp.language.ljp.view.date;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.WordTable;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 5/12/16.
 */
public class DateDao extends BaseDao<WordEntity> {

    private static final String TAG = "WardDao";

    public DateDao(Context context) {
        super(context);
    }

    @Override
    public WordEntity fetch(Cursor cursor) {
        WordEntity entity = new WordEntity();
        entity.kind = cursor.getInt(cursor.getColumnIndex(WordTable.COL_KIND));
        entity.jp1 = cursor.getString(cursor.getColumnIndex(WordTable.COL_JP1));
        entity.jp2 = cursor.getString(cursor.getColumnIndex(WordTable.COL_JP2));
        entity.ot = cursor.getString(cursor.getColumnIndex(WordTable.COL_OT));
        entity.romaji = cursor.getString(cursor.getColumnIndex(WordTable.COL_ROMAJI));
        entity.sound = cursor.getString(cursor.getColumnIndex(WordTable.COL_SOUND));
        entity.img = cursor.getString(cursor.getColumnIndex(WordTable.COL_IMG));
        return entity;
    }

    public static List<WordEntity> getListData(Context context, int kind) {
        String sql = "SELECT * FROM " + WordTable.TABLE_NAME
                + " WHERE " + WordTable.COL_KIND + " = " + kind
                + " ORDER BY " + WordTable.COL_JP1;
        Log.i(TAG, "word: sql=" + sql);
        DateDao dao = new DateDao(context);
        return dao.fetchAll(sql);
    }


}
