package com.example.trivbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trivbox.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), ScoreboardActivity.class));
    }

    public void changeActivityToCategorySelection(View view) {
        startActivity(new Intent(getApplicationContext(), CategorySelectionActivity.class));
    }
}
