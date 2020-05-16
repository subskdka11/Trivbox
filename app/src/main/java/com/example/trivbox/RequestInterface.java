package com.example.trivbox;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface RequestInterface {

    @GET("api.php?amount=10")
    Call<RequestAPIModelClass> getQuestionJson();

    @GET("api.php?amount=10")
    Call<RequestAPIModelClass> getQuestionJsonWithCategory(@Query("category") String category);

    @GET("api.php?amount=10")
    Call<RequestAPIModelClass> getQuestionJsonWithDifficulty(@Query("difficulty") String difficulty);

    @GET("api.php?amount=10")
    Call<RequestAPIModelClass> getQuestionJsonWithCategoryAndDifficulty(@Query("category") String category, @Query("difficulty") String difficulty);
}
