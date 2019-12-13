package com.example.sep4android.ui.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Adapter.PlantAdapter;
import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.ViewModel.PlantViewModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class PlantFragment extends Fragment implements PlantAdapter.OnPlantListener {
    private RecyclerView mPlantList;
    private RecyclerView.Adapter mPlantAdapter;
    private ArrayList<Plant> plants = new ArrayList<>();
    private ArrayList<PlantProfile> profiles = new ArrayList<>();

    FloatingActionButton createPlantBTN;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PlantViewModel plantViewModel = ViewModelProviders.of(this).get(PlantViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant, container, false);

        TextView textView = root.findViewById(R.id.test);
        mPlantAdapter = new PlantAdapter(plants, this);

        createPlantBTN = root.findViewById(R.id.floatingActionButton);

        mPlantList = root.findViewById(R.id.rv);
        mPlantList.hasFixedSize();
        mPlantList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlantList.setAdapter(mPlantAdapter);
        plantViewModel = ViewModelProviders.of(this).get(PlantViewModel.class);



        if (PlantReponsitory.getInstance().getPlants() == null) {
            String email = UserRepository.getInstance().getUserEmail();
            PlantReponsitory.getInstance().getPlantFromApi(email);
        }
        plantViewModel.getPlants().observe(getActivity(), plantList -> {
            for (int i = 0; i < plantList.size(); i++) {
                plants.add(plantList.getPlant(i));

            }
            mPlantAdapter.notifyDataSetChanged();
        });

        FloatButtonOnClick();
        return root;
    }

    @Override
    public void onPlantClick(int position) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, PlantDetails.newInstance(position));
        fragmentTransaction.commit();
    }

    public void FloatButtonOnClick() {
        createPlantBTN.setOnClickListener(v -> {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new CreatePlant());
            fragmentTransaction.commit();
        });
    }




}