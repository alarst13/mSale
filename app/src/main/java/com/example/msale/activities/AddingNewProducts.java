package com.example.msale.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.msale.R;
import com.example.msale.classes.Message;
import com.example.msale.classes.Products.Product;
import com.example.msale.classes.ProductsDatabase;
import com.example.msale.classes.mSale;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddingNewProducts extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    private ImageView imageView;
    private Button button;
    private EditText productNameEditText, productPriceEditText, productOffPriceEditText, descriptionEditText, numberEditText, factoryEditText;
    private String picID;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_products);

        spinner = findViewById(R.id.adding_new_product_type_spinner);
        button = findViewById(R.id.adding_new_products_btn);
        productNameEditText = findViewById(R.id.adding_product_product_name);
        productPriceEditText = findViewById(R.id.adding_product_price);
        productOffPriceEditText = findViewById(R.id.adding_product_off_price);
        descriptionEditText = findViewById(R.id.adding_product_description);
        numberEditText = findViewById(R.id.adding_product_number);
        factoryEditText = findViewById(R.id.adding_product_factory);
        imageView = findViewById(R.id.adding_image_view_products);
        final ProductsDatabase productsDatabase = new ProductsDatabase(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product(productNameEditText.getText().toString().trim(),
                        factoryEditText.getText().toString().trim(),
                        Long.valueOf(productPriceEditText.getText().toString().trim()),
                        Double.valueOf(productOffPriceEditText.getText().toString().trim()),
                        descriptionEditText.getText().toString().trim(),
                        Integer.valueOf(numberEditText.getText().
                                toString().trim()));
                product.setType(spinner.getSelectedItem().toString());
                product.setPicID(picID);
                mSale.products.addLast(product);
                productsDatabase.insertData(product.getType(), product.getName(), product.getFactory(), String.valueOf(product.getPrice()), String.valueOf(product.getOff()), product.getExplanation(), String.valueOf(product.getNumber()), product.getPicID());
                Message.message(getApplicationContext(), String.valueOf(productsDatabase.getData()));
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4500")));
        getSupportActionBar().setTitle("M8");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                picID = imageUri.toString();

                try {
                    InputStream inputStream = getContentResolver().openInputStream(Uri.parse(picID));
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
