package com.example.sep4android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileListActivity extends AppCompatActivity {
    RecyclerView mProfileList;
    RecyclerView.Adapter mProfileAdapter;
    ArrayList<Profile> profiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);


        mProfileAdapter = new ProfileAdapter(profiles);

        profiles.add(new  Profile("Roses"));
        profiles.add(new  Profile("Violets"));


        mProfileList = findViewById(R.id.rv);
        mProfileList.hasFixedSize();
        mProfileList.setLayoutManager(new LinearLayoutManager(this));
        mProfileList.setAdapter(mProfileAdapter);

    }
}
