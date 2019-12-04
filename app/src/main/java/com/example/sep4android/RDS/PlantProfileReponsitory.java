package com.example.sep4android.RDS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.PlantProfileList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantProfileReponsitory {

    private MutableLiveData<PlantProfileList> plantProfiles;
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

    public LiveData<PlantProfileList> getProfiles() {
        return plantProfiles;
    }

    public void UpdateProfiles(String email) {
        getProfileFromApi(email);
    }
}
