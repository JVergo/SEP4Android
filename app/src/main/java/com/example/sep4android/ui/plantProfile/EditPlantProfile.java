package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.ViewModel.EditPlantProfileViewModel;
import com.example.sep4android.ViewModel.ProfileDetailsViewModel;

public class EditPlantProfile extends Fragment {
    private EditPlantProfileViewModel mViewModel;
    private PlantProfile curProfile;

    TextView tempMin;
    TextView tempMax;
    TextView humidityMin;
    TextView humidityMax;
    TextView coMin;
    TextView coMax;
    TextView lightMin;
    TextView lightMax;
    TextView profileName;

    public static EditPlantProfile newInstance(int pos) {
        EditPlantProfile fragment = new EditPlantProfile();
        Bundle args = new Bundle();
        args.putInt("pID", pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_plantprofile, container, false);

        tempMin = root.findViewById(R.id.editTempMin);
        tempMax = root.findViewById(R.id.editTempMax);
        humidityMin = root.findViewById(R.id.editHumidityMin);
        humidityMax = root.findViewById(R.id.editHumidityMax);
        coMin = root.findViewById(R.id.editCo2Min);
        coMax = root.findViewById(R.id.editCo2Max);
        lightMin = root.findViewById(R.id.editLightMin);
        lightMax = root.findViewById(R.id.editLightMax);
        profileName = root.findViewById(R.id.editProfileName);

        mViewModel = ViewModelProviders.of(this).get(EditPlantProfileViewModel.class);

        if(PlantProfileReponsitory.getInstance().getProfiles() == null) {
            String email = "naya7777@gmail.com";
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        mViewModel.getProfiles().observe(getActivity(), profileList -> {
            curProfile = profileList.getProfile(getArguments().getInt("pID"));
            profileName.setText(curProfile.getName());

            SetMinMax(tempMin, tempMax, curProfile.getTemperature());
            SetMinMax(coMin, coMax, curProfile.getCo2());
            SetMinMax(humidityMin,humidityMax, curProfile.getHumidity());
            SetMinMax(lightMin,lightMax, curProfile.getLight());
        });

        Button saveBTN = root.findViewById(R.id.btn_save);
        saveBTN.setOnClickListener(v -> save());

        return root;
    }

    public void SetMinMax(TextView min, TextView max, SensorBoundaries v) {
        min.setText(v.getMin().toString());
        max.setText(v.getMax().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditPlantProfileViewModel.class);
    }

    public void clearText(){
    }

    public void save(){
        curProfile.setName(profileName.getText().toString());

        curProfile.getCo2().setMax(Double.parseDouble(coMax.getText().toString()));
        curProfile.getCo2().setMin(Double.parseDouble(coMin.getText().toString()));
        curProfile.getHumidity().setMax(Double.parseDouble(humidityMax.getText().toString()));
        curProfile.getHumidity().setMin(Double.parseDouble(humidityMin.getText().toString()));
        curProfile.getLight().setMax(Double.parseDouble(lightMax.getText().toString()));
        curProfile.getLight().setMin(Double.parseDouble(lightMin.getText().toString()));
        curProfile.getTemperature().setMax(Double.parseDouble(tempMax.getText().toString()));
        curProfile.getTemperature().setMin(Double.parseDouble(tempMin.getText().toString()));
    }
}
