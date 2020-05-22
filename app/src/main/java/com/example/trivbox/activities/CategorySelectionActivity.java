package com.example.trivbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.trivbox.R;
import com.example.trivbox.network.API;

import static com.example.trivbox.utils.Utils.spinnerAdapter;

public class CategorySelectionActivity extends AppCompatActivity {

    private Spinner catSpinner,diffSpinner,typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        catSpinner = (Spinner) findViewById(R.id.cat_spinner);
        diffSpinner = (Spinner) findViewById(R.id.diff_spinner);
        typeSpinner = (Spinner) findViewById(R.id.type_spinner);

        spinnerAdapter(this, catSpinner, getResources().getStringArray(R.array.categories));
        spinnerAdapter(this, diffSpinner, getResources().getStringArray(R.array.difficulty));
        spinnerAdapter(this, typeSpinner, getResources().getStringArray(R.array.type));
    }

    public void start_game(View view) {
        String cat_id = getCategoryId(catSpinner.getSelectedItem().toString());
        String difficulty = getDifficulty(diffSpinner.getSelectedItem().toString());
        String type = getType(typeSpinner.getSelectedItem().toString());

        API api = new API(CategorySelectionActivity.this);
        api.getQuestions(cat_id, difficulty, type);
    }

    public String getCategoryId(String selectedCategory){
        String[] categories = getResources().getStringArray(R.array.categories);
        if(!selectedCategory.equals("Any Category")){
            for (int i=1;i<categories.length;i++) {
                if (categories[i].equals(selectedCategory)){
                    return Integer.toString(i+8);
                }
            }
        }
        return "";
    }

    public String getDifficulty(String selectedDifficulty){
        if (!selectedDifficulty.equals("Any Difficulty")){
            return selectedDifficulty.substring(0, 1).toLowerCase() + selectedDifficulty.substring(1);
        }
        return "";
    }

    private String getType(String selectedType) {
        switch (selectedType){
            case "Multiple Choice":
                return "multiple";
            case "True/False":
                return "boolean";
            default:
                return "";
        }
    }
}
