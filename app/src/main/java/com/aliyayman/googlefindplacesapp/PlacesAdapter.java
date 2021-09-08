package com.aliyayman.googlefindplacesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.holderCard> {
    private Context mContext;
    private List<Places> placesList;

    public PlacesAdapter(Context mContext, List<Places> placesList) {
        this.mContext = mContext;
        this.placesList = placesList;
    }

    @NonNull
    @Override
    public holderCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.places_card_design,parent,false);
        return new holderCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderCard holder, int position) {
        Places place=placesList.get(position);
        holder.textViewName.setText(place.getName());
        holder.textViewLocation.setText(place.getLatitude()+"-"+place.getMeridian());
        holder.textViewAdress.setText(place.getAdress());

    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public class holderCard extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewLocation;
        private TextView textViewAdress;

        public holderCard(@NonNull View itemView) {
            super(itemView);
            textViewAdress=itemView.findViewById(R.id.textViewAdress);
            textViewLocation=itemView.findViewById(R.id.textViewLocation);
            textViewName=itemView.findViewById(R.id.textViewName);

        }
    }



}
