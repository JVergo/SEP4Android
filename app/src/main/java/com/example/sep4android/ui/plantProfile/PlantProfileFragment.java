package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Model.Profile;
import com.example.sep4android.Adapter.ProfileAdapter;
import com.example.sep4android.R;
import com.example.sep4android.ViewModel.PlantProfileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlantProfileFragment extends Fragment implements ProfileAdapter.OnProfileListener {

    private PlantProfileViewModel plantProfileViewModel;
    RecyclerView mProfileList;
    RecyclerView.Adapter mProfileAdapter;
    ArrayList<Profile> profiles = new ArrayList<>();
    private View root;
    private FloatingActionButton btn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantProfileViewModel =
                ViewModelProviders.of(this).get(PlantProfileViewModel.class);
       root = inflater.inflate(R.layout.fragment_plant_profile, container, false);

        profiles.add(new Profile("Rose"));
        profiles.add(new Profile("Jasmine"));
        profiles.add(new Profile("Violete"));
        profiles.add(new Profile("Rose"));
        profiles.add(new Profile("Jasmine"));
        profiles.add(new Profile("Violete"));
        profiles.add(new Profile("Rose"));
        profiles.add(new Profile("Jasmine"));
        profiles.add(new Profile("Violete"));
        profiles.add(new Profile("Rose"));

        mProfileAdapter = new ProfileAdapter(profiles, this);

        mProfileList = root.findViewById(R.id.rv);
        mProfileList.hasFixedSize();
        mProfileList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProfileList.setAdapter(mProfileAdapter);
        plantProfileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        FloatButtonOnClick();

        return root;
    }

    public void FloatButtonOnClick(){
        btn = (FloatingActionButton) root.findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new createPlantProfile());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onProfileClick(int position) {

    }
}