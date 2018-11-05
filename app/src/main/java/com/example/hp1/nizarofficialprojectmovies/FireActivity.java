package com.example.hp1.nizarofficialprojectmovies;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
        final DatabaseReference myRef = database.getReference().child("name");

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        btSave = (Button)findViewById(R.id.btSave);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvProfession = (TextView)findViewById(R.id.tvProfession);
        lvUsers = findViewById(R.id.lvUsers);
        Users = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter(this ,android.R.layout.simple_list_item_1,Users);
        lvUsers.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tvEmail.setText(value);
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
