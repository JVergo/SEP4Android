package com.example.sep4android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.Model.PlantProfile;
import com.example.sep4android.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private ArrayList<PlantProfile> mProfiles;
    private OnProfileListener mOnProfileListener;

    public ProfileAdapter(ArrayList<PlantProfile> profiles, OnProfileListener mOnProfileListener) {
        mProfiles = profiles;
        this.mOnProfileListener = mOnProfileListener;
    }

    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_profilelist_item, parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(mProfiles.get(position).getName());
    }


    public int getItemCount() {
        return mProfiles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnProfileListener.onProfileClick(getAdapterPosition());
        }

    }
    public interface OnProfileListener{
        void onProfileClick(int position);
    }
}