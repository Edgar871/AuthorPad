package com.example.authorpad.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.authorpad.R;
import com.example.authorpad.model.Story;

public class ViewActivity extends AppCompatActivity {

    private Story story;
    private TextView tvTitle, tvStory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Bundle bundle = getIntent().getExtras();
        story = bundle.getParcelable("story");

        tvTitle = findViewById(R.id.tv_title);
        tvStory = findViewById(R.id.tv_body);

        tvTitle.setText(story.getTitle());
        tvStory.setText(story.getStory());

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}