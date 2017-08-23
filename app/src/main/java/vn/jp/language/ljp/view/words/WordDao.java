package vn.jp.language.ljp.view.words;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.BaseTable;
import vn.jp.language.ljp.db.table.WordTable;
import vn.jp.language.ljp.entity.WordEntity;

/**
 * Created by huynhtran on 5/12/16.
 */
public class WordDao extends BaseDao<WordEntity> {

    private static final String TAG = "WardDao";

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
        entity.romaji = cursor.getString(cursor.getColumnIndex(WordTable.COL_ROMAJI));
        entity.sound = cursor.getString(cursor.getColumnIndex(WordTable.COL_SOUND));
        entity.img = cursor.getString(cursor.getColumnIndex(WordTable.COL_IMG));
        return entity;
    }

    public static List<WordEntity> getListData(Context context, int[] kind) {
        String where =  WordTable.COL_KIND + " ";
        String tmp;
        if (kind.length > 1) {
            tmp = kind[0] + "";
            for (int i = 1; i < kind.length; i++) {
                tmp += "," + kind[i] ;
            }
            where += " IN (" + tmp + ")";
        } else {
            where += " = " + kind[0];
        }


        WordDao dao = new WordDao(context);
        String sql = "SELECT * FROM " + WordTable.getTableName(dao.lang)
                + " Where " + BaseTable.appendCond()
                + " And " + where
                + " ORDER BY " + WordTable.COL_JP1;
        return dao.fetchAll(sql);
    }


}
