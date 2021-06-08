package com.example.authorpad.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.authorpad.R;
import com.example.authorpad.app.DatabaseHelper;
import com.example.authorpad.model.User;

public class SignupActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.et_emailSignin);
        etPassword = findViewById(R.id.et_passwordSignin);

        findViewById(R.id.button_signupSignin).setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.button_signinSignin).setOnClickListener(v -> signUp());

        databaseHelper = new DatabaseHelper(this);

    }

    private void signUp() {
        if(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Username or password can't be empty", Toast.LENGTH_LONG).show();
        } else  {
            databaseHelper.signup(new User(etEmail.getText().toString(), etPassword.getText().toString()));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}