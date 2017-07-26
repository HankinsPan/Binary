package com.binary.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.binary.R;
import com.binary.bean.Person;
import com.binary.manger.DbManger;
import com.binary.util.Constants;

import java.util.List;

/**
 * Created by bestotem on 2017/7/26.
 */

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "SQLiteActivity";

    private Button btnC, btnI, btnM, btnD, btnS;
    private Button btninsertApi, btnUpdateApi, btnSelectApi,btnList;
    private ListView lvList;

    private MySqliteHelper helper;

//    public static boolean isCount = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        helper = DbManger.getInstance(this);

        initView();
        addLitener();
//        initData();

    }

    private void initView() {
        btnC = (Button) findViewById(R.id.btn_create);
        btnI = (Button) findViewById(R.id.btn_insert);
        btnM = (Button) findViewById(R.id.btn_modify);
        btnD = (Button) findViewById(R.id.btn_del);
        btnS = (Button) findViewById(R.id.btn_select);

        btninsertApi = (Button) findViewById(R.id.btn_insert_api);
        btnUpdateApi = (Button) findViewById(R.id.btn_update_api);
        btnSelectApi = (Button) findViewById(R.id.btn_select_api);
        btnList = (Button) findViewById(R.id.btn_list);

        lvList = (ListView) findViewById(R.id.lv_list);
    }

    private void addLitener() {
        btnC.setOnClickListener(this);
        btnI.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnS.setOnClickListener(this);

        btninsertApi.setOnClickListener(this);
        btnUpdateApi.setOnClickListener(this);
        btnSelectApi.setOnClickListener(this);
        btnList.setOnClickListener(this);

    }

    private void startAdapterList(Cursor cursor) {
//        SQLiteDatabase database;

//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"info4.db";
//
//        database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);


//        cursor = database.rawQuery("select * from "+Constants.TABLE_NAME,null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.sql_item,
                cursor,
                new String[]{Constants._ID,Constants.NAME,Constants.AGE,Constants.DES},
                new int[]{R.id.tv_sql_id,R.id.tv_sql_name,R.id.tv_sql_age,R.id.tv_sql_des},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lvList.setAdapter(adapter);

    }


    @Override
    public void onClick(View view) {
        SQLiteDatabase database;

        switch (view.getId()) {
            case R.id.btn_create:
                // helper.getWritableDatabase(); -> 创建或打开数据库 （可读可写）
                //helper.getReadableDatabase();
                database = helper.getWritableDatabase();
                break;

            case R.id.btn_insert:
                database = helper.getWritableDatabase();
                for (int i = 1; i <= 20; i++) {

                    String insertSql = "insert into "
                            + Constants.TABLE_NAME + " values(" + i + ",'han+" + i + "'," + i + (Math.random() * 100) + ")";
                    DbManger.execSQL(database, insertSql);
                }

                database.close();
                break;

            case R.id.btn_modify:
                database = helper.getWritableDatabase();
                String upDateSql = "update "
                        + Constants.TABLE_NAME + " set "
                        + Constants.NAME + "= 'pan' where "
                        + Constants._ID + "=1";

                DbManger.execSQL(database, upDateSql);
                database.close();

                break;

            case R.id.btn_del:
                database = helper.getWritableDatabase();
                String delSql = "delete from "
                        + Constants.TABLE_NAME + " where "
                        + Constants._ID + "=1";

                DbManger.execSQL(database, delSql);
                database.close();

                break;

            case R.id.btn_select:
                database = helper.getWritableDatabase();
                String sSql = "select * from " + Constants.TABLE_NAME;

                Cursor cursor = DbManger.selectDataBySql(database, sSql, null);
                List<Person> list = DbManger.cursorToList(cursor);
                for (Person p : list) {
                    Log.e(TAG, p.toString());
                }

                database.close();

                break;

            case R.id.btn_insert_api:
                database = helper.getWritableDatabase();

                for (int i = 51; i <= 120; i++) {
                    ContentValues values = new ContentValues();
                    values.put(Constants._ID, i);
                    values.put(Constants.NAME, "Alice" +i+ (int)(Math.random()*10*i));
                    values.put(Constants.AGE, (int)(Math.random()*10*i));
                    values.put(Constants.DES,(i+(int) (Math.random()*100*i)));

                    database.insert(Constants.TABLE_NAME, null, values);
                }

//
//                if (result>0){
                Toast.makeText(SQLiteActivity.this, " insert success ", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(SQLiteActivity.this," insert fail ",Toast.LENGTH_SHORT).show();
//                }

                database.close();

                break;

            case R.id.btn_update_api:
                database = helper.getWritableDatabase();


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SQLiteDatabase database= helper.getWritableDatabase();

                        for (int i = 1; i <= 120; i++) {
                            ContentValues valuesUpdate = new ContentValues();

                            if (i%3==0){
                                valuesUpdate.put(Constants.NAME, "Bob" +i+ (int)(Math.random()*10*i));
                            }else {
                                valuesUpdate.put(Constants.NAME, "Alice" +i+ (int)(Math.random()*10*i));
                            }

                            valuesUpdate.put(Constants.AGE, (int)(Math.random()*10*i));
                            valuesUpdate.put(Constants.DES,(i+(int) (Math.random()*100*i)));

                            database.update(Constants.TABLE_NAME,valuesUpdate, Constants._ID + "="+i, null);
//
//                            if (i==120){
////                                isCount = true;
////                                startAdapterList();
//
//                                Toast.makeText(SQLiteActivity.this, " update success ", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }
                }).start();


//                ContentValues values1 = new ContentValues();
//                int count = database.update(Constants.TABLE_NAME, values1, Constants._ID + "=3", null);
//                long result1 = database.insert(Constants.TABLE_NAME,null,values1);

//                if (count > 0) {
//                    Toast.makeText(SQLiteActivity.this, " update success ", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(SQLiteActivity.this, " update fail ", Toast.LENGTH_SHORT).show();
//                }

//                if (isCount){
//                    Toast.makeText(SQLiteActivity.this, " update success ", Toast.LENGTH_SHORT).show();
//                }

                database.close();

                break;

            case R.id.btn_select_api:
                database = helper.getWritableDatabase();
                Cursor cursor1;
                List<Person> list1;

                cursor1 = database.query(Constants.TABLE_NAME,
                        null,
                        Constants._ID + ">?",
                        new String[]{"10"},
                        null,
                        null,
                        Constants._ID + " desc");

                list1 = DbManger.cursorToList(cursor1);
                for (Person p : list1) {
                    Log.e(TAG,p.toString());
                }

                database.close();
                break;

            case R.id.btn_list:
                database = helper.getWritableDatabase();
                Cursor cursor2;

//                cursor2 = database.query(Constants.TABLE_NAME,
//                        null,
//                        Constants._ID + ">?",
//                        new String[]{"10"},
//                        null,
//                        null,
//                        Constants._ID + " desc");

                cursor2 = database.rawQuery("select * from "+Constants.TABLE_NAME,null);
                startAdapterList(cursor2);

                database.close();


                break;

            default:
                break;
        }
    }
}
