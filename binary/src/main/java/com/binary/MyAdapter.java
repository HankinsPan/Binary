package com.binary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.binary.bean.Person;

import java.util.List;

/**
 * Created by bestotem on 2017/7/21.
 */

public class MyAdapter extends BaseAdapter {

    private List<Person> persons;
    private LayoutInflater mInflater;


    public MyAdapter(Context context, List<Person> persons) {
        this.persons = persons;
        mInflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int i) {
        return persons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_list, null);

            holder.itemId = (TextView) view.findViewById(R.id.tv_id);
            holder.itemName = (TextView) view.findViewById(R.id.tv_name);
            holder.itemAge = (TextView) view.findViewById(R.id.tv_age);
            holder.itemDes = (TextView) view.findViewById(R.id.tv_tel);

            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }

        Person person = persons.get(position);
        holder.itemId.setText(person.getId());
        holder.itemName.setText(person.getName());
        holder.itemAge.setText(person.getAge());
        holder.itemDes.setText(person.getDes());


        return view;
    }


    class ViewHolder {
        public TextView itemId;
        public TextView itemName;
        public TextView itemAge;
        public TextView itemDes;

    }
}
