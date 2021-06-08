package com.example.authorpad.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.authorpad.R;
import com.example.authorpad.app.DatabaseHelper;
import com.example.authorpad.model.Story;

public class UpdateActivity extends AppCompatActivity {

    private EditText etTitle, etStory;
    private DatabaseHelper databaseHelper;

    private Bundle bundle;

    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        findViewById(R.id.iv_back).setOnClickListener(v -> finish());

        etTitle = findViewById(R.id.et_title);
        etStory = findViewById(R.id.et_story);

        databaseHelper = new DatabaseHelper(this);

        bundle = getIntent().getExtras();
        story = bundle.getParcelable("story");

        etTitle.setText(story.getTitle());
        etStory.setText(story.getStory());

        findViewById(R.id.btn_story_finish).setOnClickListener(v -> updateStory());

        findViewById(R.id.btn_story_delete).setOnClickListener(v -> {
            databaseHelper.deleteStory(story);
            finish();
        });

    }

    private void updateStory() {
        story.setTitle(etTitle.getText().toString());
        story.setStory(etStory.getText().toString());
        databaseHelper.updateStory(story);
        finish();
    }


}