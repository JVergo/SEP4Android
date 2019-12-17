package com.example.sep4android.RDS;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.ui.plant.PlantDetails;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantReponsitory {
    private MutableLiveData<PlantList> plants;
    private static PlantReponsitory reponsitory;
    historicData HD;

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
                }
                Log.i("Vergo", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    public void savePlantToApi(Plant plant) {
        UserApi userApi = ServiceGenerator.getUserApi();
        PlantUpdater p = new PlantUpdater(plant);
        Call<Plant> call = userApi.updatePlant(p);
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
        Call<Plant> call = userApi.createPlant(new PlantUpdater(plant.getDeviceId(), plant.getPlantProfileId(), plant.getName()));
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
        Call<RequestBody> call = userApi.deletePlant("" + id);
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

    public LiveData<PlantList> getPlants() {
        return plants;
    }

    public void UpdatePalnts(String email) {
        getPlantFromApi(email);
    }

    public void GetHistoricDataFromAPI(int plantID, PlantDetails pd){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<historicData> call = userApi.getPlantByIdWithWeekAvg(plantID);
        call.enqueue(new Callback<historicData>() {
            @Override
            public void onResponse(Call<historicData> call, Response<historicData> response) {
                if (response.code() == 200) {
                    HD = response.body();
                    pd.CreateGraph(HD);
                }
                Log.i("Vergo", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<historicData> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    public historicData getHistoricData() {
        return HD;
    }
}
