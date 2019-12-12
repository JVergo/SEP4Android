package com.example.sep4android.ui.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.ViewModel.CreatePlantViewModel;

public class CreatePlant extends Fragment {
    EditText plantName;
    Spinner profile;

    public static CreatePlant newInstance() {
        return new CreatePlant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_plant, container, false);

        Button clearBTN = root.findViewById(R.id.button_clear);
        clearBTN.setOnClickListener(v -> clearText());

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


    public void clearText() {
        plantName.setText("");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CreatePlantViewModel mViewModel = ViewModelProviders.of(this).get(CreatePlantViewModel.class);
    }
}
