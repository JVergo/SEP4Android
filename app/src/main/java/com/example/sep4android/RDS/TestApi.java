package com.example.sep4android.RDS;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestApi    {
    @GET("test")
Call<TestResponse> test();

}

