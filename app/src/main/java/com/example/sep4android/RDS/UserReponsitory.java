package com.example.sep4android.RDS;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserReponsitory {
    //public static UserReponsitory reponsitory;
    private MutableLiveData<User> user;
    private static UserReponsitory instance;

    private  UserReponsitory(){
        user = new MutableLiveData<>();
    }

    public static synchronized UserReponsitory getInstance() {
        if (instance == null) {
            instance = new UserReponsitory();
        }
        return instance;
    }


    public void getUserFromApi(String email)  {
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.userInfo(email);
        //Log.i("Daniela", call.toString());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    user.setValue( response.body().getUser());

                    Log.i("Daniela", user.getValue().getEmail());
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

    /*public User getUser(String email){
        if(user == null){
            getUserFromApi(email);
        }
        return user;
    }*/


}
