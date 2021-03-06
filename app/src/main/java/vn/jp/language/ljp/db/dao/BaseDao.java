package vn.jp.language.ljp.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.BaseApplication;
import vn.jp.language.ljp.BuildConfig;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.DatabaseHelper;
import vn.jp.language.ljp.utils.Log;


public abstract class BaseDao<T> {
    public final static String TAG = BaseDao.class.getName();
    protected Context context;

    public String lang = Constant.EN;

    public BaseDao(Context context) {
        this.context = context;
        lang = BaseApplication.getInstance().pref.getStringValue("", Constant.TYPE_LANGUAGE);
        if (lang.equals(""))
            lang = context.getString(R.string.language);
    }

//    protected abstract ContentValues getContentValues(T entity);

    protected abstract T fetch(Cursor cursor);

    //    protected List<T> fetchAll(Cursor cursor) {
//        List<T> listData = new ArrayList<>();
//
//        if (cursor != null) {
//            Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
//            if (cursor.moveToFirst()) {
//                do {
//                    listData.add(fetch(cursor));
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        }
//        return listData;
//    }
    protected Cursor query(String sql) {
        return DatabaseHelper.getInstance(context).executeQuery(sql);

    }

    protected T fetch(String sql) {
        Log.i(TAG, "fetch sql:" + sql);
        T entity = null;
        try {
            Cursor cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    entity = fetch(cursor);
                }
                cursor.close();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                e.printStackTrace();
        }
        return entity;
    }

    protected List<T> fetchAll(String sql) {
        Log.i(TAG, "fetchAll sql:" + sql);
        List<T> listData = new ArrayList<>();
        try {
            Cursor cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
                        listData.add(fetch(cursor));
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                e.printStackTrace();
        }
        return listData;
    }

    protected int updateRow(String tableName, ContentValues data, String where) {
        Log.i(TAG, "update data: " + where);
        return DatabaseHelper.updateData(context, tableName, data, where);
    }

    protected void executeQuery(String sql) {
        DatabaseHelper.getInstance(context).executeDMLQuery(sql);
    }
//    protected SQLiteDatabase getDB(){
//        return DatabaseHelper.getInstance(context).getDB();
//    }
}
