package com.example.msale.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.msale.Main_Page_Activity;
import com.example.msale.R;
import com.example.msale.classes.ProductsOnSaleListAdapter;
import com.example.msale.classes.mSale;

public class AdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4500")));
        getSupportActionBar().setTitle("M8");

        recyclerView = findViewById(R.id.admin_recycler_view);
        mSale.mkAllList();
        ProductsOnSaleListAdapter productsOnSaleListAdapter = new ProductsOnSaleListAdapter(mSale.productsForShow);
        recyclerView.setAdapter(productsOnSaleListAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_product:
                startActivity(new Intent(AdminActivity.this, AddingNewProducts.class));
                return true;
            case R.id.action_sign_out:
                startActivity(new Intent(AdminActivity.this, Main_Page_Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
