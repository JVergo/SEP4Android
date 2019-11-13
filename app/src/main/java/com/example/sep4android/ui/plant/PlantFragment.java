package com.example.sep4android.ui.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Adapter.PlantAdapter;
import com.example.sep4android.R;
import com.example.sep4android.ViewModel.PlantViewModel;
import com.example.sep4android.ui.PlantInfo.PlantInfoFragment;

import java.util.ArrayList;


public class PlantFragment extends Fragment implements PlantAdapter.OnPlantListener  {

    private PlantViewModel plantViewModel;
    RecyclerView mPlantList;
    RecyclerView.Adapter mPlantAdapter;
    ArrayList<Plant> plants = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        plantViewModel = ViewModelProviders.of(this).get(PlantViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant, container, false);
        //test for list
        plants.add(new Plant("Rose","roses"));
        plants.add(new Plant("Jasmine","jasmines"));
        plants.add(new Plant("Jasmine","jasmines"));
        plants.add(new Plant("Jasmine","jasmines"));
        plants.add(new Plant("Jasmine","jasmines"));
        plants.add(new Plant("Jasmine","jasmines"));
        plants.add(new Plant("Jasmine","jasmines"));

        mPlantAdapter = new PlantAdapter(plants, this);


        mPlantList = root.findViewById(R.id.rv);
        mPlantList.hasFixedSize();
        mPlantList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlantList.setAdapter(mPlantAdapter);
        //final TextView textView = root.findViewById(R.id.text_plant);
        plantViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
    @Override
    public void onPlantClick(int position) {


       plants.get(position);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new PlantInfoFragment());
        fragmentTransaction.commit();

    }


}