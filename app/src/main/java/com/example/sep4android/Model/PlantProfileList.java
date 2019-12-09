package com.example.sep4android.Model;

import java.util.ArrayList;
import java.util.List;

public class PlantProfileList {

    private List<PlantProfile> plantProfiles;

    public PlantProfileList() {
        plantProfiles = new ArrayList<>();
    }
    public PlantProfileList(List<PlantProfile> profileList) {
        plantProfiles = profileList;
    }

    public List<PlantProfile> getPlantProfiles() {
        return plantProfiles;
    }
    public PlantProfile getProfile(int index){
        return plantProfiles.get(index);
    }
    public PlantProfile getProfileByID(int target){
        PlantProfile pp = new PlantProfile();
        for (PlantProfile p: plantProfiles) {
            if(p.getId() == target) {
                pp = p;
            }
        }
        return pp;
    }
    public int size(){
        return plantProfiles.size();
    }
    public void addPlantProfile(PlantProfile profile){
        plantProfiles.add(profile);
    }

}
