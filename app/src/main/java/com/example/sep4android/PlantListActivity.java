package com.example.sep4android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity implements PlantAdapter.OnPlantListener {

    RecyclerView mPlantList;
    RecyclerView.Adapter mPlantAdapter;
    ArrayList<Plant> plants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_plant);


        //test for list
        plants.add(new Plant("Rose","roses",15,30,12,22));
        plants.add(new Plant("Jasmine","jasmines",16,30,22,22));
        plants.add(new Plant("Jasmine","jasmines",16,30,22,22));
        plants.add(new Plant("Jasmine","jasmines",16,30,22,22));
        plants.add(new Plant("Jasmine","jasmines",16,30,22,22));
        plants.add(new Plant("Jasmine","jasmines",16,30,22,22));
        plants.add(new Plant("Jasmine","jasmines",16,30,22,22));


        mPlantAdapter = new PlantAdapter(plants, this);


        mPlantList = findViewById(R.id.rv);
        mPlantList.hasFixedSize();
        mPlantList.setLayoutManager(new LinearLayoutManager(this));
        mPlantList.setAdapter(mPlantAdapter);



    }

    //list of items should be individually a button to jump to its profile >> missing
    @Override
    public void onPlantClick(int position) {

        plants.get(position);
        Intent intent = new Intent(this, PlantInfoActivity.class);
        startActivity(intent);
    }
}
