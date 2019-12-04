package com.example.sep4android.Model;

public class SensorBoundaries {
	private double max;
	private double min;

	public SensorBoundaries(double min, double max) {
		this.max = max;
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}

	public Double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}

	public boolean isValid() {
		if(min<max){
			return true;
		}
		else {
			return false;
		}
	}
}
