package com.example.feelhut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.feelhut.fragments.DiaryFragment;
import com.example.feelhut.fragments.HomeFragment;
import com.example.feelhut.fragments.NotificationsFragment;
import com.example.feelhut.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    FrameLayout activitymainfframelayout;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);/* for hamburger sign in toolbar, these three lines use*/
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        activitymainfframelayout = findViewById(R.id.activitymainframe);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener((bottomnavigationviewlistener));

        setfragment(new HomeFragment());

    }

    private void setfragment(Fragment myfragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activitymainframe, myfragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener bottomnavigationviewlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment mefragment;
                switch (item.getItemId()) {
                    case R.id.home:
                        setTitle("Home");
                        mefragment = new HomeFragment();
                        loadfragment(mefragment);
                        return true;  // jekar ithe break use krda taa bottom nav highlight nhi hunda
                    case R.id.diary:
                        setTitle("Diary");
                        mefragment = new DiaryFragment();
                        loadfragment(mefragment);
                        return true;
                    case R.id.notification:
                        setTitle("Notifications");
                        mefragment = new NotificationsFragment();
                        loadfragment(mefragment);
                        return true;
                    case R.id.Profile:
                        setTitle("Profile");
                        mefragment = new ProfileFragment();
                        loadfragment(mefragment);
                        return true;
                }
                return false;
            }

            private void loadfragment(Fragment myfragment) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activitymainframe, myfragment);

                transaction.commit();
            }


        };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            default:
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
