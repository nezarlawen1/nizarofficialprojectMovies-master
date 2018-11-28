package com.example.hp1.nizarofficialprojectmovies;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ImageView profileImage;
    Bitmap bitmap;
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    new AllMoviesFragment()).commit();
            navigationView.setCheckedItem(R.id.Home_page);
        }


        profileImage = findViewById(R.id.profileImage);
        profileImage.setOnClickListener();

    }
        public String saveImage(Bitmap bitmap){
            File root = Environment.getExternalStorageDirectory();

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = root.getAbsolutePath()+"/DCIM/Camera/IMG_"+timeStamp+".jpg";
            File file = new File(filePath);
            try
            {
                file.createNewFile();
                FileOutputStream ostream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                ostream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(this ,"Failed to save image",Toast.LENGTH_SHORT).show();
            }
            return filePath;

         }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.Home_page :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomePageFragment()).commit();
                break;
            case R.id.all_movies :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllMoviesFragment()).commit();
                break;
            case R.id.top_10 :

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Top10Fragment()).commit();
                break;

            case R.id.search :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
                break;

            case R.id.Favorites :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoritesFragment()).commit();
                break;
            case R.id.recommended :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new recommendedFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
