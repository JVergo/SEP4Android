package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.MainActivity;
import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.ServiceGenerator;
import com.example.sep4android.RDS.UserApi;
import com.example.sep4android.RDS.UserResponse;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ViewModel.CreatePlantProfileViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createPlantProfile extends Fragment {
    private LinearLayout groupProfile, groupCo2, groupHumidity, groupTemp, groupLight;
    TextView tempMin, tempMax, humidityMin, humidityMax, coMin, coMax, lightMin, lightMax, profileName;

    public static createPlantProfile newInstance() {
        return new createPlantProfile();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_plantprofile, container, false);

        groupProfile = root.findViewById(R.id.profileLayout);
        groupCo2 = root.findViewById(R.id.layoutco2);
        groupHumidity = root.findViewById(R.id.layouthumidity);
        groupTemp = root.findViewById(R.id.layouttemp);
        groupLight = root.findViewById(R.id.layoutlight);
        tempMin = root.findViewById(R.id.editTempMin);
        tempMax = root.findViewById(R.id.editTempMax);
        humidityMin = root.findViewById(R.id.editHumidityMin);
        humidityMax = root.findViewById(R.id.editHumidityMax);
        coMin = root.findViewById(R.id.editCo2Min);
        coMax = root.findViewById(R.id.editCo2Max);
        lightMin = root.findViewById(R.id.editLightMin);
        lightMax = root.findViewById(R.id.editLightMax);
        profileName = root.findViewById(R.id.editProfileName);

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());

        Button saveBTN = root.findViewById(R.id.btn_save);
        saveBTN.setOnClickListener(v -> save());

        onResume();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity)getActivity();
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CreatePlantProfileViewModel mViewModel = ViewModelProviders.of(this).get(CreatePlantProfileViewModel.class);
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

    public void save(){
        PlantProfile newPorfile = new PlantProfile();

        newPorfile.setName(profileName.getText().toString());

        newPorfile.setUserEmail(UserRepository.getInstance().getUserEmail());

        SensorBoundaries co2 = new SensorBoundaries(Double.parseDouble(coMin.getText().toString()), Double.parseDouble(coMax.getText().toString()));
        newPorfile.setCo2Boundaries(co2);

        SensorBoundaries humidity = new SensorBoundaries(Double.parseDouble(humidityMin.getText().toString()), Double.parseDouble(humidityMax.getText().toString()));
        newPorfile.setHumidityBoundaries(humidity);

        SensorBoundaries light = new SensorBoundaries(Double.parseDouble(lightMin.getText().toString()), Double.parseDouble(lightMax.getText().toString()));
        newPorfile.setLightBoundaries(light);

        SensorBoundaries temp = new SensorBoundaries(Double.parseDouble(tempMin.getText().toString()), Double.parseDouble(tempMax.getText().toString()));
        newPorfile.setTemperatureBoundaries(temp);

        PlantProfileReponsitory.getInstance().createProfile(newPorfile);
        PlantProfileReponsitory.getInstance().UpdateProfiles(UserRepository.getInstance().getUserEmail());
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new PlantProfileFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
