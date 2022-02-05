package vn.jp.language.ljp.view.number;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.NumberTable;
import vn.jp.language.ljp.entity.NumberEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 12/22/16.
 */
public class NumberDao extends BaseDao<NumberEntity> {

    private final String TAG = "NumberDao";

    public NumberDao(Context context) {
        super(context);
    }

    @SuppressLint("Range")
    @Override
    public NumberEntity fetch(Cursor cursor) {
        NumberEntity entity = new NumberEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(NumberTable.COL_NUM)));
        entity.setJp(cursor.getString(cursor.getColumnIndex(NumberTable.COL_JP)));
        entity.setRomaji(cursor.getString(cursor.getColumnIndex(NumberTable.COL_ROMAJI)));
        entity.setSound(cursor.getString(cursor.getColumnIndex(NumberTable.COL_SOUND)));
        return entity;
    }

    public List<NumberEntity> getListData() {
        String sql = "SELECT * FROM " + NumberTable.TABLE_NAME
                + " ORDER BY " + NumberTable.COL_NUM;
        Log.i(TAG, "word: sql=" + sql);
        return fetchAll(sql);
    }

    public List<NumberEntity> getListData(int kind) {
        String sql = "SELECT * FROM " + NumberTable.TABLE_NAME
                + " WHERE " + NumberTable.COL_KIND + " = " + kind
                + " ORDER BY " + NumberTable.COL_NUM;
        Log.i(TAG, "word: sql=" + sql);
        return fetchAll(sql);
    }


}
