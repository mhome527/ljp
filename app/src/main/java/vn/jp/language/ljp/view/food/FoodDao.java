package vn.jp.language.ljp.view.food;

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
public class FoodDao extends BaseDao<WordEntity> {

    private static final String TAG = "PhraseDao";

    public FoodDao(Context context) {
        super(context);
    }

    @Override
    public WordEntity fetch(Cursor cursor) {
        WordEntity entity = new WordEntity();
        entity.jp1 = cursor.getString(cursor.getColumnIndex(WordTable.COL_JP1));
//        entity.romaji = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_ROMAJI));
//        entity.ot = cursor.getString(cursor.getColumnIndex(WordTable.COL_OT));
        entity.sound = cursor.getString(cursor.getColumnIndex(WordTable.COL_SOUND));
        entity.img = cursor.getString(cursor.getColumnIndex(WordTable.COL_IMG));
        return entity;
    }

    public List<WordEntity> getListData() {
        String sql = "SELECT * FROM " + WordTable.getTableName()
                + " Where " + WordTable.COL_KIND + " = 3";

        Log.i(TAG, "food: sql=" + sql);
        return fetchAll(sql);
    }

}
