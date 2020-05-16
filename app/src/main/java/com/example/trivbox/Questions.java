package com.example.trivbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Questions extends AppCompatActivity {
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        question = findViewById(R.id.question_id);

        Intent i = getIntent();
//        Bundle bundle = getIntent().getExtras();
//        String apiResponse = bundle.getString("name_this");

        Result apiResponse = (Result) i.getExtras().getSerializable("name_this");
        question.setText(apiResponse.getQuestion());
//        question.setText("ksjdfkj");
    }
}
