package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ViewModel.EditPlantProfileViewModel;
import com.example.sep4android.ViewModel.ProfileDetailsViewModel;

public class EditPlantProfile extends Fragment {
    private LinearLayout groupProfile, groupCo2, groupHumidity, groupTemp, groupLight;
    private EditPlantProfileViewModel mViewModel;
    private PlantProfile curProfile;

    TextView tempMin, tempMax, humidityMin, humidityMax, coMin, coMax, lightMin, lightMax, profileName;

    SensorBoundaries temp,co2,hum,light;

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


        groupProfile = root.findViewById(R.id.profileLayout);
        groupCo2 = root.findViewById(R.id.layoutco2);
        groupHumidity = root.findViewById(R.id.layouthumidity);
        groupTemp = root.findViewById(R.id.layouttemp);
        groupLight = root.findViewById(R.id.layoutlight);

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());

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
            String email = UserRepository.getInstance().getUserEmail();
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        mViewModel.getProfiles().observe(getActivity(), profileList -> {
            curProfile = profileList.getProfile(getArguments().getInt("pID"));
            profileName.setText(curProfile.getName());

            SetMinMax(tempMin, tempMax, curProfile.getTemperatureBoundaries());
            SetMinMax(coMin, coMax, curProfile.getCo2Boundaries());
            SetMinMax(humidityMin,humidityMax, curProfile.getHumidityBoundaries());
            SetMinMax(lightMin,lightMax, curProfile.getLightBoundaries());
        });

        Button saveBTN = root.findViewById(R.id.btn_save);
        saveBTN.setOnClickListener(v -> save());

        return root;
    }

    /**
     * Used to set the values of the min and max text fealds with the SensorBoundaries values
     * @param min The min textView to be set
     * @param max The max textView to be set
     * @param v The SensorBoundaries that is use to set the values of the text feald
     */
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
        // reset profile fealds
        for (int i = 0, count = groupProfile.getChildCount(); i < count; ++i) {
            View view = groupProfile.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
        // reset co2 fealds
        for (int i = 0, count = groupCo2.getChildCount(); i < count; ++i) {
            View view = groupCo2.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
        // reset humidity fealds
        for (int i = 0, count = groupHumidity.getChildCount(); i < count; ++i) {
            View view = groupHumidity.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
        // reset temperature fealds
        for (int i = 0, count = groupTemp.getChildCount(); i < count; ++i) {
            View view = groupTemp.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
        // reset light fealds
        for (int i = 0, count = groupLight.getChildCount(); i < count; ++i) {
            View view = groupLight.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
    }

    public void save() {
        if(!profileName.getText().toString().equals("")) {
            curProfile.setName(profileName.getText().toString());
        }
        if(!coMin.getText().toString().equals("") && !coMax.getText().toString().equals("")) {
            curProfile.getCo2Boundaries().setMax(Double.parseDouble(coMax.getText().toString()));
            curProfile.getCo2Boundaries().setMin(Double.parseDouble(coMin.getText().toString()));
        }
        if(!humidityMin.getText().toString().equals("") && !humidityMax.getText().toString().equals("")) {
            curProfile.getHumidityBoundaries().setMax(Double.parseDouble(humidityMax.getText().toString()));
            curProfile.getHumidityBoundaries().setMin(Double.parseDouble(humidityMin.getText().toString()));
        }
        if(!lightMin.getText().toString().equals("") && !lightMax.getText().toString().equals("")) {
            curProfile.getLightBoundaries().setMax(Double.parseDouble(lightMax.getText().toString()));
            curProfile.getLightBoundaries().setMin(Double.parseDouble(lightMin.getText().toString()));
        }
        if(!tempMin.getText().toString().equals("") && !tempMax.getText().toString().equals("")) {
            curProfile.getTemperatureBoundaries().setMax(Double.parseDouble(tempMax.getText().toString()));
            curProfile.getTemperatureBoundaries().setMin(Double.parseDouble(tempMin.getText().toString()));
        }

        //TODO: Make validate on the data input
        if (curProfile.Validate(profileName.getText().toString())) {
            PlantProfileReponsitory.getInstance().saveProfileToApi(curProfile);
            PlantReponsitory.getInstance().UpdatePalnts(UserRepository.getInstance().getUserEmail());
            PlantProfileReponsitory.getInstance().UpdateProfiles(UserRepository.getInstance().getUserEmail());

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new PlantProfileFragment());
            fragmentTransaction.commit();
        }
        else {
            Toast.makeText(getContext(), "Missing infomation", Toast.LENGTH_SHORT).show();
        }
    }
}
