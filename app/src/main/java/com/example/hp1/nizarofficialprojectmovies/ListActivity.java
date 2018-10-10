package com.example.hp1.nizarofficialprojectmovies;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.hp1.nizarofficialprojectmovies.R.*;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView lvCountries;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list);

        lvCountries = (ListView) findViewById(R.id.lvCountries);

        arrayList.add("Germany");
        arrayList.add("France");
        arrayList.add("Italy");

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        lvCountries.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String selectedItem = arrayList.get(position);
        if(selectedItem.equals("Germany")) {

        }
            if(position == 0) {

            }
    }
}
