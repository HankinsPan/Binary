package com.binary.compent;

/**
 * Created by bestotem on 2017/7/24.
 */

public class DataModal {


    public static final int TYPE_ONE = 1;

    public static final int TYPE_TWO = 2;

    public static final int TYPE_THREE = 3;


    public int type;

    public int avatarColor;

    public String name;

    public String content;

    public int contentColor;

    public DataModal(int type,
                     int avatarColor,
                     String name,
                     String content,
                     int contentColor) {
        this.type = type;
        this.avatarColor = avatarColor;
        this.name = name;
        this.content = content;
        this.contentColor = contentColor;

    }


}
