package com.example.sep4android.RDS;

/**
 *
 * This is a Repository class for plant.
 * It uses Singlrton design pattern same as the other repository classes in this project, which guaranteed the only instance.
 * This class contains all the methods a plant used to communicate with web API,
 * which includes get, create, edit and delete functions.
 * By using the MutableLiveData and LiveData, plants' info can be easily received from the WebAPI.
 *
 *
 * */
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.PlantUpdater;
import com.example.sep4android.Model.historicData;
import com.example.sep4android.ui.plant.PlantDetails;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantReponsitory {
    private MutableLiveData<PlantList> plants;
    private static PlantReponsitory reponsitory;
    historicData HD;

    //using singleton
    private PlantReponsitory() {
    }

    public static synchronized PlantReponsitory getInstance() {
        if (reponsitory == null) {
            reponsitory = new PlantReponsitory();
        }
        return reponsitory;
    }

    //app can receive information of the list of plants by the user email that logged in
    public void getPlantFromApi(String email) {
        plants = new MutableLiveData<>(); //ensure the updated data without repeat
        UserApi userApi = ServiceGenerator.getUserApi(); //get url
        Call<UserResponse> call = userApi.userInfo(email);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    //setting value of empty PlantList to what responded from API
                    plants.setValue(response.body().getUser().getPlants());
                }
                Log.i("Vergo", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage()); //if request handling failed, error message will show in Lgcat with tag of "Vergo"
            }
        });
    }

    //send and save plant info to API
    public void savePlantToApi(Plant plant) {
        UserApi userApi = ServiceGenerator.getUserApi();
        PlantUpdater p = new PlantUpdater(plant); //call PlantUpdater class to update plant info every time
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

    //create a new plant model and save it in API
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

    //delete a plant from api by the plant id
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

    //get the livw data of the list of plant
    public LiveData<PlantList> getPlants() {
        return plants;
    }

    //get the updated plant list by the user email that logged in
    public void UpdatePalnts(String email) {
        getPlantFromApi(email);
    }

    //get the last 7 days' plant data
    public void GetHistoricDataFromAPI(int plantID, PlantDetails pd){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<historicData> call = userApi.getPlantByIdWithWeekAvg(plantID);
        call.enqueue(new Callback<historicData>() {
            @Override
            public void onResponse(Call<historicData> call, Response<historicData> response) {
                if (response.code() == 200) {
                    HD = response.body(); //get historic data from what API responded
                    pd.CreateGraph(HD); // make the statistics shown as the form of graph
                }
                Log.i("Vergo", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<historicData> call, Throwable t) {
                Log.i("Vergo", "Throwable: " + t.getMessage());
            }
        });
    }

    //get the historic data
    public historicData getHistoricData() {
        return HD;
    }
}
