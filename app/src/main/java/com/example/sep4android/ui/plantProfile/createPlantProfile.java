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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.MainActivity;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.RDS.ServiceGenerator;
import com.example.sep4android.RDS.UserApi;
import com.example.sep4android.RDS.UserResponse;
import com.example.sep4android.ViewModel.CreatePlantProfileViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createPlantProfile extends Fragment {
    private LinearLayout groupProfile, groupCo2, groupHumidity, groupTemp, groupLight;

    CreatePlantProfileViewModel viewModel;
    private View root;

    private PlantProfile cProfile;

    TextView tempMin;
    TextView tempMax;
    TextView humidityMin;
    TextView humidityMax;
    TextView coMin;
    TextView coMax;
    TextView lightMin;
    TextView lightMax;
    TextView profileName;

    public static createPlantProfile newInstance() {
        return new createPlantProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create_plantprofile, container, false);

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


        onResume();
        return root;
    }


    public void onViewCreated (View view,Bundle savedInstanceState){

        viewModel = ViewModelProviders.of(this).get(CreatePlantProfileViewModel.class);

        if (PlantProfileReponsitory.getInstance().getProfiles() == null) {
            String email = "1";
            PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        }
        viewModel.saveProfile().observe(getActivity(), new Observer<PlantProfile>() {
            @Override
            public void onChanged(PlantProfile plantProfile) {
                sendProfile("1");
            }
        });

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());
        Button saveBtn = root.findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(v -> save());

    }

    public void sendProfile(String email){
        UserApi userApi = ServiceGenerator.getUserApi();
        Call<UserResponse> call = userApi.userInfo(email);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    response.body().getUser().getProfiles().addPlantProfile(cProfile);

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
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

        cProfile.setName(profileName.getText().toString());

        cProfile.getCo2().setMax(Double.parseDouble(coMax.getText().toString()));
        cProfile.getCo2().setMin(Double.parseDouble(coMin.getText().toString()));
        cProfile.getHumidity().setMax(Double.parseDouble(humidityMax.getText().toString()));
        cProfile.getHumidity().setMin(Double.parseDouble(humidityMin.getText().toString()));
        cProfile.getLight().setMax(Double.parseDouble(lightMax.getText().toString()));
        cProfile.getLight().setMin(Double.parseDouble(lightMin.getText().toString()));
        cProfile.getTemperature().setMax(Double.parseDouble(tempMax.getText().toString()));
        cProfile.getTemperature().setMin(Double.parseDouble(tempMin.getText().toString()));

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
