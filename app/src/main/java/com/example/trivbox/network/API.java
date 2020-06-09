package com.example.trivbox.network;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.example.trivbox.activities.CategorySelectionActivity;
import com.example.trivbox.activities.QuestionsActivity;
import com.example.trivbox.models.ApiResponse;
import com.example.trivbox.interfaces.RequestInterface;
import com.example.trivbox.models.Question;
import com.example.trivbox.models.Score;
import com.example.trivbox.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
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
    private HashMap<String, String> selections = new HashMap<String, String>();
    private List<Question> responseQuestions;
    private int responseCode;
    private Score scoreObj;

    public API(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        requestInterface = retrofit.create(RequestInterface.class);
        this.context = context;
    }

    public List<Question> getQuestions(HashMap<String, String> selections, Score scoreObj){
        this.selections = selections;
        this.scoreObj =scoreObj;
        Call<ApiResponse> myCall = requestInterface.getQuestions(selections.get("cat_id"), selections.get("difficulty"), selections.get("type"));

        myCall.enqueue(new Callback<ApiResponse>() {
            private ApiResponse apiResponseBody;
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                apiResponseBody = response.body();
//                setApiResponse(apiResponseBody);
                changeActivity(apiResponseBody);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Utils.showToast(context, "Error: No internet connection");
            }
        });
        return null;
    }
    public void changeActivity(ApiResponse apiResponse){
        List<Question> res = apiResponse.getQuestions();
        Intent intent = new Intent(this.context, QuestionsActivity.class);
        intent.putExtra("response", (Serializable) res);
        intent.putExtra("scoreObj", (Serializable) scoreObj);
        context.startActivity(intent);
    }

    public void setApiResponse(ApiResponse apiResponse){
        responseQuestions.addAll(apiResponse.getQuestions());
        responseCode = apiResponse.getResponseCode();
    }

    public List<Question> checkResponseCode(){
        if (responseCode == 0){
            Log.d("Hello", ""+responseCode);
            Log.d("Hello", responseQuestions.get(0).getCategory());
            return responseQuestions;
        } else if (responseCode == 1) {
            Utils.showToast(context, "Error: No questions found for that combination");
        } else {
            Utils.showToast(context, "Error: Try other category");
        }
        return null;
    }




}