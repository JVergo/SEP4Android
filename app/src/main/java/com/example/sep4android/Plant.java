package com.example.sep4android;

public class Plant {
    private String name;
    private String profile;
    private float temperatureData,co2Data,humidityData,lightData;

    public Plant(String name, String profile, float temperatureData, float co2Data, float humidityData, float lightData) {
        this.name = name;
        this.profile = profile;

        this.temperatureData = temperatureData;
        this.co2Data = co2Data;
        this.humidityData = humidityData;
        this.lightData = lightData;
    }

    public String getName() {
        return name;
    }


    public String getProfile() {
        return profile;
    }



    public float getTemperatureData() {
        return temperatureData;
    }


    public float getCo2Data() {
        return co2Data;
    }



    public float getHumidityData() {
        return humidityData;
    }


    public float getLightData() {
        return lightData;
    }



}
