package com.example.trivbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowScore extends AppCompatActivity {
    private TextView scoreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        Intent i = getIntent();
        int score = (int) i.getIntExtra("score", 0);

        scoreId = findViewById(R.id.score_id);
        Toast.makeText(ShowScore.this, ""+score, Toast.LENGTH_SHORT).show();
        scoreId.setText(""+score);

    }

    public void try_again(View view) {
        startActivity(new Intent(ShowScore.this, ClassicOptions.class));
    }

    public void exit(View view) {
        startActivity(new Intent(ShowScore.this, MainActivity.class));
    }
}
