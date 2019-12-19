package com.example.sep4android.ui.plant;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Toast;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ViewModel.CreatePlantViewModel;
import com.example.sep4android.ViewModel.EditPlantViewModel;
import com.example.sep4android.ui.plantProfile.PlantProfileFragment;

import java.util.ArrayList;

public class EditPlant extends Fragment {
    private EditText plantName;
    private EditText sensor;
    private EditPlantViewModel editPlantViewModel;
    private ArrayList<PlantProfile> profiles =  new ArrayList<>();
    private View root;
    Plant curPlant;
    Spinner profile;

    public static EditPlant newInstance(int plantPos, int profileID) {
        EditPlant fragment = new EditPlant();
        Bundle args = new Bundle();
        args.putInt("plantPos", plantPos);
        args.putInt("profileID", profileID);
        fragment.setArguments(args);
        return fragment;
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

        if(PlantReponsitory.getInstance().getPlants() == null) {
            String email = UserRepository.getInstance().getUserEmail();
            PlantReponsitory.getInstance().getPlantFromApi(email);
        }
        editPlantViewModel.getPlants().observe(getActivity(), plantList -> {
            curPlant = plantList.getPlant(getArguments().getInt("plantPos"));
            plantName.setText(curPlant.getName());
            sensor.setText(curPlant.getDeviceId());
        });

        Button saveBTN = root.findViewById(R.id.btn_save);
        saveBTN.setOnClickListener(v -> save());

        return root;
    }


    public void clearText() {
        plantName.setText("");
        sensor.setText("");
    }

    private void save() {
        boolean pass = true;

        if(!plantName.getText().toString().equals("")) {
            curPlant.setName(plantName.getText().toString());
        }
        else {
            Toast.makeText(getContext(), "Plant needs a name", Toast.LENGTH_SHORT).show();
            pass = false;
        }
        PlantProfile pp = (PlantProfile) profile.getSelectedItem();
        curPlant.setProfile(pp);

        if(!sensor.getText().toString().equals("")) {
            curPlant.setDeviceId(sensor.getText().toString());
        }
        else {
            Toast.makeText(getContext(), "Plant needs a sensor id", Toast.LENGTH_SHORT).show();
            pass = false;
        }

        if(pass) {
            PlantReponsitory.getInstance().savePlantToApi(curPlant);
            PlantReponsitory.getInstance().UpdatePalnts(UserRepository.getInstance().getUserEmail());

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new PlantFragment());
            fragmentTransaction.commit();
        }
    }

    public void spinner(){
        PlantProfile[] spinnerArray = new PlantProfile[profiles.size()];
        int curProfileID = getArguments().getInt("profileID");
        int placement = 0;

        for (int i = 0;i<profiles.size(); i++){
            spinnerArray[i] = profiles.get(i);
            if(profiles.get(i).getId() == curProfileID)
            {
                placement = i;
            }
        }

        profile = root.findViewById(R.id.spinnerprofile);
        ArrayAdapter<PlantProfile> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profile.setAdapter(adapter);

        profile.setSelection(placement);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditPlantViewModel mViewModel = ViewModelProviders.of(this).get(EditPlantViewModel.class);
    }

}
