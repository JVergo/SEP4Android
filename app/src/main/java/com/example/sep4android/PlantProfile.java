package com.example.sep4android;

public class PlantProfile {
    public MinMax co2;
    public MinMax temp;
    public MinMax humidity;
    public MinMax light;

    public PlantProfile(MinMax co2, MinMax temp, MinMax humidity, MinMax light)
    {
        this.co2 = co2;
        this.temp = temp;
        this.humidity = humidity;
        this.light = light;
    }

    public MinMax getCo2() {
        return co2;
    }
    public void setCo2(MinMax co2) {
        this.co2 = co2;
    }

    public MinMax getTemp() {
        return temp;
    }
    public void setTemp(MinMax temp) {
        this.temp = temp;
    }

    public MinMax getHumidity() {
        return humidity;
    }
    public void setHumidity(MinMax humidity) {
        this.humidity = humidity;
    }

    public MinMax getLight() {
        return light;
    }
    public void setLight(MinMax light) {
        this.light = light;
    }
    private String plantProfileName;

    public PlantProfile(String plantProfileName) {
        this.plantProfileName = plantProfileName;
    }


    public String getPlantProfileName() {
        return plantProfileName;
    }
}
