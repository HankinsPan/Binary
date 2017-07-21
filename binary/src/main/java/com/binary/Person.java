package com.binary;

/**
 * Created by bestotem on 2017/7/20.
 */

public class Person {
    private String id;
    private String name;
    private String age;
    private String des;

    private boolean isCheck;


    public Person(String id, String name, String age, String des) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.des = des;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
