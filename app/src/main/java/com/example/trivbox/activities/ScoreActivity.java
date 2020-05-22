package com.example.trivbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trivbox.R;

public class ScoreActivity extends AppCompatActivity {
    private TextView scoreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent i = getIntent();
        int score = (int) i.getIntExtra("score", 0);

        scoreId = findViewById(R.id.score_id);
        scoreId.setText(""+score);
    }

    public void try_again(View view) {
        startActivity(new Intent(ScoreActivity.this, CategorySelectionActivity.class));
    }

    public void exit(View view) {
        startActivity(new Intent(ScoreActivity.this, MainActivity.class));
    }
}
