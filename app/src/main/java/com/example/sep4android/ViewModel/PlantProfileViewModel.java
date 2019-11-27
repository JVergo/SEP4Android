package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlantProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlantProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is plant profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}