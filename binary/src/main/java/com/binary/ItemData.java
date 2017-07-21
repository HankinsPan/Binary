package com.binary;

/**
 * Created by bestotem on 2017/7/21.
 */

public class ItemData {
    public String title;
    public String content;
    public String decs;
    public String tel;


    public ItemData(String title, String content, String decs, String tel) {
        this.title = title;
        this.content = content;
        this.decs = decs;
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
