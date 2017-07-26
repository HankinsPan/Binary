package com.binary.common;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.binary.bean.Person;
import com.binary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/21.
 */

public class AdapterViewHolder extends ConmentAdapter<Person> {

    private List<Integer> mPos = new ArrayList<>();


    public AdapterViewHolder(Context mContext, List<Person> mDatas) {
        super(mContext, mDatas,R.layout.item_list);
    }


//    @Override
//    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        final ViewHolder holder = ViewHolder.get(mContext, convertView, viewGroup, R.layout.item_list, position);
//
//        final CheckBox box = holder.getView(R.id.ck_box);
//        box.setChecked(false);
//
//        if (mPos.contains(holder.getmPosition())){
//            box.setChecked(true);
//        }
//
//
//        box.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (box.isChecked()) {
//                    mPos.add(holder.getmPosition());
//                } else {
//                    mPos.remove((Integer) holder.getmPosition());
//                }
//
//            }
//        });
//
//
//        return holder.getmConvertView();
//    }

    @Override
    public void convert(final ViewHolder holder, Person person) {
        holder.setText(R.id.tv_id,person.getId()+"")
                .setText(R.id.tv_name,person.getName())
                .setText(R.id.tv_age,person.getAge()+"")
                .setText(R.id.tv_tel,person.getDes());

        final CheckBox box = holder.getView(R.id.ck_box);
        box.setChecked(false);

        if (mPos.contains(holder.getmPosition())){
            box.setChecked(true);
        }


        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (box.isChecked()) {
                    mPos.add(holder.getmPosition());
                } else {
                    mPos.remove((Integer) holder.getmPosition());
                }

            }
        });
    }
}
