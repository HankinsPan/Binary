package com.binary.compent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.binary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestotem on 2017/7/24.
 */

public class RecycAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<DataModal> datas = new ArrayList<>();


    public RecycAdapter(Context context ,List<DataModal> datas) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case DataModal.TYPE_ONE:

                return new TypeOneViewHolder(mInflater.inflate(R.layout.recyc_one,parent,false));

            case DataModal.TYPE_TWO:

                return new TypeTwoViewHolder(mInflater.inflate(R.layout.recyc_two,parent,false));

            case DataModal.TYPE_THREE:

                return new TypeThreeViewHolder(mInflater.inflate(R.layout.recyc_three,parent,false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((TypeBindViewHolder)holder).bindHolder(datas.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
