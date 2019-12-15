package com.example.sep4android.RDS;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.MainActivity;
import com.example.sep4android.Model.User;

import okhttp3.RequestBody;
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
                } else if(response.code() == 500) {
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
    public void getUserFromApi(String email){
        users = new MutableLiveData<>();

        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.userInfo(email);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 200) {
                    users.setValue( response.body().getUser());
                } else {
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

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
                } else if(response.code() == 500) {
                    Toast.makeText(c, "email or password are invalid", Toast.LENGTH_SHORT).show();
                    Log.i("Daniela", "email or password are invalid");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("Daniela", "Throwable: " + t.getMessage());
            }
        });
    }
    public void updateUser(User user){
        UserApi userApi = ServiceGenerator.getUserApi();

        Call<User> call = userApi.updateUser(UserRepository.getInstance().getUserEmail(),user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){

                    Log.i("Daniela", "on response: " + response.toString());
                    Log.i("Daniela", "call: " + call.toString());
                    return;
                }

                Log.i("Daniela", "On Response: " + response.body());
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Daniela","Throwable: " + t.getMessage());
            }
        });

    }
    public void deleteUser(String email){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<RequestBody> call = userApi.deleteAccount(""+email);

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

    public String getUserEmail() {
        return userEmail;
    }
    public LiveData<User> getUser(){

        return users;
    }

}
