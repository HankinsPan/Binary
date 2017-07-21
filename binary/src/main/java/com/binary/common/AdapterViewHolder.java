package com.binary.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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



        final Person person = mDatas.get(position);

        TextView tvId = holder.getView(R.id.tv_id);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvAge = holder.getView(R.id.tv_age);
        TextView tvTel = holder.getView(R.id.tv_tel);

        final CheckBox box = holder.getView(R.id.ck_box);
        box.setChecked(person.isCheck());

        tvId.setText(person.getId());
        tvName.setText(person.getName());
        tvAge.setText(person.getAge());
        tvTel.setText(person.getDes());


        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.setCheck(box.isChecked());
            }
        });



        return holder.getmConvertView();
    }
}
