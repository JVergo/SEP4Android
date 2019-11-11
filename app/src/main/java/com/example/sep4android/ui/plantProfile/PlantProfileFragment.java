package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Profile;
import com.example.sep4android.ProfileAdapter;
import com.example.sep4android.R;

import java.util.ArrayList;

public class PlantProfileFragment extends Fragment implements ProfileAdapter.OnProfileListener {

    private PlantProfileViewModel plantProfileViewModel;
    RecyclerView mProfileList;
    RecyclerView.Adapter mProfileAdapter;
    ArrayList<Profile> profiles = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantProfileViewModel =
                ViewModelProviders.of(this).get(PlantProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_profile, container, false);

        profiles.add(new Profile("Rose"));
        profiles.add(new Profile("Jasmine"));
        profiles.add(new Profile("Violete"));
        profiles.add(new Profile("Rose"));

        mProfileAdapter = new ProfileAdapter(profiles,this);

        mProfileList = root.findViewById(R.id.rv);
        mProfileList.hasFixedSize();
        mProfileList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProfileList.setAdapter(mProfileAdapter);
        plantProfileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        return root;
    }

    @Override
    public void onProfileClick(int position) {

    }
}