package com.example.sep4android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private ArrayList<Plant> mplants;
    private OnPlantListener mOnPlantListener;
    //Context mContext;

    public PlantAdapter(ArrayList<Plant> plants, OnPlantListener onPlantListener){
        mplants = plants;
        this.mOnPlantListener = onPlantListener;

    }



    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_plantlist_item, parent, false);
        return new ViewHolder(view, mOnPlantListener);

    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.name.setText(mplants.get(position).getPlantName());
        viewHolder.profile.setText(mplants.get(position).getProfile());

/*        viewHolder.temperatureData.setText((int) mplants.get(position).getTemperatureData());
        viewHolder.humidityData.setText((int) mplants.get(position).getHumidityData());
        viewHolder.lightData.setText( (int) mplants.get(position).getLightData());
        viewHolder.co2Data.setText( (int) mplants.get(position).getCo2Data());*/

        //data? should be set to int or?



    }

    public int getItemCount() {
        return mplants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name,profile,temperature,temperatureData,co2,co2Data,humidity,humidityData,light,lightData;

        LinearLayout parentLayout;

        OnPlantListener onPlantListener;

        ViewHolder(View itemView, OnPlantListener onplantListener) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            profile = itemView.findViewById(R.id.tv_profile);
            temperature = itemView.findViewById(R.id.tv_temp);
            temperatureData = itemView.findViewById(R.id.tv_tempdata);
            co2= itemView.findViewById(R.id.tv_co2);
            co2Data=itemView.findViewById(R.id.tv_co2data);
            humidity=itemView.findViewById(R.id.tv_humidity);
            humidityData = itemView.findViewById(R.id.tv_humiditydata);
            light = itemView.findViewById(R.id.tv_light);
            lightData = itemView.findViewById(R.id.tv_lightdata);

            parentLayout = itemView.findViewById(R.id.parent_layout);
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
