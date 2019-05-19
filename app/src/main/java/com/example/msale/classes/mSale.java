package com.example.msale.classes;

import android.content.Context;

import com.example.msale.classes.Products.Product;
import com.example.msale.classes.Users.Users;

import java.util.LinkedList;

public class mSale {
    private mSale() {
    }

    public static Users user;
    public static LinkedList <Users> users = new LinkedList<>();
    public static LinkedList<History> history = new LinkedList<>();
    public static LinkedList <Product> products = new LinkedList<>();
    public static LinkedList<Product> Cart = new LinkedList<>();
    public static LinkedList<Product> productsForShow = new LinkedList<>();
    public int a;

    public static Users mkUser(String string) {
        String[] strings = string.split(" ");
        return new Users(strings[0], strings[1], strings[2], strings[3], Long.parseLong(strings[4]), Boolean.parseBoolean(strings[5]));
    }

    public static void getFromDatabase(Context context) {
        users.clear();
        UsersDatabase dbAdapter = new UsersDatabase(context);
        String string = dbAdapter.getData();
        String[] strings = string.split("\n");
        for (int i = 0; i < strings.length; i++) {
            users.addLast(mkUser(strings[i]));
        }
    }

    public static boolean checkingUsername (String username){
        for (Users user : users) {
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkPhoneNumber(String phoneNumber){
        for (Users user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }

    public static Users getUsers(String username){
        for (Users user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
    public static void mkShowListWithOff(){
        productsForShow.clear();
        for (Product p : products) {
            if(p.isHaveOff()){
                productsForShow.addLast(p);
            }
        }
    }

    public static Users getUser() {
        return user;
    }

    public static void setUser(Users user) {
        mSale.user = user;
    }
}
