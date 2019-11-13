package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.R;

public class createPlantProfile extends Fragment {

    private CreatePlantProfileViewModel mViewModel;
    private View root;
    private Button clrbtn;

    public static createPlantProfile newInstance() {
        return new createPlantProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_create_plantprofile, container, false);

        clrbtn = root.findViewById(R.id.button_clear);
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreatePlantProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    public void clearText(){

        LinearLayout group_profile = (LinearLayout) root.findViewById(R.id.profileLayout);
        for (int i = 0, count = group_profile.getChildCount(); i < count; ++i) {
            View view = group_profile.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
        LinearLayout group_co2 = (LinearLayout) root.findViewById(R.id.layoutco2);
        for (int i = 0, count = group_co2.getChildCount(); i < count; ++i) {
            View view = group_co2.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }

        LinearLayout group_humidity = (LinearLayout) root.findViewById(R.id.layouthumidity);
        for (int i = 0, count = group_humidity.getChildCount(); i < count; ++i) {
            View view = group_humidity.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }

        LinearLayout group_temp = (LinearLayout) root.findViewById(R.id.layouttemp);
        for (int i = 0, count = group_temp.getChildCount(); i < count; ++i) {
            View view = group_temp.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }

        LinearLayout group_light = (LinearLayout) root.findViewById(R.id.layoutlight);
        for (int i = 0, count = group_light.getChildCount(); i < count; ++i) {
            View view = group_light.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
    }

    public void save(){

    }

}
