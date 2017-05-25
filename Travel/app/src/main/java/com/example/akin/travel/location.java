package com.example.akin.travel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.akin.travel.R;
import com.example.akin.travel.Locations.MyAdapter;
import com.example.akin.travel.Locations.locatin_collection;
import com.example.akin.travel.Locations.Item_click_listener;
import com.example.akin.travel.Locations.locatin_collection;
import com.example.akin.travel.Locations.MyAdapter;

public class location extends Fragment implements Item_click_listener {
    RecyclerView rv;
    MyAdapter adapter;

    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab1content,container,false);

        rv=(RecyclerView)v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        final MyAdapter adapter=new MyAdapter(getActivity(), locatin_collection.getLocations());

        rv.setAdapter(adapter);

        return v;
    }

    @Override
    public void onItemClick(View v, int pos) {

    }
}

