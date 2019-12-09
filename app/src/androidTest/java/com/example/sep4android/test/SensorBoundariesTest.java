package com.example.sep4android.test;

import com.example.sep4android.Model.SensorBoundaries;

import org.junit.Test;

import static org.junit.Assert.*;

public class SensorBoundariesTest {

    @Test
    public void getMax() {
        SensorBoundaries sb = new SensorBoundaries(0,0);
        assertEquals(Double.valueOf(0), sb.getMax());
    }

    @Test
    public void setMax() {
        SensorBoundaries sb = new SensorBoundaries(0,0);
        assertEquals(Double.valueOf(0), sb.getMax());
        sb.setMax(1);
        assertEquals(Double.valueOf(1), sb.getMax());
    }

    @Test
    public void getMin() {
        SensorBoundaries sb = new SensorBoundaries(0,0);
        assertEquals(Double.valueOf(0), sb.getMin());
    }

    @Test
    public void setMin() {
        SensorBoundaries sb = new SensorBoundaries(0,0);
        assertEquals(Double.valueOf(0), sb.getMin());
        sb.setMin(1);
        assertEquals(Double.valueOf(1), sb.getMin());
    }

    @Test
    public void isValid() {
        SensorBoundaries sb = new SensorBoundaries(0,10);
        assertTrue(sb.isValid());
        sb = new SensorBoundaries(10,0);
        assertFalse(sb.isValid());
    }
}