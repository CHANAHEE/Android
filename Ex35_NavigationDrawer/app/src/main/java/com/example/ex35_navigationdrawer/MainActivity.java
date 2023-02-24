package com.example.ex35_navigationdrawer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if( item.getItemId() == R.id.menu_gallery ){
                    Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();

                }else if( item.getItemId() == R.id.menu_send ){
                    Toast.makeText(MainActivity.this, "Send", Toast.LENGTH_SHORT).show();

                }else if( item.getItemId() == R.id.menu_aa ){
                    Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();

                }else if( item.getItemId() == R.id.menu_bb ){
                    Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();

                }

                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }
}