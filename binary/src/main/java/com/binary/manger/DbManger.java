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

    /**
     * 执行SQL命令
     *
     * @param database 数据库对象
     * @param sql      SQL语句
     */
    public static void execSQL(SQLiteDatabase database, String sql) {
        if (database != null) {
            if (sql != null && !"".equals(sql)) {
                database.execSQL(sql);
            }
        }
    }

    /**
     * 根据SQL语句查询获得cursor对象
     *
     * @param database      数据库对象
     * @param sql           查询语句
     * @param selectionArgs 查询中的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase database, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(sql, selectionArgs);
        }

        return cursor;
    }


    /**
     * 将游标类型转化为List
     *
     * @param cursor
     * @return
     */
    public static List<Person> cursorToList(Cursor cursor) {
        List<Person> list = new ArrayList<>();
        // 判断游标中的数据是否读取完毕
        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex(Constants._ID);

            int _id = cursor.getInt(columnIndex);
            String name = cursor.getString(cursor.getColumnIndex(Constants.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constants.AGE));
            String des = cursor.getString(cursor.getColumnIndex(Constants.DES));

            Person person = new Person(_id, name, age, des);
            list.add(person);
        }

        return list;
    }

    /**
     * 根据数据库以及数据表名称获取表中数据总条目
     *
     * @param db        数据库对象
     * @param tableName 数据表名称
     * @return 总条目数
     */
    public static int getData(SQLiteDatabase db, String tableName) {
        int count = 0;
        if (db != null) {
            Cursor cursor = db.rawQuery("select * from " + tableName, null);
            count = cursor.getCount(); //获取cursor中数据总数
        }
        return count;
    }

    /**
     * 根据当前页码查询获取该页码应对应的集合数据
     *
     * @param db          数据库对象
     * @param tableName   数据表名称
     * @param currentPage 当前页码
     * @return 当前页码的集合
     * <p>
     * select * from person ?,?
     */
    public static List<Person> getListByCurrentPage(SQLiteDatabase db, String tableName,
                                                    int currentPage, int pageSzie) {

        int index = (currentPage - 1) * pageSzie;
        Cursor cursor = null;
        if (db != null) {
            String sql = "select * from " + tableName + " limit ?,?";
            cursor = db.rawQuery(sql, new String[]{index + "", pageSzie + ""});
        }


        return cursorToList(cursor);
    }

}
