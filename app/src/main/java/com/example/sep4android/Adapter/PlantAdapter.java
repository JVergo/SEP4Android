package com.example.sep4android.Adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantData;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private ArrayList<Plant> mPlants;
    private OnPlantListener mOnPlantListener;

    double tempMax,tempMin,coMin,coMax,lightMin,lightMax,humidityMin,humidityMax;


    public PlantAdapter(ArrayList<Plant> plants, OnPlantListener onPlantListener) {
        mPlants = plants;
        this.mOnPlantListener = onPlantListener;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_plantlist_item, parent, false);
        return new ViewHolder(view, mOnPlantListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.name.setText(mPlants.get(position).getName());
        viewHolder.profile.setText(mPlants.get(position).getProfile().getName());

        viewHolder.temperatureData.setText("" + mPlants.get(position).getLastTemperatureMeasurement().getMeasurementValue());
        viewHolder.humidityData.setText("" + mPlants.get(position).getLastHumidityMeasurement().getMeasurementValue());
        viewHolder.lightData.setText("" + mPlants.get(position).getLastLightMeasurement().getMeasurementValue());
        viewHolder.co2Data.setText("" + mPlants.get(position).getLastCO2Measurement().getMeasurementValue());


        SetMinMax(tempMin, tempMax, mPlants.get(position).getProfile().getTemperatureBoundaries());
        SetMinMax(coMin, coMax, mPlants.get(position).getProfile().getCo2Boundaries());
        SetMinMax(humidityMin, humidityMax, mPlants.get(position).getProfile().getHumidityBoundaries());
        SetMinMax(lightMin, lightMax, mPlants.get(position).getProfile().getLightBoundaries());

        ValidatePlantData(mPlants.get(position).getLastTemperatureMeasurement(), mPlants.get(position).getProfile().getTemperatureBoundaries(), viewHolder.temperatureData);
        ValidatePlantData(mPlants.get(position).getLastCO2Measurement(), mPlants.get(position).getProfile().getCo2Boundaries(), viewHolder.co2Data);
        ValidatePlantData(mPlants.get(position).getLastHumidityMeasurement(), mPlants.get(position).getProfile().getHumidityBoundaries(), viewHolder.humidityData);
        ValidatePlantData(mPlants.get(position).getLastLightMeasurement(), mPlants.get(position).getProfile().getLightBoundaries(), viewHolder.lightData);

    }

    private void ValidatePlantData(PlantData p, SensorBoundaries sb, TextView tv)
    {
        if(p.getMeasurementValue() < sb.getMin() || p.getMeasurementValue() > sb.getMax())
        {
            tv.setTextColor(Color.parseColor("#B34F4B"));
        }
    }

    public int getItemCount() {
        return mPlants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, profile, temperatureData, co2Data, humidityData, lightData;

        OnPlantListener onPlantListener;

        ViewHolder(View itemView, OnPlantListener onplantListener) {
            super(itemView);
            name = itemView.findViewById(R.id.profileName);
            profile = itemView.findViewById(R.id.tv_profile);
            temperatureData = itemView.findViewById(R.id.tv_tempdata);
            co2Data=itemView.findViewById(R.id.tv_co2data);
            humidityData = itemView.findViewById(R.id.tv_humiditydata);
            lightData = itemView.findViewById(R.id.tv_lightdata);


            TextView tempCur = itemView.findViewById(R.id.tv_tempCurrent);
            TextView humidityCur = itemView.findViewById(R.id.tv_humidityCurrent);
            TextView lightCur = itemView.findViewById(R.id.tv_lightCurrent);
            TextView coCur = itemView.findViewById(R.id.tv_co2Current);
/*
            if (tempCur.getTextColors() == ColorStateList.valueOf(Color.parseColor("#B34F4B"))){
                temperatureData.setTextColor(Color.parseColor("#B34F4B"));
            }
            if (humidityCur.getTextColors() == ColorStateList.valueOf(Color.parseColor("#B34F4B"))){
                temperatureData.setTextColor(Color.parseColor("#B34F4B"));
            } if (lightCur.getTextColors() == ColorStateList.valueOf(Color.parseColor("#B34F4B"))){
                temperatureData.setTextColor(Color.parseColor("#B34F4B"));
            } if (coCur.getTextColors() == ColorStateList.valueOf(Color.parseColor("#B34F4B"))){
                temperatureData.setTextColor(Color.parseColor("#B34F4B"));
            }*/



            this.onPlantListener = onplantListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPlantListener.onPlantClick(getAdapterPosition());
        }
    }

    public interface OnPlantListener{
        void onPlantClick(int position);
    }



    public void SetMinMax(double min, double max, SensorBoundaries v) {
        min = Double.parseDouble(v.getMin().toString());
        max = Double.parseDouble(v.getMax().toString());
    }

}
