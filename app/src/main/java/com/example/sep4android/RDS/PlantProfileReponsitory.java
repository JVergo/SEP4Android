package com.example.sep4android.RDS;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.Model.SensorBoundaries;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantProfileReponsitory {

    private MutableLiveData<PlantProfileList> plantProfiles;
    private static PlantProfileReponsitory reponsitory;


    //private constructor and lazy initialization for realizing singleton
    private PlantProfileReponsitory() {
    }

    public static synchronized PlantProfileReponsitory getInstance() {
        if (reponsitory == null) {
            reponsitory = new PlantProfileReponsitory();
        }
        return reponsitory;
    }


    //get the list of plant profiles from API by searching the specific user email
    public void getProfileFromApi(String email) {
        plantProfiles = new MutableLiveData<>(); // every time the method is called, retrieve from remote
                                                // so that there won't be repeated data in the list shown in the recyclerview

        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.userInfo(email); // get a call from API to get uesr info
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    plantProfiles.setValue(response.body().getUser().getProfiles()); //if is correct,app will get list of plant profiles from
                    //the API response body
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    //save the plantProfile model to the server
    //used while trying to edit plant profile
    public void saveProfileToApi(PlantProfile pp) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<PlantProfile> call = userApi.updatePlantProfile(pp);
        call.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if (!response.isSuccessful()) {
                    Log.i("Vergo", "onResponse: " + response.toString());
                    Log.i("Vergo", "Call: " + call.toString());
                    return;
                }

                Log.i("Vergo", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    //save the new plant profile to web API
    //used while creating a new plant profile
    public void createProfile(PlantProfile pp) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<PlantProfile> call = userApi.createPlantProfile(pp);
        call.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if (response.code() == 200) {
                    //plantProfiles.setValue( response.body().getUser().getProfiles());
                    Log.i("Vergo", "onResponse: " + response.message());
                } else {
                    Log.i("Vergo", "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {
                Log.i("Vergo","Throwable: "+ t.getMessage());
            }
        });
    }

    //delete the plant profile info from API by its profile id
    public void deleteProfile(int id) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<RequestBody> call = userApi.deletePlantProfile("" + id);
        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                if (response.code() == 200) {
                    //plantProfiles.setValue( response.body().getUser().getProfiles());
                    Log.i("Vergo", "onResponse: " + response.message());
                } else {
                    Log.i("Vergo", "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    //get the live data about the list of plant profile from API
    public LiveData<PlantProfileList> getProfiles() {
        return plantProfiles;
    }

    //update plant profile info for user by user email from API

    public void UpdateProfiles(String email) {
        getProfileFromApi(email);
    }
}