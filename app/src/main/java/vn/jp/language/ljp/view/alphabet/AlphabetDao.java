package vn.jp.language.ljp.view.alphabet;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.AlphabetTable;
import vn.jp.language.ljp.entity.AlphabetEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 5/12/16.
 */
public class AlphabetDao extends BaseDao<AlphabetEntity> {

    private final String TAG = "AlphabetDao";

    public AlphabetDao(Context context) {
        super(context);
    }

    @Override
    public AlphabetEntity fetch(Cursor cursor) {
        AlphabetEntity entity = new AlphabetEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(AlphabetTable.COL_NUM)));
        entity.setJp1(cursor.getString(cursor.getColumnIndex(AlphabetTable.COL_JP1)));
        entity.setJp2(cursor.getString(cursor.getColumnIndex(AlphabetTable.COL_JP2)));
        entity.setOt(cursor.getString(cursor.getColumnIndex(AlphabetTable.COL_OT)));
        entity.setSound(cursor.getString(cursor.getColumnIndex(AlphabetTable.COL_SOUND)));
        return entity;
    }

    public List<AlphabetEntity> getListData() {
        String sql = "SELECT * FROM " + AlphabetTable.TABLE_NAME
                + " ORDER BY " + AlphabetTable.COL_NUM;
        Log.i(TAG, "word: sql=" + sql);
        return fetchAll(sql);
    }


}
