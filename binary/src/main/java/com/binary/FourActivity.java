package com.binary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.binary.common.AdapterViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/20.
 */

public class FourActivity extends AppCompatActivity {
    public static final String TAG = "FourActivity";

    private ListView listView;
    private List<Person> mPerson = new ArrayList<>();
    private AdapterViewHolder mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        initData();
        initView();

    }


    private void initData() {
        Person person;
        for (int i = 0; i < 200; i++) {
            String id = i + "";
            String name = "hel" + i + " lo.";
            String age = "Age " + i;
            String des = "158 73" + Math.random() * 100 + "" + i + "2315";

            person = new Person(id, name, age, des);
            mPerson.add(person);

//            mAdapter = new AdapterViewHolder(this,mPerson);
        }

    }


    private void initView() {
        listView = (ListView) findViewById(R.id.lv_main);
//        listView.setAdapter(new MyAdapter(this,mPerson));
        listView.setAdapter(new AdapterViewHolder(this,mPerson));

    }

}
