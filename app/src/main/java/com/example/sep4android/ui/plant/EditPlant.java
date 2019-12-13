package com.example.sep4android.ui.plant;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ViewModel.CreatePlantViewModel;
import com.example.sep4android.ViewModel.EditPlantViewModel;

import java.util.ArrayList;

public class EditPlant extends Fragment {
    EditText plantName;
    EditText sensor;
    EditPlantViewModel editPlantViewModel;
    ArrayList<PlantProfile> profiles =  new ArrayList<>();
    View root;
    public static EditPlant newInstance() {
        return new EditPlant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_create_plant, container, false);

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());
        plantName = root.findViewById(R.id.editPalntName);
        sensor = root.findViewById(R.id.editText_sensorId);

        editPlantViewModel = ViewModelProviders.of(this).get(EditPlantViewModel.class);

        if(PlantProfileReponsitory.getInstance().getProfiles() == null) {
            String email = UserRepository.getInstance().getUserEmail();
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        editPlantViewModel.getPlantProfiles().observe(getActivity(), plantProfList -> {
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

    private void save() {
        Plant temp = new Plant();
        temp.setName(plantName.getText().toString());
        //temp.setProfile(profile.getSelectedItem());

        PlantReponsitory.getInstance().savePlantToApi(temp);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditPlantViewModel mViewModel = ViewModelProviders.of(this).get(EditPlantViewModel.class);
    }

}
