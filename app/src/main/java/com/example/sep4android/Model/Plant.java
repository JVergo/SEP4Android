package com.example.sep4android.Model;

public class Plant {
    private String plantName;
    private /*PlantProfile*/String profile;
    private int co2, temp, humidity, light;

    public Plant(String plantName, /*PlantProfile*/String profile)
    {
        this.plantName = plantName;
        this.profile = profile;
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
