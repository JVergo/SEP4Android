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

import com.example.sep4android.Adapter.ProfileAdapter;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.ViewModel.CreatePlantViewModel;
import com.example.sep4android.ViewModel.PlantProfileViewModel;

import java.util.ArrayList;
import java.util.List;

public class CreatePlant extends Fragment {
    EditText plantName;
    PlantProfileViewModel plantProfileViewModel;
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

        plantProfileViewModel.getPlantProfiles().observe(getActivity(), plantProfileList -> {
            for(int i = 0;i<plantProfileList.size();i++){
                profiles.add(plantProfileList.getProfile(i));
            }
        });
        return root;
    }


    public void clearText() {
        plantName.setText("");
    }

    public void spinner(){



        List<String> spinnerArray =  new ArrayList<String>();
        for (int i = 0;i<profiles.size(); i++){
            spinnerArray.add(profiles.get(i).getName());
        }
        Spinner profile = root.findViewById(R.id.spinnerprofile);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = root.findViewById(R.id.spinnerItem);
        sItems.setAdapter(adapter);    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CreatePlantViewModel mViewModel = ViewModelProviders.of(this).get(CreatePlantViewModel.class);
    }



}
