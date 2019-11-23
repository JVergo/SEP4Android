package com.example.sep4android.Model;

import java.util.ArrayList;
import java.util.List;

public class PlantList
{
    private List<Plant> plants;

    public PlantList() {
        plants = new ArrayList<>();
    }

    public PlantList(List<Plant> plants){
        this.plants = plants;
    }

    public List<Plant> getClients() {
        return plants;
    }
    public Plant getPlant(int index){
        return plants.get(index);
    }
    public int size(){
        return plants.size();

    }
    public void addPlant(Plant plant){
        plants.add(plant);
    }
}