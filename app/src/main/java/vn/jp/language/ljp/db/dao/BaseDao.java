package vn.jp.language.ljp.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.utils.Log;


public abstract class BaseDao<T>{
    public final static String TAG = BaseDao.class.getName();
    protected Context context;
    public BaseDao(Context context){
        this.context = context;
    }
    protected abstract ContentValues getContentValues(T entity);
    protected abstract T fetch(Cursor cursor);

    protected List<T> fetchAll(Cursor cursor){
        List<T> listData = new ArrayList<>();

        if (cursor != null) {
            Log.i(TAG, "list "+this.getClass()+" size:" + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    listData.add(fetch(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return listData;
    }

//    protected SQLiteDatabase getDB(){
//        return DatabaseHelper.getInstance(context).getDB();
//    }
}
