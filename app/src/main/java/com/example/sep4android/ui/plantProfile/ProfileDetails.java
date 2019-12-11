package com.example.sep4android.ui.plantProfile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.ViewModel.ProfileDetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileDetails extends Fragment {
    private ProfileDetailsViewModel mViewModel;
    private PlantProfile curProfile;
    private Button delete;

    AlertDialog dialog;
    AlertDialog.Builder builder;


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
        TextView profileName = root.findViewById(R.id.profileName);


        mViewModel = ViewModelProviders.of(this).get(ProfileDetailsViewModel.class);

        if (PlantProfileReponsitory.getInstance().getProfiles() == null) {
            String email = "1";
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        mViewModel.getProfiles().observe(getActivity(), profileList -> {
            curProfile = profileList.getProfile(getArguments().getInt("profileID"));
            profileName.setText(curProfile.getName());

            SetMinMax(tempMin, tempMax, curProfile.getTemperature());
            SetMinMax(coMin, coMax, curProfile.getCo2());
            SetMinMax(humidityMin, humidityMax, curProfile.getHumidity());
            SetMinMax(lightMin, lightMax, curProfile.getLight());
        });

        FloatingActionButton editProfileBTN = root.findViewById(R.id.editProfileBTN);
        editProfileBTN.setOnClickListener(v -> {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, EditPlantProfile.newInstance(getArguments().getInt("profileID")));
            fragmentTransaction.commit();
        });

        delete = root.findViewById(R.id.imgBtnDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to delete this Plant Profile ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete api
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout, new PlantProfileFragment());
                        fragmentTransaction.commit();

                    }

                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialog = builder.create();
                dialog.show();

            }
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
