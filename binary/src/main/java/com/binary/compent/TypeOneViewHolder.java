package com.binary.compent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary.R;

/**
 * Created by bestotem on 2017/7/24.
 */

public class TypeOneViewHolder extends TypeBindViewHolder {
    public ImageView avatar;
    public TextView name;


    public TypeOneViewHolder(View itemView) {
        super(itemView);

        avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        name = (TextView) itemView.findViewById(R.id.tv_cyc_name);

    }

    @Override
    public void bindHolder(DataModal modal){
        avatar.setBackgroundResource(modal.avatarColor);
        name.setText(modal.name);
    }

}
