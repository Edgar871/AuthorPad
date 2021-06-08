package com.example.authorpad.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.authorpad.R;
import com.example.authorpad.app.DatabaseHelper;
import com.example.authorpad.app.UserSessionManager;
import com.example.authorpad.model.Story;

public class StoryActivity extends AppCompatActivity {

    private EditText etTitle, etStory;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        findViewById(R.id.iv_back).setOnClickListener(v -> finish());

        etTitle = findViewById(R.id.et_title);
        etStory = findViewById(R.id.et_story);

        findViewById(R.id.btn_story_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStory();
            }
        });

        databaseHelper = new DatabaseHelper(this);
    }

    private void insertStory() {
        if(etTitle.getText().toString().isEmpty() || etStory.getText().toString().isEmpty()) {
            Toast.makeText(this, "Story's title or body can't be empty!", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper.insertStory(new Story(etTitle.getText().toString(), etStory.getText().toString(), UserSessionManager.getInstance(this).getUserId()));
            finish();
        }
    }
}