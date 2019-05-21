package com.example.msale.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.msale.R;
import com.example.msale.classes.Products.Books.Adventure;
import com.example.msale.classes.Products.Books.Book;
import com.example.msale.classes.Products.Books.LoveAffair;
import com.example.msale.classes.Products.Books.ScienceFiction;
import com.example.msale.classes.Products.Cars.Car;
import com.example.msale.classes.Products.Cars.Limousine;
import com.example.msale.classes.Products.Cars.Sedan;
import com.example.msale.classes.Products.Electronics.CellPhone;
import com.example.msale.classes.Products.Electronics.Electronic;
import com.example.msale.classes.Products.Electronics.Laptop;
import com.example.msale.classes.Products.Electronics.Tablet;
import com.example.msale.classes.mSale;

public class ProductTypeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type);
    }

    public void clickModelButton(View view) {

        switch (view.getTag().toString()) {

            case "products":
                mSale.mkAllList();
                break;
            case "books":
                Book.mkShowList();
                break;
            case "adventure_books":
                Adventure.mkShowList();
                break;
            case "love_affair_books_layout":
                LoveAffair.mkShowList();
                break;
            case "science_fiction_books_layout":
                ScienceFiction.mkShowList();
                break;
            case "cars_layout":
                Car.mkShowList();
                break;
            case "limousin_cars_layout":
                Limousine.mkShowList();
                break;
            case "sedan_cars_layout":
                Sedan.mkShowList();
                break;
            case "electronics_layout":
                Electronic.mkShowList();
                break;
            case "cellphones_img":
                CellPhone.mkShowList();
                break;
            case "laptops":
                Laptop.mkShowList();
                break;
            case "tablets_layout":
                Tablet.mkShowList();
                break;

        }
        finish();
    }
}
