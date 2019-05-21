package com.example.msale.classes.Products;

import com.example.msale.classes.History;
import com.example.msale.classes.Users.User;
import com.example.msale.classes.mSale;

public class Product {
    private String type;
    private String name;
    private String factory;
    private long price;
    private double off;
    private boolean isHaveOff = false;
    private String explanation;
    private int number;
    private int numberForBuy;
    private String picID;

    public Product(String name, String factory, long price, double off, String explanation, int number) {
        this.name = name;
        this.factory = factory;
        this.price = price;
        this.off = off;
        this.explanation = explanation;
        this.number = number;
        if (this.off >= 1){
            this.isHaveOff = false;
        }else {
            this.isHaveOff = true;
        }
        this.numberForBuy = 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHaveOff(boolean haveOff) {
        isHaveOff = haveOff;
    }

    public String getPicID() {
        return picID;
    }

    public void setPicID(String picID) {
        this.picID = picID;
    }

    public boolean isHaveOff() {
        return isHaveOff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public double getOff() {
        return off;
    }

    public void setOff(double off) {
        this.off = off;
        if (this.off < 1) {
            this.isHaveOff = true;
        } else {
            this.isHaveOff = false;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getNumberForBuy() {
        return numberForBuy;
    }

    public void setNumberForBuy(int numberForBuy) {
        this.numberForBuy = numberForBuy;
    }

    public long getFinalPrice() {
        return (long) (this.price * this.off);
    }

    public boolean buy(User user) {
        if (user.getCash() < this.getFinalPrice() * this.numberForBuy || this.numberForBuy > this.number) {
            return false;
        }
        this.number = this.number - this.numberForBuy;
        user.setCash(user.getCash() - (this.numberForBuy * this.getFinalPrice()));
        //add date
        mSale.history.addLast(new History(mSale.user.getUsername() + " " + this.numberForBuy, this.name, this.getFinalPrice(), null, this.getClass().toString()));
        this.numberForBuy = 0;
        return true;
    }

    public static void mkShowList() {
        mSale.productsForShow.clear();
        for (Product p : mSale.products) {
            if (p instanceof Product) {
                mSale.productsForShow.addLast(p);
            }
        }
    }

    public boolean addToCart(int number) {
        if (number <= 0 || this.number < this.numberForBuy + number) {
            return false;
        }
        this.numberForBuy = this.numberForBuy + number;
        if (!mSale.Cart.contains(this)) {
            mSale.Cart.addLast(this);
        }
        return true;
    }
}
