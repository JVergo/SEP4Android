package com.example.sep4android.Model;
/**
 *This is a UserUpdate class.
 *This is calling while user tries to do the {@function ChangePassword},
 * so that password info of the user will be updated in the remote server.
 *It contains multiple constructors and getters and setters for email and password.
 *
 * */

import com.example.sep4android.Model.User;

public class UserUpdate {
    String email, password;

    /**Below are the constructors for the UserUpdate class,
     * it helps while trying to call the class UserUpdate with same information but stored as object or parameters */
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

/*getters and setters for email and password
 * the getters will return the type "String"*/
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
