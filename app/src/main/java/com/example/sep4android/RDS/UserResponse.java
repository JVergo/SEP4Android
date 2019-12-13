package com.example.sep4android.RDS;

import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.Model.User;

public class UserResponse {
    private String email;
    private String password;
    private PlantProfileList profiles;
    private PlantList plants;

    public UserResponse(String email,PlantProfileList profiles,PlantList plants){
        this.email = email;
        this.profiles = profiles;
        this.plants = plants;
    }

    public UserResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User getUser(){
        return new User(email, profiles, plants);
    }
}
