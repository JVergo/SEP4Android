package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.User;
import com.example.sep4android.RDS.UserRepository;

public class ChangePasswordViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public LiveData<User> getUser(){
        return UserRepository.getInstance().getUser();
    }
}
