package com.example.trivbox;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

interface RequestInterface {

    @GET("api.php?amount=3")
    Call<RequestAPIModelClass> getQuestionJson();
}
