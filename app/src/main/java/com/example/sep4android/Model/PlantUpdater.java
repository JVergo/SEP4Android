package com.example.sep4android.Model;

import com.example.sep4android.Model.Plant;

public class PlantUpdater {
    private int plantId;
    private String deviceId;
    private int plantProfileId;
    private String plantName;

    public PlantUpdater(String deviceId, int plantProfileId, String plantName) {
        this.deviceId = deviceId;
        this.plantProfileId = plantProfileId;
        this.plantName = plantName;
    }

    public PlantUpdater(Plant p) {
        this.plantId = p.getId();
        this.deviceId = p.getDeviceId();
        this.plantProfileId = p.getPlantProfileId();
        this.plantName = p.getName();
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getPlantProfileId() {
        return plantProfileId;
    }

    public void setPlantProfileId(int plantProfileId) {
        this.plantProfileId = plantProfileId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}
