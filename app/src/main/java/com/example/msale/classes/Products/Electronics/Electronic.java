package com.example.msale.classes.Products.Electronics;

import com.example.msale.classes.Products.Product;
import com.example.msale.classes.mSale;

public class Electronic extends Product {

    public Electronic(String name, String factory, long price, double off, String explanation, int number) {
        super(name, factory, price, off, explanation, number);
    }

    public static void mkShowList(){
        mSale.productsForShow.clear();
        for (Product p:mSale.products) {
            if(p.getType().equals("Tablet") || p.getType().equals("Laptop") || p.getType().equals("Cell phone")){
                mSale.productsForShow.addLast(p);
            }
        }
    }
}
