package com.example.msale.classes.Products.Electronics;

import com.example.msale.classes.Products.Product;
import com.example.msale.classes.mSale;

public class Tablet extends Electronic {

    public Tablet(String name, String factory, long price, double off, String explanation, int number) {
        super(name, factory, price, off, explanation, number);
    }

    public static void mkShowList(){
        mSale.productsForShow.clear();
        for (Product p:mSale.products) {
            if(p instanceof Tablet){
                mSale.productsForShow.addLast(p);
            }
        }
    }
}
