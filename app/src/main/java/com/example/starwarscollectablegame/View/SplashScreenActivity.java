package com.example.starwarscollectablegame.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;

import java.util.List;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getBoolean(getString(R.string.preferences_database_loaded), false)) {
            final StarWarsDataRepository repository = new StarWarsDataRepository(getApplication());
            final LiveData<List<Film>> data =  repository.getAllFilms();
            final LifecycleOwner owner = this;
            final Context context = this;
            Log.wtf(TAG, sharedPref.getBoolean(getString(R.string.preferences_database_loaded), false) + " = databaseLoaded");
            data.observe(owner, new Observer<List<Film>>() {
                @Override
                public void onChanged(List<Film> films) {
                    if (films.isEmpty()) {
                        repository.clearDatabase();
                        StarWarsDataRepository.fillDatabase(repository, context);
                    } else {
                        editor.putBoolean(getString(R.string.preferences_database_loaded), true);
                        data.removeObservers(owner);
                    }
                }
            });
        }

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withFooterText(getString(R.string.splashscreen_footer))
//                .withHeaderText(getString(R.string.app_name))
                .withBeforeLogoText(getString(R.string.app_name))
                //.withAfterLogoText(getString(R.string.splashscreen_bottomlogo))
                .withLogo(R.mipmap.ic_launcher_round);
        config.getFooterTextView().setTextColor(Color.WHITE);

//        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
//        config.getAfterLogoTextView().setTextColor(Color.WHITE);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);


    }
}
