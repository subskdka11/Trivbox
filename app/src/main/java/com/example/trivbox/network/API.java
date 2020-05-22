package com.example.trivbox.network;

import android.content.Context;
import android.content.Intent;

import com.example.trivbox.activities.QuestionsActivity;
import com.example.trivbox.models.ApiResponse;
import com.example.trivbox.interfaces.RequestInterface;
import com.example.trivbox.models.Question;
import com.example.trivbox.utils.Utils;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private RequestInterface requestInterface;
    private ApiResponse apiResponse;
    private Context context;

    public API(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        requestInterface = retrofit.create(RequestInterface.class);
        this.context = context;
    }

    public void getQuestions(String cat_id, String difficulty, String type){
        Call<ApiResponse> myCall = requestInterface.getQuestions(cat_id, difficulty, type);

        myCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                apiResponse = response.body();
                if (apiResponse.getResponseCode() == 0){
                    changeActivity(apiResponse);
                } else if (apiResponse.getResponseCode() == 1) {
                    Utils.showToast(context, "Error: No questions found for that combination");
                } else {
                    Utils.showToast(context, "Error: Try other category");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Utils.showToast(context, "Error: No internet connection");
            }
        });
    }

    public void changeActivity(ApiResponse apiResponse){
        List<Question> res = apiResponse.getQuestions();
        Intent intent = new Intent(this.context, QuestionsActivity.class);
        intent.putExtra("name_this", (Serializable) res);
        context.startActivity(intent);
    }

}