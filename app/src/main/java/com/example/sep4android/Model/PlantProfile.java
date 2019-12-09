package com.example.sep4android.Model;

public class PlantProfile {

    private int id;
    private String name;
    private User user;
    private SensorBoundaries co2Boundaries, temperatureBoundaries, humidityBoundaries, lightBoundaries;

    public PlantProfile() {
    }
    public PlantProfile(int id, String name, User user, SensorBoundaries co2, SensorBoundaries temperature, SensorBoundaries humidity, SensorBoundaries light) {
        super();
        this.id = id;
        this.name = name;
        this.user = user;
        this.co2Boundaries = co2;
        this.temperatureBoundaries = temperature;
        this.humidityBoundaries = humidity;
        this.lightBoundaries = light;
    }
    public PlantProfile(String name, User user, SensorBoundaries co2, SensorBoundaries temperature, SensorBoundaries humidity, SensorBoundaries light) {
        super();
        this.name = name;
        this.user = user;
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

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public SensorBoundaries getCo2() {
        return co2Boundaries;
    }
    public void setCo2(SensorBoundaries co2) {
        this.co2Boundaries = co2;
    }

    public SensorBoundaries getTemperature() {
        return temperatureBoundaries;
    }
    public void setTemperature(SensorBoundaries temperature) {
        this.temperatureBoundaries = temperature;
    }

    public SensorBoundaries getHumidity() {
        return humidityBoundaries;
    }
    public void setHumidity(SensorBoundaries humidity) {
        this.humidityBoundaries = humidity;
    }

    public SensorBoundaries getLight() {
        return lightBoundaries;
    }
    public void setLight(SensorBoundaries light) {
        this.lightBoundaries = light;
    }
}