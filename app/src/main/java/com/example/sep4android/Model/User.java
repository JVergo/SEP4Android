package com.example.sep4android.Model;

import androidx.lifecycle.MutableLiveData;

public class User{

	private String email;
	private String password;
	private MutableLiveData<PlantProfileList> profiles;
	private MutableLiveData<PlantList> plants;

	public User(String email, String password) {
		profiles = new MutableLiveData<>();
		plants = new MutableLiveData<>();
		this.email = email;
		this.password = password;

	}

	public User() {
	}

	public User(String email, MutableLiveData<PlantProfileList> profileList, MutableLiveData<PlantList> plantList) {
		this.email = email;
		this.profiles = profileList;
		this.plants = plantList;
	}

	public MutableLiveData<PlantProfileList> getProfiles() {
		return profiles;
	}

	public void setProfiles(MutableLiveData<PlantProfileList> profiles) {
		this.profiles = profiles;
	}

	public MutableLiveData<PlantList> getPlants() {
		return plants;
	}

	public void setPlants(MutableLiveData<PlantList> plants) {
		this.plants = plants;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
