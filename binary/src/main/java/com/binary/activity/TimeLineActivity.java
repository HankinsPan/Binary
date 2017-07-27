package com.binary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.binary.R;
import com.binary.adapter.TimelineAdapter;
import com.binary.bean.Person;
import com.binary.view.TimeDiverItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/27.
 */

public class TimeLineActivity extends AppCompatActivity {
    public static final String TAG = "TimeLineActivity";

    private RecyclerView reTimeLine;
    private List<Person> datas = new ArrayList<>();
    private TimelineAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);

        initView();
        initDatas();

    }

    private void initDatas() {

        for (int i = 1; i <= 120; i++) {
            int id = (int) (i * Math.random() * 10) + i;

            String name = "hoi ";
            if (i % 3 == 0) {
                name += "jone" + i;
            } else {
                name += "Steven" + i;
            }
            int age = (int) (i * Math.random() * 100) - i;
            String des = "nothing to say " + i;

            Person person = new Person(id, name,age,des);

            datas.add(person);

        }
    }

    private void initView() {
        reTimeLine = (RecyclerView) findViewById(R.id.recyc_timeLine);
        adapter = new TimelineAdapter(this,datas);
        reTimeLine.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        reTimeLine.setLayoutManager(manager);
        reTimeLine.addItemDecoration(new TimeDiverItemDecoration(this));

    }
}
