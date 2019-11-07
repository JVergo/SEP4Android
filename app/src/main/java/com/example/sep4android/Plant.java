package com.example.sep4android;

public class Plant {
    private String plantName;
    //private PlantProfile profile;
    private String profile;
    private int co2, temp, humidity, light;

    public Plant(String plantName, PlantProfile profile)
    {
        this.plantName = plantName;
        //this.profile = profile;
    }
    //Temp constructor for testing
    public Plant(String name, String profile, int temperatureData, int co2Data, int humidityData, int lightData) {
        this.plantName = name;
        this.profile = profile;

        this.temp = temperatureData;
        this.co2 = co2Data;
        this.humidity = humidityData;
        this.light = lightData;
    }

    public String getPlantName() {
        return plantName;
    }
    public /*PlantProfile*/String getProfile() {
        return profile;
    }

    public int getCo2() {
        return co2;
    }
    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getTemp() {
        return temp;
    }
    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumdity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getLight() {
        return light;
    }
    public void setLight(int light) {
        this.light = light;
    }
}
