package com.example.akin.travel.Locations;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.akin.travel.R;
import com.example.akin.travel.Locations.Item_click_listener;
import com.example.akin.travel.Locations.location_type_1;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView img;
    TextView nametxt;
    TextView explanation;
    Item_click_listener itemClickListener1;
    private RelativeLayout main;


    public MyViewHolder(View v) {
        super(v);
        img=(ImageView) v.findViewById(R.id.karinImage);
        nametxt=(TextView)v.findViewById(R.id.textname_karin);
        explanation=(TextView)v.findViewById(R.id.text_explain);
        main=(RelativeLayout)v.findViewById(R.id.rl);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemClickListener1!=null){
                    itemClickListener1.onItemClick(v,getAdapterPosition());
                }
                if(getPosition()==0) {
                    Intent intent = new Intent(v.getContext(),location_type_1.class);
                    v.getContext().startActivity(intent);

                }else if(getPosition()==1) {
                    Intent intent = new Intent(v.getContext(), location_type_2.class);
                    v.getContext().startActivity(intent);

                }else if(getPosition()==2) {
                    Intent intent = new Intent(v.getContext(),location_type_3.class);
                    v.getContext().startActivity(intent);

                } else if(getPosition()==3) {
                    Intent intent = new Intent(v.getContext(), location_type_4.class);
                    v.getContext().startActivity(intent);}
                else if(getPosition()==4) {
                    Intent intent = new Intent(v.getContext(), location_type_5.class);
                    v.getContext().startActivity(intent);
                }  else if(getPosition()==5) {
                    Intent intent = new Intent(v.getContext(), location_type_6.class);
                    v.getContext().startActivity(intent);
                }
                else if(getPosition()==6) {
                    Intent intent = new Intent(v.getContext(), location_type_7.class);
                    v.getContext().startActivity(intent);
                }
                else if(getPosition()==7) {
                    Intent intent = new Intent(v.getContext(), location_type_8.class);
                    v.getContext().startActivity(intent);
                }  else if(getPosition()==8) {
                    Intent intent = new Intent(v.getContext(), location_type_9.class);
                    v.getContext().startActivity(intent);
                }

            }
        });
        v.setOnClickListener(this);}




    public void setItemClickListener(Item_click_listener ic){
        this.itemClickListener1=ic;

    }

    @Override
    public void onClick(View v) {

    }
}