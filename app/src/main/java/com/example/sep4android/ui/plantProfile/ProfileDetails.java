package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantList;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.ViewModel.ProfileDetailsViewModel;

public class ProfileDetails extends Fragment {

    private ProfileDetailsViewModel mViewModel;
    private View root;
    private PlantProfile curPlant;
    private String email = "naya7777@gmail.com";


    public static ProfileDetails newInstance(int profilePos) {
        ProfileDetails fragment = new ProfileDetails();
        Bundle args = new Bundle();
        args.putInt("profileID", profilePos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.profile_details_fragment, container, false);

        final TextView tempMin = root.findViewById(R.id.profileTempMin);
        final TextView tempMax = root.findViewById(R.id.profileTempMax);
        final TextView humidityMin = root.findViewById(R.id.profileHumidityMin);
        final TextView humidityMax = root.findViewById(R.id.profileHumidityMax);
        final TextView coMin = root.findViewById(R.id.profileCo2Min);
        final TextView coMax = root.findViewById(R.id.profileCo2Max);
        final TextView lightMin = root.findViewById(R.id.profileLightMin);
        final TextView lightMax = root.findViewById(R.id.profileLightMax);

        mViewModel = ViewModelProviders.of(this).get(ProfileDetailsViewModel.class);
        PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        mViewModel.getProfiles(email).observe(getActivity(), new Observer<PlantProfileList>() {
            @Override
            public void onChanged(PlantProfileList profileList) {
                curPlant = profileList.getProfile(getArguments().getInt("profileID"));

                SetMinMax(tempMin, tempMax, curPlant.getTemperature());
                SetMinMax(coMin, coMax, curPlant.getCo2());
                SetMinMax(humidityMin,humidityMax,curPlant.getHumidity());
                SetMinMax(lightMin,lightMax,curPlant.getLight());


            }
        });

        //tempMax.setText("" + curPlant.getTemperature().getMax());
        return root;
    }

    public void SetMinMax(TextView min, TextView max, SensorBoundaries v){
        min.setText(v.getMin().toString());
        max.setText(v.getMax().toString());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}
