package com.example.sep4android.RDS;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @DELETE("plantprofiles/{plantProfileID}")
    Call<RequestBody> deletePlantProfile(@Path("plantProfileID") String plantProfileID);

    @PUT("plants")
    Call<Plant> updatePlant(@Body Plant plant);

    @POST("plants")
    Call<Plant> createPlant(@Body Plant plant);

    @DELETE("plants/{plantID}")
    Call<Plant> deletePlant(@Path("plantID") String plantID);
}
