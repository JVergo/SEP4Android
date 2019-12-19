package com.example.sep4android.Model;

/**
 * This is a PlantUpdater class which is used for the update of the plant information only.
 * This class contains the fields of plantId, deviceId, plantProfileId, and plantName.
 * Also, it has multiple constructors.
 * The getters and setters are implemented for the fields.
 *
 * While user wants to edit the plant details, this class is responsible to save updated plant info.
 * This class makes plant model having relationships with profile
 * and can be signed to the specific device by storing the unique ids of plant, plant profile and device.
 *
 *
 *
 * */
import com.example.sep4android.Model.Plant;

public class PlantUpdater {
    private int plantId;
    private String deviceId;
    private int plantProfileId;
    private String plantName;

    //constructors
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

    //getters and setters for plant name, plant is, plant profile id, and device id.
    //plant can get info about plant profiles by searching their ids
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
