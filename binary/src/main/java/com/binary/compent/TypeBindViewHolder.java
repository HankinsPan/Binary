package com.binary.compent;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by bestotem on 2017/7/24.
 */

public abstract class TypeBindViewHolder extends RecyclerView.ViewHolder {


    public TypeBindViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(DataModal modal);

}
