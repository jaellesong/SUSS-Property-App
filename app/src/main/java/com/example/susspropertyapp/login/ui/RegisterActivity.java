package com.example.susspropertyapp.login.ui;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.UserDataManager;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Toolbar topAppBar;
    MediaPlayer mp;
    private UserDataManager loginDM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        topAppBar = findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(this, R.raw.button);
        Button continueBtn = findViewById(R.id.continueBtn);
        loginDM = new UserDataManager(this);
        topAppBar.setNavigationOnClickListener(view1 -> {
            mp.start();
            Intent MainLoginActivity = new Intent(RegisterActivity.this, MainLoginActivity.class);
//        listNotes.putExtra("notes", (ArrayList<Note>) notes);
            startActivity(MainLoginActivity);
        });

        EditText name = findViewById(R.id.editName);
        EditText email = findViewById(R.id.editEmail);
        EditText pw = findViewById(R.id.editPw);
        EditText pw2 = findViewById(R.id.editPw2);

        continueBtn.setOnClickListener(v -> {
            String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern patternEmail = Pattern.compile(regexEmail);
            Matcher matcherEmail = patternEmail.matcher(email.getText().toString());

            String regexPw ="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}";
            Pattern patternPw = Pattern.compile(regexPw);
            Matcher matcherPw = patternPw.matcher(pw.getText().toString());

            if (name.getText().toString().isEmpty() ||
                    email.getText().toString().isEmpty() ||
                    pw.getText().toString().isEmpty() ||
                    pw.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(),
                        "Please fill up all the fields", Toast.LENGTH_SHORT).show();

            } else if (!matcherEmail.matches()) {
                Toast.makeText(getApplicationContext(),
                        "Please enter a valid email", Toast.LENGTH_SHORT).show();

            } else if (!pw.getText().toString().equals(pw2.getText().toString())) {
                Toast.makeText(getApplicationContext(),
                        "Both passwords do not match", Toast.LENGTH_SHORT).show();

            } else if (!matcherPw.matches()){
                Toast.makeText(getApplicationContext(),
                        "Password must have at least 1 lowercase, 1 uppercase, " +
                                "1 special character, 1 digit and 8 letters",
                        Toast.LENGTH_SHORT).show();
            } else {
                Cursor testC = loginDM.searchUser(email.getText().toString(),pw.getText().toString());

                if (testC != null && testC.getCount() > 0) {
                    Toast.makeText(getApplicationContext(),
                            email.getText().toString() + " already exists", Toast.LENGTH_SHORT).show();

                } else{
                    String userId = "";
                    Random r = new Random();
                    char c = (char)(r.nextInt(26) + 'a');
                    userId += String.valueOf(c).toUpperCase();

                    for (int i = 0; i < 8; i++) {
                        int num = r.nextInt(10);
                        userId += String.valueOf(num);
                    }

                    loginDM.insert(userId,email.getText().toString(),pw.getText().toString(),name.getText().toString(),"false");
                    Intent LoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(LoginActivity);
                    finish();
                    Toast.makeText(getApplicationContext(),
                            name.getText().toString() + " has been registered", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}