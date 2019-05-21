package com.example.msale.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.msale.MapsActivity;
import com.example.msale.R;
import com.example.msale.classes.Message;
import com.example.msale.classes.Products.Product;
import com.example.msale.classes.ProductsOnSaleListAdapter;
import com.example.msale.classes.Users.User;
import com.example.msale.classes.mSale;

public class MyCartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView check , corse ;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        user = (User) getIntent().getSerializableExtra("user_second");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Cart");

        check = findViewById(R.id.cart_check_img_btn);
        corse = findViewById(R.id.cart_cross_ims_btn);
        recyclerView = findViewById(R.id.cart_recycler_view);
        ProductsOnSaleListAdapter productsOnSaleListAdapter = new ProductsOnSaleListAdapter(mSale.Cart , this);
        recyclerView.setAdapter(productsOnSaleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    boolean b = mSale.buy(user);
                    if (b) {
                        Intent i = new Intent(MyCartActivity.this , MapsActivity.class);
                        Message.message(getApplicationContext(), "it`s done");
                        startActivity(i);
                    }else {
                        Message.message(getApplicationContext() , "it`s not done");
                    }
                }else {
                    Message.message(getApplicationContext() , "please sign in first");
                }
            }
        });
        corse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Product p : mSale.Cart){
                    p.setNumberForBuy(0);
                }
                mSale.Cart.clear();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
