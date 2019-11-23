package com.example.sep4android.Model;

public class Plant {

        private int id;
        private String name;
        private PlantProfile profile;
        private PlantData co2, humidity, temperature, light;

        public PlantData getCo2() {
            return co2;
        }

        public void setCo2(PlantData co2) {
            this.co2 = co2;
        }

        public PlantData getHumidity() {
            return humidity;
        }

        public void setHumidity(PlantData humidity) {
            this.humidity = humidity;
        }


        public PlantData getTemperature() {
            return temperature;
        }


        public void setTemperature(PlantData temperature) {
            this.temperature = temperature;
        }


        public PlantData getLight() {
            return light;
        }


        public void setLight(PlantData light) {
            this.light = light;
        }

        public Plant() {

        }

        public Plant(int id, String name, PlantProfile profile, PlantData co2, PlantData temperature, PlantData humidity, PlantData light) {
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
