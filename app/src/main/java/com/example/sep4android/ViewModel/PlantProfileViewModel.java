package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.RDS.PlantProfileReponsitory;

public class PlantProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    private LiveData<PlantProfileList> mPlantProfileList;
    private String email;

    private PlantProfileReponsitory reponsitory;
    public PlantProfileViewModel(){
        reponsitory = PlantProfileReponsitory.getInstance();
        mPlantProfileList = reponsitory.getProfiles();
    }

    public LiveData<PlantProfileList> getPlantProfiles(String email){
        /*if(mPlantList.getValue() == null)
        getPlantsFromApi(email);
        return mPlantList;*/
        return reponsitory.getProfiles();
    }



}