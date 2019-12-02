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

    private PlantDetailsViewModel mViewModel;
    View root;
    FloatingActionButton btn;

    public static PlantDetails newInstance() {
        return new PlantDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_plant_info, container, false);

        FloatButtonOnClick();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlantDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    public void FloatButtonOnClick(){
        btn = root.findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new CreatePlant());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });
    }

}
