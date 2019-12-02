package com.example.sep4android.RDS;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.PlantList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantReponsitory {
    private MutableLiveData<PlantList> plants;
    private static PlantReponsitory reponsitory;

    private PlantReponsitory(){
        //reponsitory = PlantReponsitory.getInstance();
        plants = new MutableLiveData<>();
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
                    Log.i("Daniela", plants.getValue().toString());
                } else {
                    Log.i("Daniela", "error");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("Daniela", t.getMessage());
            }
        });
    }

 public LiveData<PlantList> getPlants(){
        return plants;
 }

}
