package com.example.sep4android.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.PlantList;
import com.example.sep4android.RDS.UserReponsitory;

public class PlantViewModel extends ViewModel {

    private MutableLiveData<PlantList> mPlantList;
/*
    public PlantViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is plant fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }*/

    private UserReponsitory reponsitory;

    public PlantViewModel(){
        reponsitory = UserReponsitory.getInstance();
        mPlantList = new MutableLiveData<PlantList>();
    }

    public void getPlantsFromApi(String email){
        reponsitory.getUserFromApi(email);
    }

    public MutableLiveData<PlantList> getPlants(String email){
        if(mPlantList.getValue() == null) getPlantsFromApi(email);
        return mPlantList;
    }

}