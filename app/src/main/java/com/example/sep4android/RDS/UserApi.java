package com.example.sep4android.RDS;

/**
 * This is an interface class called UserApi, which is implemented in order to do the {@PUT},{@POST},(@DELETE) and (@GET) requests.
 * This class contains the request related to user account, plant, and plant profile.
 *
 *
 * */
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
    //get request for user
    @GET("users/{userID}")
    Call<UserResponse> userInfo(@Path("userID") String text);

    //put request to send updated plant profile info to API (EDIT)
    @PUT("plantprofiles")
    Call<PlantProfile> updatePlantProfile(@Body PlantProfile plantProfile);

    //post request to create a new plant profile (CREATE)
    @POST("plantprofiles")
    Call<PlantProfile> createPlantProfile(@Body PlantProfile plantProfile);

    //delete request to remove plant profile from specific user (DELETE)
    @DELETE("plantprofiles/{plantProfileID}")
    Call<RequestBody> deletePlantProfile(@Path("plantProfileID") String plantProfileID);

    //put request to send updated plant info to API (EDIT)
    @PUT("plants")
    Call<Plant> updatePlant(@Body PlantUpdater plant);

    //create a new plant and send it to API (CREATE)
    @POST("plants")
    Call<Plant> createPlant(@Body PlantUpdater plant);

    //delete a plant from the the specific user (DELETE)
    @DELETE("plants/{plantID}")
    Call<RequestBody> deletePlant(@Path("plantID") String plantID);

    //create a new user account (CREATE)
    @POST("users")
    Call<Boolean> createAccount(@Body User user);

    //log in
    @POST("login")
    Call<Boolean> Login(@Body User user);

    //change user password (EDIT)
    @PUT("users/{email}")
    Call<User> updateUser( @Path("email") String email ,@Body UserUpdate user);

    //delete a user account (DELETE)
    @DELETE("users/{email}")
    Call<RequestBody> deleteAccount(@Path("email") String email);

    //get historic data from API
    @GET("plants/{plantID}")
    Call<historicData> getPlantByIdWithWeekAvg(@Path("plantID") int plantID);


}
