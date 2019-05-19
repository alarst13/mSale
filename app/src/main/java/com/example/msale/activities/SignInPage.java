package com.example.msale.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.msale.Main_Page_Activity;
import com.example.msale.R;
import com.example.msale.classes.Message;
import com.example.msale.classes.Users.Users;
import com.example.msale.classes.UsersDatabase;
import com.example.msale.classes.mSale;

public class SignInPage extends AppCompatActivity {
    TextView sign_up;
    Button submit;
    EditText username_edt;
    EditText password_edt;
    Boolean bool = false;
    UsersDatabase database = new UsersDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4500")));
        getSupportActionBar().setTitle("Sign In");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }

        username_edt = findViewById(R.id.sign_in_username_edt);
        password_edt = findViewById(R.id.sign_in_password_edt);

        submit = findViewById(R.id.sign_in_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_edt.getText().toString();
                String password = password_edt.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    if (mSale.checkingUsername(username)) {
                        Users users = mSale.getUsers(username);
                        if (users.getPassword().equals(password)) {
                            Intent intent = new Intent(getBaseContext(), Main_Page_Activity.class);
                            bool = true;
                            database.editBool(String.valueOf(bool), mSale.getUsers(username).getId());
                            try {
                                mSale.getFromDatabase(getApplicationContext());
                            } catch (Exception e) {}
                            intent.putExtra("user", mSale.getUsers(username));
                            username_edt.setText("");
                            password_edt.setText("");
                            finishActivity(888);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            password_edt.setText("");
                            Message.message(getApplicationContext(), "Wrong Password");
                        }
                    }
                    else {
                        username_edt.setText("");
                        Message.message(getApplicationContext(), "This username does not exist.");
                    }
                }
                else {
                    Message.message(getApplicationContext(), "Please fill all the fields.");
                }
            }
        });

        sign_up = findViewById(R.id.sign_up_txt_button);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInPage.this, SignUp.class));
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
