package com.binary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.binary.R;
import com.binary.SimpeAdapter;

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
        mDatas = new ArrayList<>();

        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.re_cyc);
        adapter = new SimpeAdapter(this, mDatas);
        mRecyclerView.setAdapter(adapter);


        LinearLayoutManager lp = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(lp);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setmOnItemClickListener(new SimpeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SixActivity.this,"click -> "+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                mDatas.remove(position);
                adapter.notifyItemChanged(position);
                Toast.makeText(SixActivity.this,"position "+position+" be remove",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_listView:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

                break;

            case R.id.action_gridView:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

                break;

            case R.id.action_hor:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.HORIZONTAL));

                break;

            case R.id.action_full:
                Intent intent = new Intent(SixActivity.this,FullActivity.class);
                startActivity(intent);
                break;

            case R.id.action_add:
                adapter.addData(1);

                break;

            case R.id.action_del:
                adapter.delData(1);

                break;

            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}




