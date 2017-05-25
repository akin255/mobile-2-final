package com.example.akin.travel;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.example.akin.travel.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;



 public class message extends Fragment {
    private FloatingActionButton add_subject;
    private EditText name_subject;
    private ListView listView;
    private ArrayAdapter<String>arrayAdapter;
    private ArrayList<String>list_of_rooms=new ArrayList<>();
    private String name;
    DatabaseReference mRoofRef;
    SearchView sv;
    String userid;
    String user_name;
    FirebaseAuth firebaseAuth;
    private DatabaseReference root= FirebaseDatabase.getInstance().getReference().getRoot();
    public message(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2content, container, false);

        add_subject=(FloatingActionButton) rootView.findViewById(R.id.bn_konu);
        name_subject=(EditText)rootView.findViewById(R.id.edit_konu);
        user_name=FirebaseAuth.getInstance().getCurrentUser().getUid();
        //  FirebaseUser user1=firebaseAuth.getCurrentUser();
        listView=(ListView)rootView.findViewById(R.id.lv_message);
        name=FirebaseAuth.getInstance().getCurrentUser().getEmail();

        mRoofRef = FirebaseDatabase.getInstance().getReference();
        // Query lastQuery = mRoofRef.child("UserStyle").child(user1.getUid()).orderByKey().limitToLast(1);
        // lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
        //   @Override
        //   public void onDataChange(DataSnapshot dataSnapshot) {
        //     name=dataSnapshot.child("name").getValue().toString();
        // }

        // @Override
        // public void onCancelled(DatabaseError databaseError) {

        //    }
        // });//
        sv=(SearchView)rootView.findViewById(R.id.search_view);


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                arrayAdapter.getFilter().filter(text);
                return false;
            }
        });
        root= FirebaseDatabase.getInstance().getReference().child("message");
        arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.list_of_message,R.id.message_user,list_of_rooms);
        listView.setAdapter(arrayAdapter);

        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object>map=new HashMap<String,Object>();
                map.put(name_subject.getText().toString(),"");
                root.updateChildren(map);
            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String>set=new HashSet<String>();
                Iterator i=dataSnapshot.getChildren().iterator();
                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                list_of_rooms.clear();
                list_of_rooms.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                TextView txtview = ((TextView) view.findViewById(R.id.message_user));
                String room = txtview.getText().toString();
                Intent intent=new Intent(getActivity().getApplicationContext(), Chat_Room.class);
                intent.putExtra("room_name",room);
                intent.putExtra("user_name",name);
                startActivity(intent);
            }
        });



        return rootView;

    }}
