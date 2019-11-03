package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.R;

public class PlantProfileFragment extends Fragment {

    private PlantProfileViewModel plantProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantProfileViewModel =
                ViewModelProviders.of(this).get(PlantProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_profile, container, false);
        //final TextView textView = root.findViewById(R.id.text_plantProfile);
        plantProfileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}