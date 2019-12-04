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
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.R;
import com.example.sep4android.ViewModel.PlantDetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlantDetails extends Fragment {
    FloatingActionButton editPlantBTN;

    public static PlantDetails newInstance() {
        return new PlantDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_plant_info, container, false);
        editPlantBTN = root.findViewById(R.id.floatingActionButton);
        FloatButtonOnClick();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PlantDetailsViewModel mViewModel = ViewModelProviders.of(this).get(PlantDetailsViewModel.class);
    }

    public void FloatButtonOnClick(){
        editPlantBTN.setOnClickListener(v -> {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new CreatePlant());
            fragmentTransaction.commit();
        });
    }

}
