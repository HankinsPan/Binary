package com.binary.compent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.binary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/24.
 */

public class RecycActivity extends AppCompatActivity {

    public static final String TAG = "RecycActivity";

    private RecyclerView mRecyc;
    private RecycAdapter adapter;
    List<DataModal> mDatas = new ArrayList<>();

    int colors[] = {android.R.color.holo_blue_light,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_light};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyc);


        initData();
    }


    private void initData() {

        DataModal modal;

        for (int i = 0; i < 50; i++) {

            int type = (int) ((Math.random() * 3) + 1);
            int avatarColor = colors[type - 1];
            String name = "TYpe" + type + " " + i;
            String content = "Content " + type * i;
            int contentColor = colors[(type + 1) % 3];

            modal = new DataModal(type,avatarColor,name,content,contentColor);
            mDatas.add(modal);
        }

        initView();
    }


    private void initView() {
        mRecyc = (RecyclerView) findViewById(R.id.recyc_one);

        adapter = new RecycAdapter(this,mDatas);
        mRecyc.setAdapter(adapter);

        mRecyc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

}
