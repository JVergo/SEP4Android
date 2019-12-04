package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.ViewModel.ProfileDetailsViewModel;

public class ProfileDetails extends Fragment {
    private ProfileDetailsViewModel mViewModel;
    private PlantProfile curProfile;

    public static ProfileDetails newInstance(int profilePos) {
        ProfileDetails fragment = new ProfileDetails();
        Bundle args = new Bundle();
        args.putInt("profileID", profilePos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_details, container, false);

        TextView tempMin = root.findViewById(R.id.profileTempMin);
        TextView tempMax = root.findViewById(R.id.profileTempMax);
        TextView humidityMin = root.findViewById(R.id.profileHumidityMin);
        TextView humidityMax = root.findViewById(R.id.profileHumidityMax);
        TextView coMin = root.findViewById(R.id.profileCo2Min);
        TextView coMax = root.findViewById(R.id.profileCo2Max);
        TextView lightMin = root.findViewById(R.id.profileLightMin);
        TextView lightMax = root.findViewById(R.id.profileLightMax);

        mViewModel = ViewModelProviders.of(this).get(ProfileDetailsViewModel.class);

        if(PlantProfileReponsitory.getInstance().getProfiles() == null) {
            String email = "naya7777@gmail.com";
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        mViewModel.getProfiles().observe(getActivity(), profileList -> {
            curProfile = profileList.getProfile(getArguments().getInt("profileID"));

            SetMinMax(tempMin, tempMax, curProfile.getTemperature());
            SetMinMax(coMin, coMax, curProfile.getCo2());
            SetMinMax(humidityMin,humidityMax, curProfile.getHumidity());
            SetMinMax(lightMin,lightMax, curProfile.getLight());
        });

        return root;
    }

    public void SetMinMax(TextView min, TextView max, SensorBoundaries v) {
        min.setText(v.getMin().toString());
        max.setText(v.getMax().toString());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileDetailsViewModel.class);
    }

}
