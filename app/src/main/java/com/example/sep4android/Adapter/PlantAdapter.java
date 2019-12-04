package com.example.sep4android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Model.Plant;
import com.example.sep4android.R;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private ArrayList<Plant> mPlants;
    private OnPlantListener mOnPlantListener;

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

        //ToDO: Add so the plant view is updated with current the data form the database see examples below

/*      viewHolder.temperatureData.setText(mPlants.get(position).getTemperature());
        viewHolder.humidityData.setText((int) mPlants.get(position).getHumidity());
        viewHolder.lightData.setText( (int) mPlants.get(position).getLight());
        viewHolder.co2Data.setText( (int) mPlants.get(position).getCo2());
*/
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
}
