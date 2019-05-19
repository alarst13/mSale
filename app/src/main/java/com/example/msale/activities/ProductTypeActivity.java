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

            case "product":
                mSale.mkAllList();
                break;
            case "book":
                Book.mkShowList();
                break;
            case "adventure":
                Adventure.mkShowList();
                break;
            case "love affair":
                LoveAffair.mkShowList();
                break;
            case "Science fiction":
                ScienceFiction.mkShowList();
                break;
            case "car":
                Car.mkShowList();
                break;
            case "limousine":
                Limousine.mkShowList();
                break;
            case "sedan":
                Sedan.mkShowList();
                break;
            case "electronic":
                Electronic.mkShowList();
                break;
            case "cellphone":
                CellPhone.mkShowList();
                break;
            case "laptop":
                Laptop.mkShowList();
                break;
            case "tablet":
                Tablet.mkShowList();
                break;

        }
        finish();
    }
}
