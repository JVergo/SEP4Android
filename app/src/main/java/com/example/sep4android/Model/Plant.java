package com.example.sep4android.Model;

public class Plant {

        private int id;
        private String name;
        private PlantProfile profile;
        private SensorBoundaries co2, humidity, temperature, light;

        public SensorBoundaries getCo2() {
            return co2;
        }

        public void setCo2(SensorBoundaries co2) {
            this.co2 = co2;
        }

        public SensorBoundaries getHumidity() {
            return humidity;
        }

        public void setHumidity(SensorBoundaries humidity) {
            this.humidity = humidity;
        }


        public SensorBoundaries getTemperature() {
            return temperature;
        }


        public void setTemperature(SensorBoundaries temperature) {
            this.temperature = temperature;
        }


        public SensorBoundaries getLight() {
            return light;
        }


        public void setLight(SensorBoundaries light) {
            this.light = light;
        }

        public Plant() {

        }

        public Plant(int id, String name, PlantProfile profile, SensorBoundaries co2, SensorBoundaries temperature, SensorBoundaries humidity, SensorBoundaries light) {
            this.id = id;
            this.name = name;
            this.profile = profile;
            this.co2 = co2;
            this.temperature = temperature;
            this.humidity = humidity;
            this.light = light;
        }

        public Plant(String name, PlantProfile profile) {
            this.name = name;
            this.profile = profile;
        }

        public Plant(int id, String name, PlantProfile profile) {
            this.id = id;
            this.name = name;
            this.profile = profile;
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

        public PlantProfile getProfile() {
            return profile;
        }

        public void setProfile(PlantProfile profile) {
            this.profile = profile;
        }


}
