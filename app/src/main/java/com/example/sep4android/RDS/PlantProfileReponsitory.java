package com.example.sep4android.RDS;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.PlantProfileList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlantProfileReponsitory {

    private MutableLiveData<PlantProfileList> plantProfiles;
    private MutableLiveData<PlantProfile> profile;
    private static PlantProfileReponsitory reponsitory;

    private PlantProfileReponsitory(){
    }

    public static synchronized PlantProfileReponsitory getInstance() {
        if (reponsitory == null) {
            reponsitory = new PlantProfileReponsitory();
        }
        return reponsitory;
    }


    public void getProfileFromApi(String email) {
        plantProfiles = new MutableLiveData<>();

        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.userInfo(email);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    plantProfiles.setValue( response.body().getUser().getProfiles());
                } else {
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    public void saveProfileToApi(PlantProfile pp) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<PlantProfile> call = userApi.updatePlantProfile(pp);
        call.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if(!response.isSuccessful())
                {
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
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

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

    public LiveData<PlantProfileList> getProfiles() {
        return plantProfiles;
    }

    public void UpdateProfiles(String email) {
        getProfileFromApi(email);
    }
}