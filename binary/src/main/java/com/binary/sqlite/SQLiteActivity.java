package com.binary.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.binary.R;
import com.binary.adapter.SQLAdapter;
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
    private List<Person> persons;


    //分页显示
    private int totalNum;
    private int pageSize= 18;
    private int pageNum;
    private int currentPage = 1;
    private boolean isDivPage;


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

//    private void startCursorAdapter(Cursor cursor2){
//        MyCursorAdapter adapter1 = new MyCursorAdapter(this,cursor2,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//        lvList.setAdapter(adapter1);
//    }


    private void pageForList(final SQLiteDatabase database){
        totalNum = DbManger.getData(database,Constants.TABLE_NAME);
        pageNum = (int) Math.ceil(totalNum/(double)pageSize); //根据总条目与每页的展示条目数 获得总页数 向上取整

        if (currentPage ==1){
            persons = DbManger.getListByCurrentPage(database,Constants.TABLE_NAME,currentPage,pageSize);
        }

        final SQLAdapter adapter = new SQLAdapter(this,persons);
        lvList.setAdapter(adapter);


        lvList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                    if (currentPage<pageNum){
                        currentPage++;
                        persons.addAll(DbManger.getListByCurrentPage(database,Constants.TABLE_NAME,currentPage,pageSize));
                        adapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isDivPage = ((firstVisibleItem+visibleItemCount)==totalItemCount);

            }
        });
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

                database.beginTransaction();

                for (int i = 51; i <= 150; i++) {
                    ContentValues values = new ContentValues();
                    values.put(Constants._ID, i);
                    values.put(Constants.NAME, "Alice" +i+ (int)(Math.random()*10*i));
                    values.put(Constants.AGE, (int)(Math.random()*10*i));
                    values.put(Constants.DES,(i+(int) (Math.random()*100*i)));

                    database.insert(Constants.TABLE_NAME, null, values);
                }
                database.setTransactionSuccessful();
                database.endTransaction();

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

                        database.beginTransaction();
                        for (int i = 1; i <= 150; i++) {
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

                        database.setTransactionSuccessful();
                        database.endTransaction();

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
//                Cursor cursor2;
//                cursor2 = database.query(Constants.TABLE_NAME,
//                        null,
//                        Constants._ID + ">?",
//                        new String[]{"10"},
//                        null,
//                        null,
//                        Constants._ID + " desc");

//                cursor2 = database.rawQuery("select * from "+Constants.TABLE_NAME,null);
//                startAdapterList(cursor2);

//                startCursorAdapter(cursor2);

                pageForList(database);
                database.close();


                break;

            default:
                break;
        }
    }


    public class MyCursorAdapter extends CursorAdapter{
        /**
         * 必须定义的构造方法
         * @param context
         * @param c
         * @param flags
         */
        public MyCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        /**
         * 创建适配器中每个Item对应的view对象
         * @param context 上下文对象
         * @param cursor 数据源游标对象
         * @param viewGroup 当前item的父布局
         * @return 每项item的View对象
         */
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View view = LayoutInflater.from(SQLiteActivity.this).inflate(R.layout.sql_item,null);


            return view;
        }

        /**
         * 通过newView（） 方法确定了每个item展示的view对象 在bindView（） 中对布局中的控件进行填充
         * @param view 由newView（） 返回的每项View对象
         * @param context 上下文
         * @param cursor 数据源cursor对象
         */
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tvSqlId = (TextView) findViewById(R.id.tv_sql_id);
            TextView tvSqlNmae= (TextView) findViewById(R.id.tv_sql_name);
            TextView tvSqlAge= (TextView) findViewById(R.id.tv_sql_age);
            TextView tvSqlDes= (TextView) findViewById(R.id.tv_sql_des);

            tvSqlId.setText(cursor.getInt(cursor.getColumnIndex(Constants._ID))+"");
            tvSqlNmae.setText(cursor.getString(cursor.getColumnIndex(Constants.NAME)));
            tvSqlAge.setText(cursor.getInt(cursor.getColumnIndex(Constants.AGE))+"");
            tvSqlDes.setText(cursor.getString(cursor.getColumnIndex(Constants.DES)));


        }
    }
}
