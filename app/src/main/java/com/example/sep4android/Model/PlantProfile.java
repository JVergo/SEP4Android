package com.example.sep4android.Model;

public class PlantProfile {

    private int id;
    private String name;
    private String userEmail;
    private SensorBoundaries co2Boundaries, temperatureBoundaries, humidityBoundaries, lightBoundaries;

    public PlantProfile() {
    }
    public PlantProfile(int id, String name, String userEmail, SensorBoundaries co2, SensorBoundaries temperature, SensorBoundaries humidity, SensorBoundaries light) {
        super();
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
        this.co2Boundaries = co2;
        this.temperatureBoundaries = temperature;
        this.humidityBoundaries = humidity;
        this.lightBoundaries = light;
    }
    public PlantProfile(String name, String userEmail, SensorBoundaries co2, SensorBoundaries temperature, SensorBoundaries humidity, SensorBoundaries light) {
        super();
        this.name = name;
        this.userEmail = userEmail;
        this.co2Boundaries = co2;
        this.temperatureBoundaries = temperature;
        this.humidityBoundaries = humidity;
        this.lightBoundaries = light;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public SensorBoundaries getCo2Boundaries() {
        return co2Boundaries;
    }
    public void setCo2Boundaries(SensorBoundaries co2) {
        this.co2Boundaries = co2;
    }

    public SensorBoundaries getTemperatureBoundaries() {
        return temperatureBoundaries;
    }
    public void setTemperatureBoundaries(SensorBoundaries temperature) {
        this.temperatureBoundaries = temperature;
    }

    public SensorBoundaries getHumidityBoundaries() {
        return humidityBoundaries;
    }
    public void setHumidityBoundaries(SensorBoundaries humidity) {
        this.humidityBoundaries = humidity;
    }

    public SensorBoundaries getLightBoundaries() {
        return lightBoundaries;
    }
    public void setLightBoundaries(SensorBoundaries light) {
        this.lightBoundaries = light;
    }

    @Override
    public String toString() {
        return  name;
    }

    public boolean Validate(String tempName){
        if(!tempName.equals("") && co2Boundaries != null && temperatureBoundaries != null && humidityBoundaries != null && lightBoundaries != null)
        {
            return true;
        }
        return false;
    }
}