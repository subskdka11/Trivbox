package com.example.trivbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassicOptions extends AppCompatActivity {

    private Spinner catSpinner,diffSpinner;
    RequestAPIModelClass apiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_options);

        catSpinner = (Spinner) findViewById(R.id.cat_spinner);
        diffSpinner = (Spinner) findViewById(R.id.diff_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item, getResources().getStringArray(R.array.categories));
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        catSpinner.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> diffArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item, getResources().getStringArray(R.array.difficulty));
        diffArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        diffSpinner.setAdapter(diffArrayAdapter);
    }

    public void start_game(View view) {
        String selectedCategory = catSpinner.getSelectedItem().toString();
        String selectedDifficulty = diffSpinner.getSelectedItem().toString();
        String[] categories = getResources().getStringArray(R.array.categories);
        String cat_id = "0";

        if(!selectedCategory.equals("Any Category")){
            for (int i=1;i<categories.length;i++) {
                if (categories[i].equals(selectedCategory)){
                    cat_id = Integer.toString(i+8);
                    break;
                }
            }
        }

        switch (selectedDifficulty){
            case "Easy":
                selectedDifficulty = "easy";
                break;
            case "Medium":
                selectedDifficulty = "medium";
                break;
            case "Hard":
                selectedDifficulty = "hard";
                break;
            default:
                selectedDifficulty = "All";
                break;
        }
        getAPIData(cat_id, selectedDifficulty);
    }

    private void getAPIData(String cat_id, String selectedDifficulty) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<RequestAPIModelClass> mycall;

        if (cat_id.equals("0")) {
            if (selectedDifficulty.equals("All")) {
                mycall = requestInterface.getQuestionJson();
            } else {
                mycall = requestInterface.getQuestionJsonWithDifficulty(selectedDifficulty);
            }
        } else if (selectedDifficulty.equals("All")){
            mycall = requestInterface.getQuestionJsonWithCategory(cat_id);
        } else{
            mycall = requestInterface.getQuestionJsonWithCategoryAndDifficulty(cat_id, selectedDifficulty);
        }

        mycall.enqueue(new Callback<RequestAPIModelClass>() {
            @Override
            public void onResponse(Call<RequestAPIModelClass> call, Response<RequestAPIModelClass> response) {
                apiResponse = response.body();
                if (apiResponse.getResponseCode() == 0){
                    changeActivity(apiResponse);
                } else if (apiResponse.getResponseCode() == 1) {
                    Toast.makeText(ClassicOptions.this, "Error: No questions found for that combination", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ClassicOptions.this, "Error: Try other category", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestAPIModelClass> call, Throwable t) {
                Toast.makeText(ClassicOptions.this, "kdkfj", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeActivity(RequestAPIModelClass apiResponse){
        List<Result> res = apiResponse.getResults();
        Intent intent = new Intent(ClassicOptions.this, Questions.class);
        intent.putExtra("name_this", (Serializable) res);
        startActivity(intent);
    }
}
