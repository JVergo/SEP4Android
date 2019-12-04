package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.R;
import com.example.sep4android.ViewModel.EditPlantProfileViewModel;

public class editPlantProfile extends Fragment {

    private EditPlantProfileViewModel mViewModel;

    public static editPlantProfile newInstance() {
        return new editPlantProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_plantprofile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditPlantProfileViewModel.class);
    }

    public void clearText(){
    }

    public void save(){
    }
}
