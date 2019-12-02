package com.example.sep4android.ui.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.R;
import com.example.sep4android.ViewModel.CreatePlantViewModel;

public class CreatePlant extends Fragment {

    private CreatePlantViewModel mViewModel;

    private View root;
    private Button clrbtn;

    EditText plantName;

    public static CreatePlant newInstance() {
        return new CreatePlant();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_create_plant, container, false);

        clrbtn = root.findViewById(R.id.button_clear);
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });

        plantName = root.findViewById(R.id.editText_plantename);

        return root;
    }


    public void clearText() {

        plantName.setText("");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreatePlantViewModel.class);
        // TODO: Use the ViewModel
    }

}
