package com.binary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.binary.bean.ItemData;

import java.util.List;

/**
 * Created by bestotem on 2017/7/21.
 */

public class FiveAdapter extends BaseAdapter {

    private List<ItemData> datas;
    private LayoutInflater mInflater;

    public FiveAdapter(Context context, List<ItemData> datas) {

        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mViewHolder mHolder;

        if (convertView == null) {
            mHolder = new mViewHolder();

            convertView = mInflater.inflate(R.layout.five_item, null);

            mHolder.itemTitle = (TextView) convertView.findViewById(R.id.tv_title);
            mHolder.itemContent = (TextView) convertView.findViewById(R.id.tv_content);
            mHolder.itemTel = (TextView) convertView.findViewById(R.id.tv_tel);
            mHolder.itemDes = (TextView) convertView.findViewById(R.id.tv_desc);

            convertView.setTag(mHolder);


        } else {
            mHolder = (mViewHolder) convertView.getTag();
        }

        ItemData itemData = datas.get(position);
        mHolder.itemTitle.setText(itemData.getTitle());
        mHolder.itemContent.setText(itemData.getContent());
        mHolder.itemDes.setText(itemData.getDecs());
        mHolder.itemTel.setText(itemData.getTel());

        return convertView;
    }

    class mViewHolder {
        public TextView itemTitle;
        public TextView itemContent;
        public TextView itemDes;
        public TextView itemTel;

    }
}
