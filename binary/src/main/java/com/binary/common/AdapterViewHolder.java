package com.binary.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binary.Person;
import com.binary.R;

import java.util.List;

/**
 * Created by bestotem on 2017/7/21.
 */

public class AdapterViewHolder extends ConmentAdapter<Person> {

    public AdapterViewHolder(Context mContext, List<Person> mDatas) {
        super(mContext,mDatas);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, viewGroup, R.layout.item_list, position);



        Person person = mDatas.get(position);

        TextView tvId = holder.getView(R.id.tv_id);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvAge = holder.getView(R.id.tv_age);
        TextView tvTel = holder.getView(R.id.tv_tel);

        tvId.setText(person.getId());
        tvName.setText(person.getName());
        tvAge.setText(person.getAge());
        tvTel.setText(person.getDes());




        return holder.getmConvertView();
    }
}
