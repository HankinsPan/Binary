package com.binary.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.binary.util.Constants;

/**
 * Created by bestotem on 2017/7/26.
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    public static final String TAG = "MySqliteHelper";

    /**
     * 构造函数
     *
     * @param context 上下文对象
     * @param name    数据库名称
     * @param factory 游标工厂
     * @param version 版本号 >=1
     */
    public MySqliteHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    /**
     * 当数据库创建时调用
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG,"-- onCreate --");
        String sql = "create table "
                +Constants.TABLE_NAME+" ("
                +Constants._ID+ " Integer primary key,"
                +Constants.NAME+" varchar(10),"
                +Constants.AGE+" Integer,"
                +Constants.DES+" varchar(30)"
                + ")";

        db.execSQL(sql);

    }

    /**
     * 当数据库版本更新时调用
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e(TAG,"-- onUpgrade --");
    }

    /**
     * 当数据库打开时调用
     *
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.e(TAG,"-- onOpen --");
    }
}
