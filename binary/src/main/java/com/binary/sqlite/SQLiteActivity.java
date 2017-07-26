package com.binary.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.binary.R;
import com.binary.manger.DbManger;
import com.binary.util.Constants;

/**
 * Created by bestotem on 2017/7/26.
 */

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "SQLiteActivity";

    private Button btnC, btnI, btnM,btnD,btnS;
    private Button btninsertApi,btnUpdateApi;

    private MySqliteHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        initView();
        addLitener();
        initData();

    }

    private void initView() {
        btnC = (Button) findViewById(R.id.btn_create);
        btnI = (Button) findViewById(R.id.btn_insert);
        btnM = (Button) findViewById(R.id.btn_modify);
        btnD = (Button) findViewById(R.id.btn_del);
        btnS = (Button) findViewById(R.id.btn_select);

        btninsertApi = (Button) findViewById(R.id.btn_insert_api);
        btnUpdateApi = (Button) findViewById(R.id.btn_update_api);

    }

    private void addLitener() {
        btnC.setOnClickListener(this);
        btnI.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnS.setOnClickListener(this);

        btninsertApi.setOnClickListener(this);
        btnUpdateApi.setOnClickListener(this);

    }

    private void initData() {
        helper = DbManger.getInstance(this);
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
                String insertSql = "insert into "
                        + Constants.TABLE_NAME+" values(1,'han',23)";

                DbManger.execSQL(database,insertSql);
                database.close();
                break;

            case R.id.btn_modify:
                database = helper.getWritableDatabase();
                String upDateSql = "update "
                        + Constants.TABLE_NAME+" set "
                        +Constants.NAME+"= 'pan' where "
                        +Constants._ID+"=1";

                DbManger.execSQL(database,upDateSql);
                database.close();

                break;

            case R.id.btn_del:
                database = helper.getWritableDatabase();
                String delSql = "delete from "
                        + Constants.TABLE_NAME+" where "
                        +Constants._ID+"=1";

                DbManger.execSQL(database,delSql);
                database.close();

                break;

            case R.id.btn_select:

                break;

            case R.id.btn_insert_api:
                database = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Constants._ID,3);
                values.put(Constants.NAME,"Alice");
                values.put(Constants.AGE,26);

                long result = database.insert(Constants.TABLE_NAME,null,values);

                if (result>0){
                    Toast.makeText(SQLiteActivity.this," insert success ",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SQLiteActivity.this," insert fail ",Toast.LENGTH_SHORT).show();
                }

                database.close();

                break;

            case R.id.btn_update_api:
                database = helper.getWritableDatabase();

                ContentValues values1 = new ContentValues();

                values1.put(Constants.NAME,"bob");
                values1.put(Constants.AGE,28);

                int count = database.update(Constants.TABLE_NAME,values1,Constants._ID+"=3",null);
//                long result1 = database.insert(Constants.TABLE_NAME,null,values1);

                if (count>0){
                    Toast.makeText(SQLiteActivity.this," update success ",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SQLiteActivity.this," update fail ",Toast.LENGTH_SHORT).show();
                }

                database.close();

                break;

            default:
                break;
        }
    }
}
