package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.RDS.PlantProfileReponsitory;

public class CreatePlantProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<PlantProfile> mplantProfile;

    public CreatePlantProfileViewModel(){
        mplantProfile = new MutableLiveData<>();
    }


    public LiveData<PlantProfile> saveProfile(){
        return PlantProfileReponsitory.getInstance().sendProfile();
    }
}
