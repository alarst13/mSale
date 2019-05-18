package com.example.msale.classes;

public class History {
    private String userName;
    private String product;
    private long price;
    private String date;
    private String type;

    public History(String userName, String product, long price, String date, String type) {
        this.userName = userName;
        this.product = product;
        this.price = price;
        this.date = date;
        this.type = type;
        //write on data base
    }

    public String getUserName() {
        return userName;
    }

    public String getProduct() {
        return product;
    }

    public long getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
