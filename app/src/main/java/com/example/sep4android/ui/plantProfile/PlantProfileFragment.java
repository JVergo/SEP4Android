package com.example.sep4android.ui.plantProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Adapter.ProfileAdapter;
import com.example.sep4android.MainActivity;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.Model.PlantProfileList;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantProfileReponsitory;
import com.example.sep4android.ViewModel.PlantProfileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlantProfileFragment extends Fragment implements ProfileAdapter.OnProfileListener {

    private PlantProfileViewModel plantProfileViewModel;
    RecyclerView mProfileList;
    RecyclerView.Adapter mProfileAdapter;
    ArrayList<PlantProfile> profiles = new ArrayList<>();;
    private View root;
    private FloatingActionButton btn;
    private String email= "naya7777@gmail.com";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       root = inflater.inflate(R.layout.fragment_plant_profile, container, false);


        mProfileAdapter = new ProfileAdapter(profiles, this);

        mProfileList = root.findViewById(R.id.rv);
        mProfileList.hasFixedSize();
        mProfileList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProfileList.setAdapter(mProfileAdapter);



        plantProfileViewModel = ViewModelProviders.of(this).get(PlantProfileViewModel.class);
        PlantProfileReponsitory.getInstance().getProfileFromApi(email);
        plantProfileViewModel.getPlantProfiles(email).observe(getActivity(), new Observer<PlantProfileList>() {
            @Override
            public void onChanged(PlantProfileList plantProfileList) {
                for(int i = 0;i<plantProfileList.size();i++){
                    profiles.add(plantProfileList.getProfile(i));
                }
                mProfileAdapter.notifyDataSetChanged();
            }
        });

        FloatButtonOnClick();

        return root;
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity)getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }*/

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

        profiles.get(position);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, ProfileDetails.newInstance(position));
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((MainActivity)getActivity()).onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}