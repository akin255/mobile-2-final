package com.example.akin.travel.Locations;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import com.example.akin.travel.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {
    Context c;
    ArrayList<locations> locations;
    LayoutInflater inflater;
    ArrayList<locations> filterList;

    public MyAdapter(Context c,ArrayList<locations>Locations){
        this.c=c;
        this.locations= Locations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.model_location,parent,false);

        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       locations row=locations.get(position);
        final String name=locations.get(position).getName();
        final String exp=locations.get(position).getExplanation();
        final int img=locations.get(position).getImage();

        holder.nametxt.setText(name);
        holder.explanation.setText(exp);
        holder.img.setImageResource(img);


    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }
    private void openDetailActity(String name,String exp,int img){
        Intent i=new Intent(c,location_type_1.class);

        c.startActivity(i);



    }
}
