package com.binary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.binary.FiveAdapter;
import com.binary.bean.ItemData;
import com.binary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/21.
 */

public class FiveActivity extends AppCompatActivity {
    public static final String TAG = "FiveActivity";

    private List<ItemData> datas = new ArrayList<>();
    private ListView lvItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        initData();
        initView();

    }


    private void initData() {
        ItemData itemData;
        for (int i = 1; i <= 100; i++) {

            String title = "This is " + i;
            String content = "this is a num " + i + " you can see";
            String des = "maybe you can get " + Math.random() * 100 * i + " num";
            String tel = "tel : +86 " + Math.random() * 50 * i + "-1";

            itemData = new ItemData(title, content, des, tel);
            datas.add(itemData);

        }
    }

    private void initView() {
        lvItem = (ListView) findViewById(R.id.lv_five);
        lvItem.setAdapter(new FiveAdapter(this, datas));
    }
}
