package com.example.hp1.nizarofficialprojectmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure");

        builder.setCancelable(false);

        builder.setPositiveButton("YES",this);

        builder.setNegativeButton("NO",this);

        AlertDialog dialog=builder.create();

        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i==dialogInterface.BUTTON_POSITIVE)
        {
            Toast.makeText(this,"YES",Toast.LENGTH_SHORT).show();
        }
        if(i==dialogInterface.BUTTON_NEGATIVE)
        {
            Toast.makeText(this,"NO",Toast.LENGTH_SHORT).show();
        }



    }

}