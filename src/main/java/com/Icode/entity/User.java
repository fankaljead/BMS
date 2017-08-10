package com.Icode.entity;

/**
 * Created by Icode on 2017/7/29.
 * descript:Uesr人员
 */
public class User extends Entity {
    private String ID;
    private String name;
    private int age;
    private short sex;
    private String phoneNumber;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public short getSex() {
        return sex;
    }

    public void setSex(short sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTableName() {
        return "User";
    }

    public String getPrimaryKey() {
        return "ID";
    }
}
