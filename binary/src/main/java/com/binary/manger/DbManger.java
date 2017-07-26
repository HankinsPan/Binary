package com.binary.manger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.binary.bean.Person;
import com.binary.sqlite.MySqliteHelper;
import com.binary.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/26.
 */

public class DbManger {
    private static MySqliteHelper helper;

    public static MySqliteHelper getInstance(Context context) {
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    public static void execSQL(SQLiteDatabase database, String sql) {
        if (database != null) {
            if (sql != null && !"".equals(sql)) {
                database.execSQL(sql);
            }
        }
    }

    /**
     * 根据SQL语句查询获得cursor对象
     * @param database 数据库对象
     * @param sql 查询语句
     * @param selectionArgs 查询中的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase database,String sql,String[] selectionArgs){
        Cursor cursor = null;
        if (database!=null){
            cursor = database.rawQuery(sql,selectionArgs);
        }

        return cursor;
    }


    public static List<Person> cursorToList(Cursor cursor){
        List<Person> list = new ArrayList<>();
        // 判断游标中的数据是否读取完毕
        while (cursor.moveToNext()){
            int columnIndex = cursor.getColumnIndex(Constants._ID);

            int _id = cursor.getInt(columnIndex);
            String name = cursor.getString(cursor.getColumnIndex(Constants.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constants.AGE));
            String des = cursor.getString(cursor.getColumnIndex(Constants.DES));

            Person person = new Person(_id,name,age,des);
            list.add(person);
        }


        return list;
    }

}
