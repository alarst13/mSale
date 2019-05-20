package com.example.msale.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.msale.R;
import com.example.msale.classes.Message;
import com.example.msale.classes.Products.Product;
import com.example.msale.classes.Users.User;

public class ProductActivity extends AppCompatActivity {

    TextView name , description , price , offPrice ;
    RecyclerView recyclerView;
    Button add;

    Product product;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        name = findViewById(R.id.product_activity_name);
        description = findViewById(R.id.product_activity_description);
        price = findViewById(R.id.product_activity_price);
        offPrice = findViewById(R.id.product_activity_off_price);
        recyclerView = findViewById(R.id.product_activity_list_view);
        add = findViewById(R.id.product_activity_add_to_cart);

        Intent intent = this.getIntent();
        product = (Product) intent.getSerializableExtra("product");
        user = (User) intent.getSerializableExtra("user");

        if(product == null || user == null){
            finish();
        }

        name.setText(product.getName());
        description.setText(product.getExplanation());
        if (product.isHaveOff()){
            price.setText(String.valueOf(product.getPrice()));
            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            offPrice.setText(String.valueOf(product.getFinalPrice()));
        }
        else {
            price.setText(String.valueOf(product.getFinalPrice()));
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = product.addToCart(1);
                if(b){
                    Message.message(getApplicationContext() , "it add to cart");
                }
                else {
                    Message.message(getApplicationContext() , "it dose nt add to cart");
                }
            }
        });
    }
}
