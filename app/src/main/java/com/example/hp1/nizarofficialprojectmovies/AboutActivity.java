package com.example.hp1.nizarofficialprojectmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView tvAboutApp , tvAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tvAboutApp = findViewById(R.id.tvAboutApp);
        tvAbout = findViewById(R.id.tvAbout);
    }
}
