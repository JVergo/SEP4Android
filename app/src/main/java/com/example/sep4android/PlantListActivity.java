package com.example.sep4android;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity implements PlantAdapter.OnPlantListener {

    RecyclerView mPlantList;
    RecyclerView.Adapter mPlantAdapter;
    ArrayList<Plant> plants = new ArrayList<>();
    //private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_plant);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(myToolbar);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //test for list
        plants.add(new Plant("Rose", "roses"));
        plants.add(new Plant("Jasmine", "jasmines"));
        plants.add(new Plant("Jasmine", "jasmines"));
        plants.add(new Plant("Jasmine", "jasmines"));
        plants.add(new Plant("Jasmine", "jasmines"));
        plants.add(new Plant("Jasmine", "jasmines"));
        plants.add(new Plant("Jasmine", "jasmines"));


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


    public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.back){
        finish();
    }return super.onOptionsItemSelected(item);
    }
}
