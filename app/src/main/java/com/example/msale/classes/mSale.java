package com.example.msale.classes;

import android.content.Context;

import com.example.msale.classes.Products.Product;
import com.example.msale.classes.Users.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class mSale {
    private mSale() {
    }

    public static User user;
    public static LinkedList<User> users = new LinkedList<>();
    public static LinkedList<History> history = new LinkedList<>();
    public static LinkedList<Product> products = new LinkedList<>();
    public static LinkedList<Product> Cart = new LinkedList<>();
    public static LinkedList<Product> productsForShow = new LinkedList<>();
    private static boolean isFirstCallToDatabase;


    public static User mkUser(String string) {
        String[] strings = string.split(" ");
        return new User(strings[0], strings[1], strings[2], strings[3], Long.parseLong(strings[4]), Boolean.parseBoolean(strings[5]));
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

    public static boolean checkingUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public static User getUsers(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void getAllProductsFromDatabase(Context context) {
        if (isFirstCallToDatabase)
            return;

        ProductsDatabase productsDatabase = new ProductsDatabase(context);
        String s = productsDatabase.getData();

        if (!s.equals("")) {
            String[] productStrings = s.split("\n");

            for (String s1 : productStrings) {
                String[] fieldsStrings = s1.split(" ");

                Product product = new Product(fieldsStrings[2], fieldsStrings[3], Long.valueOf(fieldsStrings[4]), Double.valueOf(fieldsStrings[5]), fieldsStrings[6], Integer.valueOf(fieldsStrings[7]));
                product.setType(fieldsStrings[1]);
                product.setPicID(fieldsStrings[8]);
                products.add(product);
            }
        }
        isFirstCallToDatabase = true;
    }

    public static void mkShowListWithOff() {
        productsForShow.clear();
        for (Product p : products) {
            if (p.isHaveOff()) {
                productsForShow.addLast(p);
            }
        }
    }

    public static void mkAllList() {
        productsForShow.clear();
        for (Product p : products) {
            productsForShow.addLast(p);
        }
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        mSale.user = user;
    }

    public static boolean buy(User user) {
        long sum = 0;
        for (Product p : mSale.Cart) {
            sum = sum + (long) (p.getNumberForBuy() * p.getFinalPrice());
            if (p.getNumberForBuy() > p.getNumber()) {
                return false;
            }
        }
        if (sum > user.getCash()) {
            return false;
        }
        for (Product p : mSale.Cart) {
            if (!p.buy(user)) {
                return true;
            }
        }
        return true;
    }
}
