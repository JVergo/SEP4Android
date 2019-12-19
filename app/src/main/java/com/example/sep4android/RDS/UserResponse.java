package com.example.sep4android.RDS;

/**
 * This is a UsreResponse class, which is used to handle response related to get user infomation from the Web Api.
 * It contains multiple constructors and one getter for user.
 *
 * */
import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.Model.User;

public class UserResponse {
    private String email;
    private PlantProfileList profiles;
    private PlantList plants;

    /**This is the constructor for UserResponse class, and it contains all information related to one user*/
    public UserResponse(String email,PlantProfileList profiles,PlantList plants){
        this.email = email;
        this.profiles = profiles;
        this.plants = plants;
    }

    /**constructor with user email and password for log in*/
    public UserResponse(String email, String password) {
        this.email = email;
    }

    public User getUser(){
        return new User(email, profiles, plants);
    }
}
