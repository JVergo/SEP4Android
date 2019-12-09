package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.PlantReponsitory;

public class PlantViewModel extends ViewModel {
    private MutableLiveData<PlantList> mPlantList;

    public PlantViewModel(){
        mPlantList = new MutableLiveData<>();
    }

    public void getPlantsFromApi(String email){
        PlantReponsitory.getInstance().getPlantFromApi(email);
    }

    public LiveData<PlantList> getPlants(){
        return PlantReponsitory.getInstance().getPlants();
    }


}