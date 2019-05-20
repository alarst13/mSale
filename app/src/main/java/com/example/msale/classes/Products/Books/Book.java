package com.example.msale.classes.Products.Books;

import com.example.msale.classes.Products.Product;
import com.example.msale.classes.mSale;

public class Book extends Product {

    public Book(String name, String factory, long price, double off, String description, int number) {
        super(name, factory, price, off, description, number);
    }

    public static void mkShowList(){
        mSale.productsForShow.clear();
        for (Product p : mSale.products) {
            if (p.getType().equals("Adventure") || p.getType().equals("Love affair") || p.getType().equals("Science fiction")){
                mSale.productsForShow.addLast(p);
            }
        }
    }
}
