package com.binary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/22.
 */

public class FullActivity extends AppCompatActivity {

    private RecyclerView mRcycView;
    private List<String> mDatas = new ArrayList<>();
    private FullAdapter fullAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        initDatas();
        initView();
    }

    private void initView() {
        mRcycView = (RecyclerView) findViewById(R.id.re_cyc);

        fullAdapter = new FullAdapter(this,mDatas);
        mRcycView.setAdapter(fullAdapter);

        mRcycView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    private void initDatas() {
        mDatas = new ArrayList<>();

        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add(""+(char)i);
        }
    }



}
