package com.binary.compent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary.R;

/**
 * Created by bestotem on 2017/7/24.
 */

public class TypeThreeViewHolder extends TypeBindViewHolder {
    public ImageView avatar;
    public TextView name;
    public TextView content;
    public ImageView contentColor;


    public TypeThreeViewHolder(View itemView) {
        super(itemView);

        avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        name = (TextView) itemView.findViewById(R.id.tv_cyc_name);
        content = (TextView) itemView.findViewById(R.id.tv_cyc_content);
        contentColor = (ImageView) itemView.findViewById(R.id.iv_cyc_content);

    }

    @Override
    public void bindHolder(DataModal modal) {
        avatar.setBackgroundResource(modal.avatarColor);
        name.setText(modal.name);
        content.setText(modal.content);
        contentColor.setBackgroundResource(modal.contentColor);
    }

}
