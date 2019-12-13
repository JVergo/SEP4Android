package com.example.sep4android.ui.plant;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.ViewModel.CreatePlantViewModel;

import java.util.ArrayList;

public class CreatePlant extends Fragment {
    EditText plantName;
    EditText sensor;
    CreatePlantViewModel createPlantProfileViewModel;
    ArrayList<PlantProfile> profiles =  new ArrayList<>();



    View root;
    public static CreatePlant newInstance() {
        return new CreatePlant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create_plant, container, false);

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());

        plantName = root.findViewById(R.id.editText_plantename);
        sensor = root.findViewById(R.id.editText_sensorId);

        createPlantProfileViewModel = ViewModelProviders.of(this).get(CreatePlantViewModel.class);

        if(PlantProfileReponsitory.getInstance().getProfiles() == null) {
            String email = "1";
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        createPlantProfileViewModel.getPlantProfiles().observe(getActivity(), plantProfList -> {
            for (int i = 0; i < plantProfList.size(); i++) {
                profiles.add(plantProfList.getProfile(i));
            }

            spinner();

        });




        return root;
    }


    public void clearText() {
        plantName.setText("");
        sensor.setText("");
    }

    public void spinner(){


        String[] spinnerArray = new String[profiles.size()];

        for (int i = 0;i<profiles.size(); i++){
            spinnerArray[i] = profiles.get(i).getName();
        }

        Spinner profile = root.findViewById(R.id.spinnerprofile);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profile.setAdapter(adapter);


    }





}
