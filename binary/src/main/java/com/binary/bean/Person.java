package com.binary.bean;

/**
 * Created by bestotem on 2017/7/20.
 */

public class Person {
    private int id;
    private String name;
    private int age;
    private String des;

    private boolean isCheck;


    public Person(int id, String name, int age, String des) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.des = des;
    }

    @Override
    public String toString() {
        return "id= " + this.id + " name= " + this.name + " age= " + this.age + " des= " + this.des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
