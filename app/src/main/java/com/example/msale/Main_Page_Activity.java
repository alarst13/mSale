package com.example.msale;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.msale.activities.SignInPage;
import com.example.msale.classes.Message;
import com.example.msale.classes.Users.Users;
import com.example.msale.classes.UsersDatabase;
import com.example.msale.classes.mSale;

import java.io.Serializable;
import java.security.cert.Extension;

public class Main_Page_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Boolean bool = false;
    UsersDatabase database = new UsersDatabase(this);
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_page);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        try{
            user = (Users) getIntent().getSerializableExtra("user");
            bool = user.isBool();
        }
        catch (Exception e){}

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Page_Activity.this, MapsActivity.class));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if(bool == true){
            navigationView = findViewById(R.id.nav_view);
            Menu menu = navigationView.getMenu();
            MenuItem nav_account = menu.findItem(R.id.nav_account);
            nav_account.setTitle(user.getUsername());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sign_out) {
            if(bool == true){
                database.editBool(String.valueOf(false), user.getId());
                mSale.getFromDatabase(this);
                Intent intent = new Intent(this, Main_Page_Activity.class);
                startActivity(intent);
                finish();
            }
        } else if (id == R.id.nav_account) {
            if(bool == false) {
                try {
                    mSale.getFromDatabase(getApplicationContext());
                } catch (Exception e) {}
                Intent intent = new Intent(Main_Page_Activity.this, SignInPage.class);
                startActivityForResult(intent,888);
            }
            else{
            }
        } else if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_mSale) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
