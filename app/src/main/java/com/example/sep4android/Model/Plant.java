package com.example.sep4android.Model;

public class Plant {
    private int plantId;
    private String deviceId;
    private int plantProfileId;
    private String plantName;
    private PlantProfile plantProfile;
    private PlantData lastCO2Measurement, lastHumidityMeasurement, lastTemperatureMeasurement, lastLightMeasurement;

    public Plant(){}

    public Plant(int id, String deviceId, String name, int profile, PlantData co2, PlantData temperature, PlantData humidity, PlantData light) {
        this.plantId = id;
        this.deviceId = deviceId;
        this.plantName = name;
        this.lastCO2Measurement = co2;
        this.lastTemperatureMeasurement = temperature;
        this.lastHumidityMeasurement = humidity;
        this.lastLightMeasurement = light;
        this.plantProfileId = profile;
    }

    public Plant(String deviceId, String plantName, int plantProfileId) {
        this.deviceId = deviceId;
        this.plantName = plantName;
        this.plantProfileId = plantProfileId;
    }

    public int getId() {
        return plantId;
    }

    public void setId(int id) {
        this.plantId = id;
    }

    public String getName() {
        return plantName;
    }

    public void setName(String name) {
        this.plantName = name;
    }

    public PlantProfile getProfile() {
        return plantProfile;
    }

    public void setProfile(PlantProfile profile) {
        this.plantProfile = profile;
        plantProfileId = profile.getId();
    }

    public PlantData getLastCO2Measurement() {
        return lastCO2Measurement;
    }
    public void setLastCO2Measurement(PlantData lastCO2Measurement) {
        this.lastCO2Measurement = lastCO2Measurement;
    }

    public PlantData getLastHumidityMeasurement() {
        return lastHumidityMeasurement;
    }
    public void setLastHumidityMeasurement(PlantData lastHumidityMeasurement) {
        this.lastHumidityMeasurement = lastHumidityMeasurement;
    }

    public PlantData getLastTemperatureMeasurement() {
        return lastTemperatureMeasurement;
    }
    public void setLastTemperatureMeasurement(PlantData lastTemperatureMeasurement) {
        this.lastTemperatureMeasurement = lastTemperatureMeasurement;
    }

    public PlantData getLastLightMeasurement() {
        return lastLightMeasurement;
    }
    public void setLastLightMeasurement(PlantData lastLightMeasurement) {
        this.lastLightMeasurement = lastLightMeasurement;
    }

    public int getPlantProfileId() {
        return plantProfileId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
