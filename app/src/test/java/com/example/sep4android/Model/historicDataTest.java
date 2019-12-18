package com.example.sep4android.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class historicDataTest {

    @Test
    public void getCo2() {
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(expected, new PlantData[1], new PlantData[1], new PlantData[1]);
        assertArrayEquals(expected, hd.getCo2());
    }

    @Test
    public void setCo2() {
        PlantData[] pd = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(pd, new PlantData[1], new PlantData[1], new PlantData[1]);
        assertArrayEquals(pd, hd.getCo2());
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        hd.setCo2(expected);
        assertArrayEquals(expected, hd.getCo2());
    }

    @Test
    public void getHumidity() {
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(new PlantData[1], expected, new PlantData[1], new PlantData[1]);
        assertArrayEquals(expected, hd.getHumidity());
    }

    @Test
    public void setHumidity() {
        PlantData[] pd = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(new PlantData[1], pd, new PlantData[1], new PlantData[1]);
        assertArrayEquals(pd, hd.getHumidity());
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        hd.setHumidity(expected);
        assertArrayEquals(expected, hd.getHumidity());
    }

    @Test
    public void getTemperature() {
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(new PlantData[1], new PlantData[1], expected, new PlantData[1]);
        assertArrayEquals(expected, hd.getTemperature());
    }

    @Test
    public void setTemperature() {
        PlantData[] pd = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(new PlantData[1], new PlantData[1], pd, new PlantData[1]);
        assertArrayEquals(pd, hd.getTemperature());
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        hd.setTemperature(expected);
        assertArrayEquals(expected, hd.getTemperature());
    }

    @Test
    public void getLight() {
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(new PlantData[1], new PlantData[1], new PlantData[1], expected);
        assertArrayEquals(expected, hd.getLight());
    }

    @Test
    public void setLight() {
        PlantData[] pd = {new PlantData(0), new PlantData(3)};
        historicData hd = new historicData(new PlantData[1], new PlantData[1], new PlantData[1], pd);
        assertArrayEquals(pd, hd.getLight());
        PlantData[] expected = {new PlantData(0), new PlantData(3)};
        hd.setLight(expected);
        assertArrayEquals(expected, hd.getLight());
    }
}