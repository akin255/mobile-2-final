package com.example.akin.travel;


import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import com.example.akin.travel.Chat;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.security.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Chat_Room extends AppCompatActivity {

    private FloatingActionButton btn_send_msg;
    private EditText input_msg;
    private TextView chat_conversation;
    private String user_name,room_name;
    private DatabaseReference root,myREF;
    private String userId;
    private String temp_key;
    private FirebaseListAdapter<Chat>adapter;
    String userdisplayename;
    FirebaseAuth firebaseAuth;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);
        btn_send_msg=(FloatingActionButton) findViewById(R.id.btn_send);
        input_msg=(EditText) findViewById(R.id.msg_input);

        // FirebaseUser user=firebaseAuth.getCurrentUser();
        userdisplayename=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        user_name=getIntent().getExtras().get("user_name").toString();
        room_name=getIntent().getExtras().get("room_name").toString();
        userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase mFirebaseDatabase=FirebaseDatabase.getInstance();
        myREF=mFirebaseDatabase.getReference();
        myREF.child("message").orderByChild("timestamp").limitToLast(10);
        DatabaseReference myRef=mFirebaseDatabase.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        root= FirebaseDatabase.getInstance().getReference().child("message").child(room_name);
        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object>map=new HashMap<String,Object>();
                temp_key=root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root=root.child(temp_key);
                Map<String,Object>map2=new HashMap<String,Object>();
                map2.put("name",user_name);
                map2.put("msg",input_msg.getText().toString());
                map2.put("time", ServerValue.TIMESTAMP);

                message_root.updateChildren(map2);



            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                displayChatMessage();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                displayChatMessage();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void displayChatMessage() {
        final ListView list_of_messages=(ListView)findViewById(R.id.lv_chat);
        adapter=new FirebaseListAdapter<Chat>
                (this,Chat.class,R.layout.item_chat_room,root= FirebaseDatabase.getInstance().getReference().child("message").child(room_name)) {
            @Override
            protected void populateView(View v, Chat model, int position) {
                TextView messageText,messageUser,messageTime;
                messageUser=(TextView)v.findViewById(R.id.tvUser);
                messageTime=(TextView)v.findViewById(R.id.tvTime);
                messageText=(TextView)v.findViewById(R.id.tvMessage);
                messageText.setText(model.getMsg());
                messageUser.setText(model.getName());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getTime()));



            }
        };

        list_of_messages.setAdapter(adapter);




    }



}
