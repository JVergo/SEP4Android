package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.PlantList;
import com.example.sep4android.RDS.PlantReponsitory;

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

    private PlantReponsitory reponsitory;

    public PlantViewModel(){
        reponsitory = PlantReponsitory.getInstance();
        mPlantList = new MutableLiveData<PlantList>();
    }

    public void getPlantsFromApi(String email){
        reponsitory.getPlantFromApi(email);
    }

    public LiveData<PlantList> getPlants(String email){
        return reponsitory.getPlants();
    }

}