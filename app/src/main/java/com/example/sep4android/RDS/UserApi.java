package com.example.sep4android.RDS;

import com.example.sep4android.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("users/{userID}")
    Call<UserResponse> userInfo(@Path("userID") String text);

    @POST("users")
    Call<UserResponse> sendUser(@Body User user);


}
