package com.example.sep4android.ui.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.ViewModel.PlantDetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlantDetails extends Fragment {
    private FloatingActionButton editPlantBTN;
    private PlantDetailsViewModel mViewModel;
    private Plant curPlant;

    public static PlantDetails newInstance(int pos) {
        PlantDetails fragment = new PlantDetails();
        Bundle args = new Bundle();
        args.putInt("plantID", pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_plant_info, container, false);
        editPlantBTN = root.findViewById(R.id.floatingActionButton);

        TextView plantName = root.findViewById(R.id.tv_plantName);

        TextView tempMin = root.findViewById(R.id.tv_tempMin);
        TextView tempMax = root.findViewById(R.id.tv_tempMax);
        TextView tempCur = root.findViewById(R.id.tv_tempCurrent);

        TextView humidityMin = root.findViewById(R.id.tv_humidityMin);
        TextView humidityMax = root.findViewById(R.id.tv_humidityMax);
        TextView humidityCur = root.findViewById(R.id.tv_humidityCurrent);

        TextView coMin = root.findViewById(R.id.tv_co2Min);
        TextView coMax = root.findViewById(R.id.tv_co2Max);
        TextView coCur = root.findViewById(R.id.tv_co2Current);

        TextView lightMin = root.findViewById(R.id.tv_lightMin);
        TextView lightMax = root.findViewById(R.id.tv_lightMax);
        TextView lightCur = root.findViewById(R.id.tv_lightCurrent);

        TextView profileName = root.findViewById(R.id.tv_plantType);

        PlantDetailsViewModel mViewModel = ViewModelProviders.of(this).get(PlantDetailsViewModel.class);

        if(PlantReponsitory.getInstance().getPlants() == null) {
            String email = "1";
            PlantReponsitory.getInstance().getPlantFromApi(email);
        }
        mViewModel.getPlants().observe(getActivity(), plantList -> {
            curPlant = plantList.getPlant((getArguments().getInt("plantID")));
            plantName.setText(curPlant.getName());
            tempCur.setText("" + curPlant.getLastTemperatureMeasurement().getMeasurementValue());
            humidityCur.setText("" + curPlant.getLastHumidityMeasurement().getMeasurementValue());
            coCur.setText("" + curPlant.getLastCO2Measurement().getMeasurementValue());
            lightCur.setText("" + curPlant.getLastLightMeasurement().getMeasurementValue());

            profileName.setText(curPlant.getProfile().getName());

            SetMinMax(tempMin, tempMax, curPlant.getProfile().getTemperatureBoundaries());
            SetMinMax(coMin, coMax, curPlant.getProfile().getCo2Boundaries());
            SetMinMax(humidityMin,humidityMax, curPlant.getProfile().getHumidityBoundaries());
            SetMinMax(lightMin,lightMax, curPlant.getProfile().getLightBoundaries());
        });

        FloatButtonOnClick();
        return root;
    }

    public void SetMinMax(TextView min, TextView max, SensorBoundaries v) {
        min.setText(v.getMin().toString());
        max.setText(v.getMax().toString());
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
            fragmentTransaction.replace(R.id.frameLayout, new EditPlant());
            fragmentTransaction.commit();
        });
    }

}
