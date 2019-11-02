package com.example.sep4android;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PlantInfoActivity extends AppCompatActivity {
    private static final String TAG = "PlantInfoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);
        Log.d(TAG, "onCreate: started.");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(myToolbar);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.back){
            finish();
        }return super.onOptionsItemSelected(item);
    }
}

