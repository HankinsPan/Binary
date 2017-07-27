package com.binary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binary.R;
import com.binary.bean.Person;

import java.util.List;

/**
 * Created by bestotem on 2017/7/27.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TLineViewHolder> {
    private List<Person> datas;
    private LayoutInflater mInflater;

    public TimelineAdapter(Context context, List<Person> datas) {
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public TLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_shop, parent, false);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        params.topMargin = 1;
        view.setLayoutParams(params);
        TLineViewHolder holder = new TLineViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(TLineViewHolder holder, int position) {
        Person person = datas.get(position);

        holder.tId.setText(person.getId() + "");
        holder.tName.setText(person.getName());
        holder.tAge.setText(person.getAge()+ "");
        holder.tDes.setText(person.getDes());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}

class TLineViewHolder extends RecyclerView.ViewHolder {
    TextView tId;
    TextView tName;
    TextView tAge;
    TextView tDes;

    public TLineViewHolder(View itemView) {
        super(itemView);

        tId = (TextView) itemView.findViewById(R.id.tv_T_id);
        tName = (TextView) itemView.findViewById(R.id.tv_T_name);
        tAge = (TextView) itemView.findViewById(R.id.tv_T_age);
        tDes = (TextView) itemView.findViewById(R.id.tv_T_des);
    }


}
