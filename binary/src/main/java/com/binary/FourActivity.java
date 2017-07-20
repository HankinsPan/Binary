package com.binary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/20.
 */

public class FourActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String TAG = "FourActivity";

    private ListView listView;
    private List<Person> mPerson = new ArrayList<Person>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        initView();
        initData();

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(new MyAdapter());

    }

    private void initData() {
        Person person;
        for (int i = 0; i < 200; i++) {
            String id = i + "";
            String name = "Hel " + i + " .lo..";
            String age = "Age " + i;
            String des = "158 73" + Math.random() * 100 + "" + i + "2315";

            person = new Person(id, name, age, des);
            mPerson.add(person);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mPerson.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View itemView = View.inflate(FourActivity.this,R.layout.item_list,null);
            TextView tId  = (TextView) itemView.findViewById(R.id.tv_id);
            TextView tName = (TextView) itemView.findViewById(R.id.tv_name);
            TextView tAge = (TextView) itemView.findViewById(R.id.tv_age);
            TextView tDes = (TextView) itemView.findViewById(R.id.tv_tel);

            Person person = mPerson.get(position);

            tId.setText(person.getId());
            tName.setText(person.getName());
            tAge.setText(person.getAge());
            tDes.setText(person.getDes());

            return itemView;
        }
    }

}
