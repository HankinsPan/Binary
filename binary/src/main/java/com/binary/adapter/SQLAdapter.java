package com.binary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.binary.R;
import com.binary.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/26.
 */

public class SQLAdapter extends BaseAdapter {

    private List<Person> list = new ArrayList<>();
    private LayoutInflater mInflater;

    public SQLAdapter(Context context,List<Person> list){
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView ==null){

            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.sql_item,null);
            holder.tvSqlId = (TextView) convertView.findViewById(R.id.tv_sql_id);
            holder.tvSqlNmae = (TextView) convertView.findViewById(R.id.tv_sql_name);
            holder.tvSqlAge = (TextView) convertView.findViewById(R.id.tv_sql_age);
            holder.tvSqlDes = (TextView) convertView.findViewById(R.id.tv_sql_des);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Person person = list.get(position);

        holder.tvSqlId.setText(person.getId()+"");
        holder.tvSqlNmae.setText(person.getName());
        holder.tvSqlAge.setText(person.getAge()+"");
        holder.tvSqlDes.setText(person.getDes());

        return convertView;
    }

    public class ViewHolder{
        TextView tvSqlId;
        TextView tvSqlNmae;
        TextView tvSqlAge;
        TextView tvSqlDes;

    }

}
