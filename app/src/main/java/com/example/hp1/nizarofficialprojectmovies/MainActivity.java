package com.example.hp1.nizarofficialprojectmovies;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener  {

    private static final int WRITE_REQUEST_CODE = 2;
    DrawerLayout drawer;
    ImageView profileImage;
    ImageButton moviePic;
    Bitmap bitmap;
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    private static final int NOTIFICATION_REMINDER_NIGHT = 2;
    private int requestCode;
    private int grantResults[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //if you dont have required permissions ask for it (only required for API 23+)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);


            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NowPlayingFragment()).commit();
                navigationView.setCheckedItem(R.id.Now_Playing);
            }

            View i = navigationView.getHeaderView(0);
            profileImage = i.findViewById(R.id.profileImage);
            moviePic = i.findViewById(R.id.moviePic);
            SharedPreferences pref = getSharedPreferences("Profile", MODE_PRIVATE);
            String name = pref.getString("image", "");
            if (!name.equals("")) {

                Bitmap myBitmap = BitmapFactory.decodeFile(name);
                profileImage.setImageBitmap(myBitmap);
            }

            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, CAMERA_REQUEST);
                }
            });

//        moviePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(),LoginActivity.class);
//                startActivity(intent);
//            }
//        });


            Intent notifyIntent = new Intent(this, MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast
                    (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                    1000 * 60 * 60 * 24, pendingIntent);


        }
    }
    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("","granted");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
                return;
            }

            // other 'case' line to check fosr other
            // permissions this app might request
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(photo);
            String file = saveImage(bitmap);

            SharedPreferences pref = getSharedPreferences("Profile",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("image",file);
            editor.commit();
        }
        else{
            if(requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK){
                Uri targetUri = data.getData();
                try{
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    profileImage.setImageBitmap(bitmap);
                    String file = saveImage(bitmap);

                    SharedPreferences pref = getSharedPreferences("Profile",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("image",file);
                    editor.commit();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }


    public String saveImage(Bitmap bitmap) {


        File root = Environment.getExternalStorageDirectory();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = root.getAbsolutePath() + "/DCIM/Camera/IMG_" + timeStamp + ".jpg";
        File file = new File(filePath);
        try {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("FILE",file.toString());
            Toast.makeText(this, file + " Failed to save image", Toast.LENGTH_LONG).show();
        }
        return filePath;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent goToNextActivity = new Intent(getApplicationContext(), AlertDialogActivity.class);
        Intent goToNextActivity1 = new Intent(getApplicationContext(), AboutActivity.class);

        switch (item.getItemId()) {
            case R.id.action_settings:
                goToNextActivity = new Intent(getApplicationContext(), AlertDialogActivity.class);
                startActivity(goToNextActivity);
                break;
            case R.id.about:
                goToNextActivity = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(goToNextActivity1);
                break;
            case R.id.sign_out:
                goToNextActivity = new Intent(getApplicationContext(), AlertDialogActivity.class);
                startActivity(goToNextActivity);
                break;
        }
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.Now_Playing:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NowPlayingFragment()).commit();
                break;
            case R.id.Popular_Movies:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PopularMoviesFragment()).commit();
                break;
            case R.id.top_Rated:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TopRatedFragment()).commit();
                break;

            case R.id.Search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
                break;

            case R.id.Favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoritesFragment()).commit();
                break;
            case R.id.Up_Coming:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new upcomingFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
