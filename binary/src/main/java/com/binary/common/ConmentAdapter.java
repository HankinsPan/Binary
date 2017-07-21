package com.binary.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by bestotem on 2017/7/21.
 */

public abstract class ConmentAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    ConmentAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, viewGroup, layoutId, position);

        convert(holder, getItem(position));
        return holder.getmConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);
}
