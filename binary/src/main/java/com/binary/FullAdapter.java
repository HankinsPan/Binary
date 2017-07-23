package com.binary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/22.
 */

public class FullAdapter extends SimpeAdapter {
    private Context mContext;
    private List<String> datas;
    private LayoutInflater mInflater;

    private List<Integer> mHeight;


    public FullAdapter(Context context, List<String> datas) {
        super(context, datas);
        this.mContext = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);


        mHeight = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            mHeight.add(200 + (int) (Math.random() * (300 + i * 20)));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeight.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        holder.tvTxt.setText(datas.get(position));
        setUpItemEvent(holder);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
