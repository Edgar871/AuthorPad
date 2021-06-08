package com.example.authorpad.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.authorpad.R;
import com.example.authorpad.app.DatabaseHelper;
import com.example.authorpad.app.UserSessionManager;
import com.example.authorpad.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_emailSignin);
        etPassword = findViewById(R.id.et_passwordSignin);

        findViewById(R.id.button_signupSignin).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.button_signinSignin).setOnClickListener(v -> {
            if(login()) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Wrong username or password!", Toast.LENGTH_LONG).show();
            }
        });

        databaseHelper = new DatabaseHelper(this);
    }

    private boolean login() {
        Cursor cursor = databaseHelper.login(new User(etUsername.getText().toString(), etPassword.getText().toString()));
        cursor.moveToNext();
        if(cursor.getCount() != 0) {
            UserSessionManager.getInstance(MainActivity.this).setLogin(cursor.getString(1), cursor.getInt(0));
            return true;
        }
        return false;
    }
}