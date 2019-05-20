package com.example.msale.classes.Products.Books;

import com.example.msale.classes.Products.Product;
import com.example.msale.classes.mSale;

public class ScienceFiction extends Book {

    public ScienceFiction(String name, String factory, long price, double off, String explanation, int number) {
        super(name, factory, price, off, explanation, number);
    }

    public static void mkShowList(){
        mSale.productsForShow.clear();
        for (Product p:mSale.products) {
            if(p.getType().equals("Science fiction")){
                mSale.productsForShow.addLast(p);
            }
        }
    }
}
