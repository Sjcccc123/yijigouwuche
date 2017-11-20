package com.bwie.shopcar;


public class ItemBean {

    boolean check;
    String name;
    int price;
    int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ItemBean(int num, boolean check, String name, int price) {
        this.num = num;
        this.check = check;
        this.name = name;
        this.price = price;
    }

    public ItemBean() {
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
