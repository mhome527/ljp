package vn.jp.language.ljp.db.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.table.PhraseTable;
import vn.jp.language.ljp.db.table.WordTable;
import vn.jp.language.ljp.entity.PhraseEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 5/12/16.
 */
public class PhraseDao extends BaseDao<PhraseEntity> {

    private final String TAG = "PhraseDao";

    public PhraseDao(Context context) {
        super(context);
    }

    @Override
    public PhraseEntity fetch(Cursor cursor) {
        PhraseEntity entity = new PhraseEntity();
        entity.jp = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_JP));
        entity.romaji = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_ROMAJI));
        entity.ot = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_OT));
        entity.sound = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_SOUND));
        return entity;
    }

    public List<PhraseEntity> getListData() {
        String sql = "SELECT * FROM " + WordTable.TABLE_NAME + " w1, " + WordTable.TABLE_NAME_EN + " w2"
                + " ORDER BY " + WordTable.COL_JP1;
        Log.i(TAG, "phrase: sql=" + sql);
        return fetchAll(sql);
    }


}
