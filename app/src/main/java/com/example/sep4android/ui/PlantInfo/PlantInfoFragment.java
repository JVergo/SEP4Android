package com.example.sep4android.ui.PlantInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sep4android.R;
import com.example.sep4android.ViewModel.PlantInfoViewModel;

public class PlantInfoFragment extends Fragment  {

private PlantInfoViewModel plantInfoViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plant_info, container, false);
        return rootView;


    }





}
