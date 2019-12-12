package com.example.sep4android.RDS;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantReponsitory {
    private MutableLiveData<PlantList> plants;
    private static PlantReponsitory reponsitory;

    private PlantReponsitory() {
    }

    public static synchronized PlantReponsitory getInstance() {
        if (reponsitory == null) {
            reponsitory = new PlantReponsitory();
        }
        return reponsitory;
    }

    public void getPlantFromApi(String email) {
        plants = new MutableLiveData<>();
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.userInfo(email);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    plants.setValue(response.body().getUser().getPlants());
                } else {
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }

    public void savePlantToApi(Plant plant) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<Plant> call = userApi.updatePlant(plant);
        call.enqueue(new Callback<Plant>() {
            @Override
            public void onResponse(Call<Plant> call, Response<Plant> response) {
                if(!response.isSuccessful())
                {
                    Log.i("Vergo", "onResponse: " + response.toString());
                    Log.i("Vergo", "Call: " + call.toString());
                    return;
                }

                Log.i("Vergo", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Plant> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    public void createPlant(Plant plant) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<Plant> call = userApi.createPlant(plant);
        call.enqueue(new Callback<Plant>() {
            @Override
            public void onResponse(Call<Plant> call, Response<Plant> response) {
                if (response.code() == 200) {
                    //plantProfiles.setValue( response.body().getUser().getProfiles());
                    Log.i("Vergo", "onResponse: " + response.message());
                } else {
                    Log.i("Vergo", "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(Call<Plant> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    public void deletePlant(int id) {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<Plant> call = userApi.deletePlant("" + id);
        call.enqueue(new Callback<Plant>() {
            @Override
            public void onResponse(Call<Plant> call, Response<Plant> response) {
                if (response.code() == 200) {
                    //plantProfiles.setValue( response.body().getUser().getProfiles());
                    Log.i("Vergo", "onResponse: " + response.message());
                } else {
                    Log.i("Vergo", "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(Call<Plant> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    public LiveData<PlantList> getPlants() {
        return plants;
    }

    public void UpdatePalnts(String email) {
        getPlantFromApi(email);
    }
}
