package com.example.sep4android.RDS;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private MutableLiveData<User> users;
    private static UserRepository repository;

    private UserRepository() {
    }

    public static synchronized UserRepository getInstance() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    public void sendAccountRequest(User user){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.createAccount(user);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

}
