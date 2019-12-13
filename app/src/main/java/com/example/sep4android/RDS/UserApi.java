package com.example.sep4android.RDS;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
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
    Call<UserResponse>createAccount(@Body User user);

    @POST("plants")
    Call<UserResponse>addPlant(@Body Plant plant);

    @POST("users")
    Call<PlantProfile>createProfile(@Body PlantProfile profile);



    @POST("users")
    Call<UserResponse> sendUser(@Body User user);


}
