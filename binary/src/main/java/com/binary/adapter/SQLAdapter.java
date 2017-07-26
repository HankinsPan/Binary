package com.binary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.binary.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/26.
 */

public class SQLAdapter extends BaseAdapter {

    private List<Person> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public SQLAdapter(Context context,List<Person> list){
        this.context =context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
