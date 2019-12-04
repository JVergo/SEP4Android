package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.RDS.PlantProfileReponsitory;

public class PlantProfileViewModel extends ViewModel {
    private LiveData<PlantProfileList> mPlantProfileList;

    public PlantProfileViewModel(){
        mPlantProfileList = PlantProfileReponsitory.getInstance().getProfiles();
    }

    public LiveData<PlantProfileList> getPlantProfiles(){
        return PlantProfileReponsitory.getInstance().getProfiles();
    }



}