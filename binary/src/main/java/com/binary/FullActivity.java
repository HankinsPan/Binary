package com.binary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

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

        mRcycView.setItemAnimator(new DefaultItemAnimator());

        fullAdapter.setmOnItemClickListener(new SimpeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(FullActivity.this,"this is -> "+position+" item",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                mDatas.remove(position);
                fullAdapter.notifyItemChanged(position);
                Toast.makeText(FullActivity.this,position+" item be remove",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initDatas() {
        mDatas = new ArrayList<>();

        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add(""+(char)i);
        }
    }



}
