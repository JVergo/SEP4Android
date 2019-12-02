package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.PlantReponsitory;

public class ProfileDetailsViewModel extends ViewModel {
    private MutableLiveData<PlantProfileList> mProfileList;
    private PlantProfileReponsitory reponsitory;

    public ProfileDetailsViewModel(){
        reponsitory = PlantProfileReponsitory.getInstance();
        mProfileList = new MutableLiveData<PlantProfileList>();
    }

    public void getProfilesFromApi(String email){
        reponsitory.getProfileFromApi(email);
    }

    public LiveData<PlantProfileList> getProfiles(String email){
        return reponsitory.getProfiles();
    }
}
