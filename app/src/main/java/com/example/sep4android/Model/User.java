package com.example.sep4android.Model;

public class User{

	private String email;
	//private String password;
	private PlantProfileList profiles;
	private PlantList plants;

	public User() {
	}
	public User(String email, String password) {
		profiles = new PlantProfileList();
		plants = new PlantList();
		this.email = email;
		//this.password = password;
	}
	public User(String email,PlantProfileList profileList, PlantList plantList) {
		this.email = email;
		this.profiles = profileList;
		this.plants = plantList;
	}

	public PlantProfileList getProfiles() {
		return profiles;
	}
	public void setProfiles(PlantProfileList profiles) {
		this.profiles = profiles;
	}

	public PlantList getPlants() {
		return plants;
	}
	public void setPlants(PlantList plants) {
		this.plants = plants;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	//public String getPassword() {
		//return password;
	//}
	//public void setPassword(String password) {
		//this.password = password;
	//}

}
