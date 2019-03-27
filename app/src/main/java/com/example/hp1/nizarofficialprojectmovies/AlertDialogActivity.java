package com.example.hp1.nizarofficialprojectmovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }


    /**
     * creates the alert dialog with yes and no answers
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure");

        builder.setCancelable(true);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(AlertDialogActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        builder.setNegativeButton("NO",this);

        final AlertDialog dialog=builder.create();
        dialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });

        dialog.show();
    }

    /**
     * messages when clicking on yes or no
     * @param dialogInterface
     * @param i
     */
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