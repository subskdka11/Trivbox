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
    private TextView checkSelected;
    RequestAPIModelClass apiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_options);

        String[] categories = { "All", "31", "15", "28" };
        String[] difficulties = { "All", "easy", "medium", "hard" };

        catSpinner = (Spinner) findViewById(R.id.cat_spinner);
        diffSpinner = (Spinner) findViewById(R.id.diff_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, difficulties);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diffSpinner.setAdapter(adapter1);

    }

    public void start_game(View view) {
        String selectedCategory = catSpinner.getSelectedItem().toString();
        String selectedDifficulty = diffSpinner.getSelectedItem().toString();
        checkSelected = (TextView) findViewById(R.id.check_selected);
        Toast.makeText(ClassicOptions.this, selectedCategory+selectedDifficulty, Toast.LENGTH_SHORT).show();
        getAPIData(selectedCategory, selectedDifficulty);
    }

    private void getAPIData(String selectedCategory, String selectedDifficulty) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<RequestAPIModelClass> mycall;
        if (selectedCategory.equals("All")) {
            if (selectedDifficulty.equals("All")) {
                mycall = requestInterface.getQuestionJson();
            } else {
                mycall = requestInterface.getQuestionJsonWithDifficulty(selectedDifficulty);
            }
        } else if (selectedDifficulty.equals("All")){
            mycall = requestInterface.getQuestionJsonWithCategory(selectedCategory);
        } else{
            mycall = requestInterface.getQuestionJsonWithCategoryAndDifficulty(selectedCategory, selectedDifficulty);
        }

        mycall.enqueue(new Callback<RequestAPIModelClass>() {
            @Override
            public void onResponse(Call<RequestAPIModelClass> call, Response<RequestAPIModelClass> response) {
                apiResponse = response.body();
                if (apiResponse.getResponseCode() == 0){
                    changeActivity(apiResponse);
                }else {
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
