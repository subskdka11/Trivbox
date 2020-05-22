package com.example.trivbox.interfaces;

import com.example.trivbox.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {
    @GET("api.php?amount=10")
    Call<ApiResponse> getQuestions(@Query("category") String category, @Query("difficulty") String difficulty, @Query("type") String type);
}
