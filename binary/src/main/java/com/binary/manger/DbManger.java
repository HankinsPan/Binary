package com.binary.manger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.binary.sqlite.MySqliteHelper;

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

}
