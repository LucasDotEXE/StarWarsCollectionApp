package com.example.starwarscollectablegame.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.starwarscollectablegame.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean(getString(R.string.prefrences_theme_use_sith), true).apply();
        setTheme(sharedPref.getBoolean(getString(R.string.preferences_theme_use_sith), false) ? R.style.AppThemeSith_NoActionBar : R.style.AppThemeJedi_NoActionBar );

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_map, R.id.nav_collection, R.id.nav_profile, R.id.nav_settings,
                R.id.nav_film, R.id.nav_people, R.id.nav_starships, R.id.nav_vehiles)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        if (sharedPref.getBoolean(getString(R.string.preferences_theme_open_settings), false)) {
            navController.navigate(R.id.nav_settings);
            editor.putBoolean(getString(R.string.preferences_theme_open_settings), false).apply();
        } else {
            navController.navigate(R.id.nav_map);
        }
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
