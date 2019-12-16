package com.example.sep4android.ui.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Adapter.ProfileAdapter;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ViewModel.CreatePlantViewModel;
import com.example.sep4android.ViewModel.PlantProfileViewModel;
import com.example.sep4android.ui.plantProfile.PlantProfileFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreatePlant extends Fragment {
    EditText plantName;
    EditText sensor;
    CreatePlantViewModel createPlantProfileViewModel;
    ArrayList<PlantProfile> profiles =  new ArrayList<>();
    Spinner profile;
    View root;
    public static CreatePlant newInstance() {
        return new CreatePlant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create_plant, container, false);

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());

        plantName = root.findViewById(R.id.editPalntName);
        sensor = root.findViewById(R.id.editText_sensorId);

        createPlantProfileViewModel = ViewModelProviders.of(this).get(CreatePlantViewModel.class);

        if(PlantProfileReponsitory.getInstance().getProfiles() == null) {
            String email = UserRepository.getInstance().getUserEmail();
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        createPlantProfileViewModel.getPlantProfiles().observe(getActivity(), plantProfList -> {
            for (int i = 0; i < plantProfList.size(); i++) {
                profiles.add(plantProfList.getProfile(i));
            }

            spinner();

        });

        Button saveBTN = root.findViewById(R.id.btn_save);
        saveBTN.setOnClickListener(v -> save());

        return root;
    }

    private void save() {
        Plant temp = new Plant();
        boolean pass = true;

        if(!plantName.getText().toString().equals("")) {
            temp.setName(plantName.getText().toString());
        }
        else {
            Toast.makeText(getContext(), "Plant needs a name", Toast.LENGTH_SHORT).show();
            pass = false;
        }
        temp.setProfile((PlantProfile) profile.getSelectedItem());

        if(!sensor.getText().toString().equals("")) {
            temp.setDeviceId(sensor.getText().toString());
        }
        else {
            Toast.makeText(getContext(), "Plant needs a sensor id", Toast.LENGTH_SHORT).show();
            pass = false;
        }

        if(pass) {
            PlantReponsitory.getInstance().createPlant(temp);
            PlantReponsitory.getInstance().UpdatePalnts(UserRepository.getInstance().getUserEmail());

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new PlantFragment());
            fragmentTransaction.commit();
        }
    }


    public void clearText() {
        plantName.setText("");
        sensor.setText("");
    }

    public void spinner(){
        PlantProfile[] spinnerArray = new PlantProfile[profiles.size()];

        for (int i = 0;i<profiles.size(); i++){
            spinnerArray[i] = profiles.get(i);
        }

        profile = root.findViewById(R.id.spinnerprofile);
        ArrayAdapter<PlantProfile> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profile.setAdapter(adapter);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CreatePlantViewModel mViewModel = ViewModelProviders.of(this).get(CreatePlantViewModel.class);
    }
}
