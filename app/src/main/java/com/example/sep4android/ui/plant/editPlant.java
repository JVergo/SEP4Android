package com.example.sep4android.ui.plant;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4android.R;
import com.example.sep4android.ViewModel.EditPlantViewModel;

public class editPlant extends Fragment {

    public static editPlant newInstance() {
        return new editPlant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_plant_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditPlantViewModel mViewModel = ViewModelProviders.of(this).get(EditPlantViewModel.class);
    }

}
