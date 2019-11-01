package com.example.sep4android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private ArrayList<Profile> mprofiles;

    ProfileAdapter(ArrayList<Profile> profiles) {
        mprofiles = profiles;

    }

    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_profilelist_item, parent, false);
        return new ProfileAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(mprofiles.get(position).getName());
    }


    public int getItemCount() {
        return mprofiles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
        }
    }
}
