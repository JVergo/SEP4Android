package com.example.sep4android.RDS;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.Model.User;

public class UserResponse {
    private String email;
    private String password;
    private PlantProfileList profiles;
    private PlantList plants;

    public User getUser(){
        MutableLiveData<PlantProfileList> mutableprofiles = new MutableLiveData<PlantProfileList>();
        mutableprofiles.setValue(profiles);
        MutableLiveData<PlantList> mutableplants = new MutableLiveData<PlantList>();
        mutableplants.setValue(plants);

        return new User(email,mutableprofiles,mutableplants);
    }
}
