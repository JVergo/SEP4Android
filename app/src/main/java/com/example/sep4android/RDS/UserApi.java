package com.example.sep4android.RDS;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.Model.User;

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
    Call<Plant> updatePlant(@Body PlantUpdater plant);

    @POST("plants")
    Call<Plant> createPlant(@Body PlantUpdater plant);

    @DELETE("plants/{plantID}")
    Call<RequestBody> deletePlant(@Path("plantID") String plantID);

    @POST("users")
    Call<Boolean> createAccount(@Body User user);

    @POST("login")
    Call<Boolean> Login(@Body User user);
}
