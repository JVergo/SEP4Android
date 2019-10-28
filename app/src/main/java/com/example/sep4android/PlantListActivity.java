package com.example.sep4android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity {

    RecyclerView mPlantList;
    RecyclerView.Adapter mPlantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);

        ArrayList<Plant> plants = new ArrayList<>();

        plants.add(new Plant("rose","roses",15,30,12,22));
        plants.add(new Plant("jasmine","jasmines",16,30,22,22));


        mPlantAdapter = new PlantAdapter(plants);


        mPlantList = findViewById(R.id.rv);
        mPlantList.hasFixedSize();
        mPlantList.setLayoutManager(new LinearLayoutManager(this));
        mPlantList.setAdapter(mPlantAdapter);

    }


}
