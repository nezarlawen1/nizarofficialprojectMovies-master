package com.example.hp1.nizarofficialprojectmovies;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.StringReader;

public class SharedActivity extends AppCompatActivity implements View.OnClickListener {
    Button btSave;
    EditText etNamne;
    TextView tvName;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        btSave = (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(this);
        etNamne = (EditText) findViewById(R.id.etName);

        SharedPreferences pref = getSharedPreferences("Profile",MODE_PRIVATE);
        String name=pref.getString("name",null);

        tvName = (TextView) findViewById(R.id.tvName);
        if (name !=null){
            tvName.setText(name);
        }
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences("Profile",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name",etNamne.getText().toString());
        editor.commit();
    }
}
