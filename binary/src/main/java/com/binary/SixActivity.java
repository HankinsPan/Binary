package com.binary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/22.
 */

public class SixActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    private SimpeAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        initDatas();
        initView();
    }


    private void initDatas() {
        mDatas = new ArrayList<String>();

        for (int i = 'A'; i <='z'; i++) {
            mDatas.add(""+(char)i);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.re_cyc);
        adapter = new SimpeAdapter(this,mDatas);
        mRecyclerView.setAdapter(adapter);


        LinearLayoutManager lp = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(lp);

    }
}
