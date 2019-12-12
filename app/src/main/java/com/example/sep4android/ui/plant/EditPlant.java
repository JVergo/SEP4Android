package com.example.sep4android.ui.plant;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.ViewModel.EditPlantViewModel;

public class EditPlant extends Fragment {

    TextView plantName;
    Spinner profile;

    public static EditPlant newInstance() {
        return new EditPlant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_plant, container, false);

        plantName = root.findViewById(R.id.editPalntName);
        profile = root.findViewById(R.id.spinnerprofile);

        Button saveBTN = root.findViewById(R.id.btn_save);
        saveBTN.setOnClickListener( v ->  save());

        return root;
    }

    private void save() {
        Plant temp = new Plant();
        temp.setName(plantName.getText().toString());
        //temp.setProfile(profile.getSelectedItem());

        PlantReponsitory.getInstance().savePlantToApi(temp);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditPlantViewModel mViewModel = ViewModelProviders.of(this).get(EditPlantViewModel.class);

    }



}
