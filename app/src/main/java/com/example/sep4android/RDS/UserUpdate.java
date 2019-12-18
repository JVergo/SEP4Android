package com.example.sep4android.RDS;

import com.example.sep4android.Model.User;

public class UserUpdate {
    String email, password;

    //constructors
    public UserUpdate(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserUpdate() {
    }

    public UserUpdate(String email, String password) {
        this.email = email;
        this.password = password;
    }


    //getters and setters for email and password
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
