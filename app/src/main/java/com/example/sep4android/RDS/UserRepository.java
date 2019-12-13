package com.example.sep4android.RDS;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.MainActivity;
import com.example.sep4android.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private MutableLiveData<User> users;
    private String userEmail;
    private static UserRepository repository;

    private UserRepository() {
    }

    public static synchronized UserRepository getInstance() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    public void sendAccountRequest(User user, Context c){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<Boolean> call = userApi.createAccount(user);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code() == 200){
                    //users.setValue(response.body().getUser());
                    Log.i("Daniela", "onResponse: " + response.toString());
                    Log.i("Daniela", "Call: " + call.toString());
                    Intent myIntent = new Intent(c,  MainActivity.class);
                    c.startActivity(myIntent);
                    return;
                } else if(response.code() == 900) {
                    Toast.makeText(c, "User already exits", Toast.LENGTH_SHORT).show();
                    Log.i("Daniela", "User already exits");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("Daniela", "Throwable: " + t.getMessage());
            }
        });
    }

    public void loginReques(User user, Context c){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<Boolean> call = userApi.Login(user);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code() == 200){
                    //users.setValue(response.body().getUser());
                    Log.i("Daniela", "onResponse: " + response.toString());
                    Log.i("Daniela", "Call: " + call.toString());
                    userEmail = user.getEmail();
                    Intent myIntent = new Intent(c,  MainActivity.class);
                    c.startActivity(myIntent);
                    return;
                } else {
                    Log.i("Daniela", "onResponse: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("Daniela", "Throwable: " + t.getMessage());
            }
        });
    }

    public String getUserEmail() {
        return userEmail;
    }
}
