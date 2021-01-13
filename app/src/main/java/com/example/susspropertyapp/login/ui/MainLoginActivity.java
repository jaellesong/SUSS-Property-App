package com.example.susspropertyapp.login.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.UserDataManager;

public class MainLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(v -> {
            Intent LoginActivity = new Intent(MainLoginActivity.this, LoginActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(LoginActivity,options.toBundle());

            finish();
        });
        registerBtn.setOnClickListener(v -> {
            Intent LoginActivity = new Intent(MainLoginActivity.this, RegisterActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(LoginActivity,options.toBundle());
            finish();
        });

    }


}