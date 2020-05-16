package com.example.trivbox;

import retrofit2.Call;
import retrofit2.http.GET;

interface RequestInterface {

    @GET("api.php?amount=10")
    Call<RequestAPIModelClass> getQuestionJson();
}
