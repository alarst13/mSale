package com.example.msale.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.msale.R;
import com.example.msale.Main_Page_Activity;
import com.example.msale.classes.Message;
import com.example.msale.classes.mSale;
import com.example.msale.classes.UsersDatabase;

import java.util.Random;

public class SignUp extends AppCompatActivity {

    UsersDatabase helper;
    EditText password_edt, confirm_edt, username_edt, phoneNumber_edt;
    Button submit;
    Boolean bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4500")));
        getSupportActionBar().setTitle("Sign Up");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        submit = findViewById(R.id.sign_up_btn);
        username_edt = findViewById(R.id.sign_up_username_edt);
        phoneNumber_edt = findViewById(R.id.sign_up_phone_number_edt);
        confirm_edt = findViewById(R.id.sign_up_confirming_password_edt);
        password_edt = findViewById(R.id.sign_up_password_edt);

        helper = new UsersDatabase(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                String username = username_edt.getText().toString();
                String phoneNumber = phoneNumber_edt.getText().toString();
                String password = password_edt.getText().toString();
                String confirm = confirm_edt.getText().toString();

                if (username.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Message.message(getApplicationContext(), "Please Fill All The Fields.");
                } else {
                    if (!password.equals(confirm)) {
                        Message.message(getApplicationContext(), "Those Passwords didn't match.\nPlease try again.");
                    } else {
                        if (!mSale.checkingUsername(username)) {
                            if (!mSale.checkPhoneNumber(phoneNumber)) {
                                long cash = new Random().nextInt(10000000) + 1000000;
                                long id = (long) helper.insertData(username, phoneNumber, password, String.valueOf(cash), String.valueOf(bool));
                                if (id <= 0) {
                                    Message.message(getApplicationContext(), "Insertion Unsuccessful");
                                    username_edt.setText("");
                                    phoneNumber_edt.setText("");
                                    password_edt.setText("");
                                    confirm_edt.setText("");
                                }
                                else {
                                    Intent intent = new Intent(getBaseContext(), Main_Page_Activity.class);
                                    bool = true;
                                    helper.editBool(username,String.valueOf(bool));
                                    intent.putExtra("user", mSale.getUsers(username));
                                    username_edt.setText("");
                                    phoneNumber_edt.setText("");
                                    password_edt.setText("");
                                    confirm_edt.setText("");
                                    finishActivity(888);
                                    startActivity(intent);
                                }
                            }
                            else {
                                Message.message(getApplicationContext(), "This Phone Number has been used.");
                            }
                        }
                        else {
                            Message.message(getApplicationContext(), "This Username already exists.");
                        }
                    }
                }
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
