package com.example.sep4android.RDS;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @GET("users/{userID}")
    Call<UserResponse> userInfo(@Path("userID") String text);

    @PUT("plantprofiles")
    Call<PlantProfile> updatePlantProfile(@Body PlantProfile plantProfile);

    @POST("plantprofiles")
    Call<PlantProfile> createPlantProfile(@Body PlantProfile plantProfile);

    @PUT("testString")
    Call<PlantProfile> testString(@Body String test);

    @PUT("testPlantProfile")
    Call<PlantProfile> testProfile(@Body PlantProfile PlantProfile);
}
