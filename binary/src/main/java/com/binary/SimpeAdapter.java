package com.binary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bestotem on 2017/7/22.
 */

public class SimpeAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private List<String> datas;
    private LayoutInflater mInflater;

    public SimpeAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onLongItemClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void addData(int position){

        datas.add(position,"insert one");
        notifyItemChanged(position);
    }

    public void delData(int position){
        datas.remove(position);
        notifyItemChanged(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.recyc_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvTxt.setText(datas.get(position));

        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onLongItemClick(view,position);
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}


class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tvTxt;

    public MyViewHolder(View view) {
        super(view);

        tvTxt = (TextView) view.findViewById(R.id.tv_txt);
    }
}
