package com.example.sep4android.RDS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.PlantList;

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


    public void getPlantFromApi(String email)  {
        plants = new MutableLiveData<>();
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.userInfo(email);
        //Log.i("Daniela", call.toString());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    plants.setValue( response.body().getUser().getPlants());
                } else {
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
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
