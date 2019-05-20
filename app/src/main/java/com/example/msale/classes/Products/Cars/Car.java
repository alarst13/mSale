package com.example.msale.classes.Products.Cars;

import com.example.msale.classes.Products.Product;
import com.example.msale.classes.mSale;

public class Car extends Product {
    public Car(String name, String factory, long price, double off, String explanation, int number) {
        super(name, factory, price, off, explanation, number);
    }

    public static void mkShowList(){
        mSale.productsForShow.clear();
        for (Product p: mSale.products) {
            if(p.getType().equals("Limousine") || p.getType().equals("Sedan") ){
                mSale.productsForShow.addLast(p);
            }
        }
    }
}
