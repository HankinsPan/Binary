package com.binary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/22.
 */

public class FullAdapter extends RecyclerView.Adapter<FullViewHolder> {
    private Context mContext;
    private List<String> datas;
    private LayoutInflater mInflater;

    private List<Integer> mHeight;



    public FullAdapter(Context context,List<String> datas){
        this.mContext = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);


        mHeight = new ArrayList<Integer>();
        for (int i = 0; i < datas.size(); i++) {
            mHeight.add(200+(int) (Math.random()*(400+i*20)));
        }
    }

    @Override
    public FullViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyc_item,parent,false);
        FullViewHolder holder = new FullViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FullViewHolder holder, int position) {
        LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeight.get(position);

        holder.itemView.setLayoutParams(layoutParams);
        holder.tvTxt.setText(datas.get(position));

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }
}

class FullViewHolder extends RecyclerView.ViewHolder{
    TextView tvTxt;

    public FullViewHolder(View itemView) {
        super(itemView);

        tvTxt = (TextView) itemView.findViewById(R.id.tv_txt);
    }
}
