package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.MainActivity;
import com.example.sep4android.R;
import com.example.sep4android.ViewModel.CreatePlantProfileViewModel;

public class createPlantProfile extends Fragment {
    private LinearLayout groupProfile, groupCo2, groupHumidity, groupTemp, groupLight;

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

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());

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
