package com.example.sep4android;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlantInfoActivity extends AppCompatActivity {
    private static final String TAG = "PlantInfoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);
        Log.d(TAG, "onCreate: started.");

    }


}
