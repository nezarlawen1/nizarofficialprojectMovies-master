package com.example.hp1.nizarofficialprojectmovies;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class FireActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail,etPass ;
    Button btSave ;
    TextView tvEmail , tvProfession ;
    ListView lvUsers;
    ArrayList<String>Users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        btSave = (Button)findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                myRef.child("name").setValue(email);
                myRef.child("profession").setValue("Student");
                myRef.child("name").push().setValue(null);
            }
        });
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvProfession = (TextView)findViewById(R.id.tvProfession);
        lvUsers = findViewById(R.id.lvUsers);
        Users = new ArrayList<String>();
        final ArrayAdapter<String > adapter = new ArrayAdapter(this ,android.R.layout.simple_list_item_1,Users);
        lvUsers.setAdapter(adapter);

        myRef.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                Log.v("E_VALUE","Data: "+ dataSnapshot.getValue());
                String name = map.get("name");
                String profession = map.get("profession");
                tvEmail.setText(name);
                tvProfession.setText(profession);

                String value = dataSnapshot.getValue(String.class);
                tvEmail.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        myRef.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.getValue(String.class);
                Users.add(name);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
