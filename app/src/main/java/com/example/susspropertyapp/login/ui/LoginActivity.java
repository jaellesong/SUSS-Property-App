package com.example.susspropertyapp.login.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.UserDataManager;

public class LoginActivity extends AppCompatActivity {

    Toolbar topAppBar;
    MediaPlayer mp;
    private UserDataManager loginDM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null)  getSupportActionBar().hide();
        topAppBar = findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(LoginActivity.this, R.raw.button);
        Button continueBtn = findViewById(R.id.continueBtn);


        EditText email = findViewById(R.id.editEmail);
        EditText password = findViewById(R.id.editPw);
        TextView invalidText = findViewById(R.id.invalidText);

        loginDM = new UserDataManager(this);
        topAppBar.setNavigationOnClickListener(view1 -> {
            mp.start();
            Intent MainLoginActivity = new Intent(this, MainLoginActivity.class);
//        listNotes.putExtra("notes", (ArrayList<Note>) notes);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(MainLoginActivity,options.toBundle());
        });

        continueBtn.setOnClickListener(v -> {

            invalidText.animate() .alpha(0f).setDuration(0).setListener(null);;

            Cursor loginCursor =loginDM.searchUser(email.getText().toString(),password.getText().toString());
            String userId = "";
            while (loginCursor.moveToNext()) {
                userId =  loginCursor.getString(loginCursor.getColumnIndex("userId"));
            }

            if (loginCursor != null && loginCursor.getCount() > 0 ) {
                invalidText.setVisibility((int)0);
                Intent MainActivity = new Intent(this, MainActivity.class);
                MainActivity.putExtra("userId", userId);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                startActivity(MainActivity,options.toBundle());

                while (loginCursor.moveToNext()) {
                    Toast.makeText(getApplicationContext(), "Welcome " +
                            loginCursor.getString(3)+"!", Toast.LENGTH_LONG).show();
                }
            } else {
                invalidText.setVisibility(TextView.VISIBLE );
                invalidText.animate().alpha(1f).setDuration(300).setListener(null);
                Toast.makeText(getApplicationContext(),"Cannot find user",Toast.LENGTH_SHORT).show();
            }

//            finish();
        });

    }

}