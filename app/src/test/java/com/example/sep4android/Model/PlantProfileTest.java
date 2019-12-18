package com.example.sep4android.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlantProfileTest {

    @Test
    public void getId() {
        int expected = 1;
        PlantProfile pp = new PlantProfile(expected, "", "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(expected, pp.getId());
    }
    @Test
    public void setId() {
        int id = 1;
        PlantProfile pp = new PlantProfile(id, "", "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(id, pp.getId());
        int expected = 99;
        pp.setId(expected);
        assertEquals(expected, pp.getId());
    }

    @Test
    public void getName() {
        String expected = "Test";
        PlantProfile pp = new PlantProfile(0, expected, "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(expected, pp.getName());
    }
    @Test
    public void setName() {
        String name = "Test";
        PlantProfile pp = new PlantProfile(0, name, "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(name, pp.getName());
        String expected = "bob";
        pp.setName(expected);
        assertEquals(expected, pp.getName());
    }

    @Test
    public void setUserEmail() {
        String expected = "Test";
        PlantProfile pp = new PlantProfile(0, "", expected, new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(expected, pp.getUserEmail());
    }
    @Test
    public void getUserEmail() {
        String email = "Test";
        PlantProfile pp = new PlantProfile(0, "", email, new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(email, pp.getUserEmail());
        String expected = "bob";
        pp.setUserEmail(expected);
        assertEquals(expected, pp.getUserEmail());
    }

    @Test
    public void getCo2Boundaries() {
        SensorBoundaries expected = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", expected, new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(expected, pp.getCo2Boundaries());
    }

    @Test
    public void setCo2Boundaries() {
        SensorBoundaries co2 = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", co2, new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(co2, pp.getCo2Boundaries());
        SensorBoundaries expected = new SensorBoundaries(2,0);
        pp.setCo2Boundaries(expected);
        assertEquals(expected, pp.getCo2Boundaries());
    }

    @Test
    public void getTemperatureBoundaries() {
        SensorBoundaries expected = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", new SensorBoundaries(0,0), expected, new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(expected, pp.getTemperatureBoundaries());
    }
    @Test
    public void setTemperatureBoundaries() {
        SensorBoundaries temp = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", new SensorBoundaries(0,0), temp, new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertEquals(temp, pp.getTemperatureBoundaries());
        SensorBoundaries expected = new SensorBoundaries(2,0);
        pp.setTemperatureBoundaries(expected);
        assertEquals(expected, pp.getTemperatureBoundaries());
    }

    @Test
    public void getHumidityBoundaries() {
        SensorBoundaries expected = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), expected, new SensorBoundaries(0,0));
        assertEquals(expected, pp.getHumidityBoundaries());
    }
    @Test
    public void setHumidityBoundaries() {
        SensorBoundaries humi = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), humi, new SensorBoundaries(0,0));
        assertEquals(humi, pp.getHumidityBoundaries());
        SensorBoundaries expected = new SensorBoundaries(2,0);
        pp.setHumidityBoundaries(expected);
        assertEquals(expected, pp.getHumidityBoundaries());
    }

    @Test
    public void getLightBoundaries() {
        SensorBoundaries expected = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), expected);
        assertEquals(expected, pp.getLightBoundaries());
    }
    @Test
    public void setLightBoundaries() {
        SensorBoundaries light = new SensorBoundaries(0,0);
        PlantProfile pp = new PlantProfile(0, "", "", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), light);
        assertEquals(light, pp.getLightBoundaries());
        SensorBoundaries expected = new SensorBoundaries(2,0);
        pp.setLightBoundaries(expected);
        assertEquals(expected, pp.getLightBoundaries());
    }

    @Test
    public void toString1() {
        String expeted = "Test";
        PlantProfile pp = new PlantProfile();
        pp.setName(expeted);
        assertEquals(expeted, pp.toString());
    }

    @Test
    public void validate() {
        PlantProfile pp = new PlantProfile(0, "Test", "Test", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertTrue(pp.Validate(pp.getName()));

        pp = new PlantProfile(0, "", "Test", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertFalse(pp.Validate(pp.getName()));

        pp = new PlantProfile(0, "Test", "Test", null, new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertFalse(pp.Validate(pp.getName()));

        pp = new PlantProfile(0, "", "Test", new SensorBoundaries(0,0), null, new SensorBoundaries(0,0), new SensorBoundaries(0,0));
        assertFalse(pp.Validate(pp.getName()));

        pp = new PlantProfile(0, "", "Test", new SensorBoundaries(0,0), new SensorBoundaries(0,0), null, new SensorBoundaries(0,0));
        assertFalse(pp.Validate(pp.getName()));

        pp = new PlantProfile(0, "", "Test", new SensorBoundaries(0,0), new SensorBoundaries(0,0), new SensorBoundaries(0,0), null);
        assertFalse(pp.Validate(pp.getName()));
    }
}