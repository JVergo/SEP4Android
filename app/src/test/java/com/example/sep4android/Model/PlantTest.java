package com.example.sep4android.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlantTest {

    @Test
    public void getId() {
        int expected = 1;
        Plant p = new Plant(expected, "", "", 0, new PlantData(0), new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(expected, p.getId());
    }
    @Test
    public void setId() {
        int id = 1;
        Plant p = new Plant(id, "", "", 0, new PlantData(0), new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(id, p.getId());
        int expected = 2;
        p.setId(expected);
        assertEquals(expected, p.getId());
    }

    @Test
    public void getName() {
        String expected = "Test";
        Plant p = new Plant(0, "", expected, 0, new PlantData(0), new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(expected, p.getName());
    }
    @Test
    public void setName() {
        String name = "Test";
        Plant p = new Plant(0, "", name, 0, new PlantData(0), new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(name, p.getName());
        String expected = "Rose";
        p.setName(expected);
        assertEquals(expected, p.getName());
    }

    @Test
    public void getProfile() {
        PlantProfile expected = new PlantProfile();
        Plant p = new Plant(0, "", "", 0, new PlantData(0), new PlantData(0), new PlantData(0), new PlantData(0));
        p.setProfile(expected);
        assertEquals(expected, p.getProfile());
    }
    @Test
    public void setProfile() {
        PlantProfile pp = new PlantProfile();
        Plant p = new Plant(0, "", "", 0, new PlantData(0), new PlantData(0), new PlantData(0), new PlantData(0));
        p.setProfile(pp);
        assertEquals(pp, p.getProfile());
        PlantProfile expected = new PlantProfile();
        p.setProfile(expected);
        assertEquals(expected, p.getProfile());
    }

    @Test
    public void getLastCO2Measurement() {
        PlantData expected = new PlantData(34);
        Plant p = new Plant(0, "", "", 0, expected, new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(expected, p.getLastCO2Measurement());
    }
    @Test
    public void setLastCO2Measurement() {
        PlantData pd = new PlantData(22);
        Plant p = new Plant(0, "", "", 0, pd, new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(pd, p.getLastCO2Measurement());
        PlantData expected = new PlantData(1);
        p.setLastCO2Measurement(expected);
        assertEquals(expected, p.getLastCO2Measurement());
    }

    @Test
    public void getLastHumidityMeasurement() {
        PlantData expected = new PlantData(99);
        Plant p = new Plant(0, "", "", 0, new PlantData(0),  new PlantData(0), expected, new PlantData(0));
        assertEquals(expected, p.getLastHumidityMeasurement());
    }
    @Test
    public void setLastHumidityMeasurement() {
        PlantData pd = new PlantData(55);
        Plant p = new Plant(0, "", "", 0, new PlantData(0),  new PlantData(0), pd, new PlantData(0));
        assertEquals(pd, p.getLastHumidityMeasurement());
        PlantData expected = new PlantData(11);
        p.setLastHumidityMeasurement(expected);
        assertEquals(expected, p.getLastHumidityMeasurement());
    }

    @Test
    public void getLastTemperatureMeasurement() {
        PlantData expected = new PlantData(3456);
        Plant p = new Plant(0, "", "", 0, new PlantData(0), expected,  new PlantData(0), new PlantData(0));
        assertEquals(expected, p.getLastTemperatureMeasurement());
    }
    @Test
    public void setLastTemperatureMeasurement() {
        PlantData pd = new PlantData(585);
        Plant p = new Plant(0, "", "", 0, new PlantData(0), pd,  new PlantData(0), new PlantData(0));
        assertEquals(pd, p.getLastTemperatureMeasurement());
        PlantData expected = new PlantData(159);
        p.setLastTemperatureMeasurement(expected);
        assertEquals(expected, p.getLastTemperatureMeasurement());
    }

    @Test
    public void getLastLightMeasurement() {
        PlantData expected = new PlantData(585);
        Plant p = new Plant(0, "", "", 0, new PlantData(0),  new PlantData(0), new PlantData(0), expected);
        assertEquals(expected, p.getLastLightMeasurement());
    }
    @Test
    public void setLastLightMeasurement() {
        PlantData pd = new PlantData(585);
        Plant p = new Plant(0, "", "", 0, new PlantData(0),  new PlantData(0), new PlantData(0), pd);
        assertEquals(pd, p.getLastLightMeasurement());
        PlantData expected = new PlantData(159);
        p.setLastLightMeasurement(expected);
        assertEquals(expected, p.getLastLightMeasurement());
    }

    @Test
    public void getPlantProfileId() {
        int expected = 7;
        Plant p = new Plant(0, "", "", expected, new PlantData(0),  new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(expected, p.getPlantProfileId());
    }

    @Test
    public void getDeviceId() {
        String expected = "diviceid";
        Plant p = new Plant(0, expected, "", 0, new PlantData(0),  new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(expected, p.getDeviceId());
    }
    @Test
    public void setDeviceId() {
        String dId = "diviceid";
        Plant p = new Plant(0, dId, "", 0, new PlantData(0),  new PlantData(0), new PlantData(0), new PlantData(0));
        assertEquals(dId, p.getDeviceId());
        String expected = "diviceid34525";
        p.setDeviceId(expected);
        assertEquals(expected, p.getDeviceId());
    }
}