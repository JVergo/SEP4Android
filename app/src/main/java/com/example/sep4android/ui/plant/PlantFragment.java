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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Adapter.PlantAdapter;
import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantList;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.ViewModel.PlantViewModel;
import com.example.sep4android.ui.PlantInfo.PlantInfoFragment;

import java.util.ArrayList;


public class PlantFragment extends Fragment implements PlantAdapter.OnPlantListener  {

    private PlantViewModel plantViewModel;
    RecyclerView mPlantList;
    RecyclerView.Adapter mPlantAdapter;
    ArrayList<Plant> plants = new ArrayList<>();
    private String email = "naya7777@gmail.com";
    private TextView textView;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        plantViewModel = ViewModelProviders.of(this).get(PlantViewModel.class);
        root = inflater.inflate(R.layout.fragment_plant, container, false);
        //test for list
        textView = root.findViewById(R.id.test);
        mPlantAdapter = new PlantAdapter(plants, this);


        mPlantList = root.findViewById(R.id.rv);
        mPlantList.hasFixedSize();
        mPlantList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlantList.setAdapter(mPlantAdapter);
        //final TextView textView = root.findViewById(R.id.text_plant);
        plantViewModel = ViewModelProviders.of(this).get(PlantViewModel.class);
      /*  TestReponsitory.getInstance().getTextFromApi();

        plantViewModel.getTest().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });*/
        PlantReponsitory.getInstance().getPlantFromApi(email);
        plantViewModel.getPlants(email).observe(getActivity(), new Observer<PlantList>() {
            @Override
            public void onChanged(PlantList plantList) {
                for(int i = 0;i<plantList.size();i++){
                    plants.add(plantList.getPlant(i));

                }
                mPlantAdapter.notifyDataSetChanged();
                /*Log.i("hello",plants.toString());*/

            }
        });
        return root;
    }



   /* @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity)getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }*/

    @Override
    public void onPlantClick(int position) {


       plants.get(position);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new PlantInfoFragment());
        fragmentTransaction.commit();

    }


}