package com.example.starwarscollectablegame.View.ui.map;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.Model.GeoFencing.GeoFenceHandler;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapViewModel extends AndroidViewModel {

//    private PlayerCollectionDataRepository repository;
    public int markerCounter = 0;
    public Map<String, Marker> markers = new HashMap<>();

    private GeoFenceHandler geoFenceHandler;
    private StarWarsDataRepository helperRepo;

    private LiveData<List<FilmCollection>> filmCollection;
    private LiveData<List<Film>> allFilms;

    public MapViewModel(@NonNull Application application) throws InterruptedException {
        super(application);
//        this.repository = new PlayerCollectionDataRepository(application);
        this.helperRepo = new StarWarsDataRepository(application);
        /*helperRepo.getAllPlayerData().observeForever(new Observer<List<PlayerData>>() {
            @Override
            public void onChanged(List<PlayerData> playerData) {
                Intent openNewPlayerActivity = new Intent(application.getApplicationContext(), AddPlayerActivity.class);
                openNewPlayerActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                application.getApplicationContext().startActivity(openNewPlayerActivity);
            }
        });*/
        {
           /* PlayerData playerData = new PlayerData("Default", R.raw.icon_luke_skywalker);
            helperRepo.getAllPlayerData().getValue().add(playerData);
            helperRepo.*/

        }


        filmCollection = this.helperRepo.getFilmCollection();
        allFilms = helperRepo.getAllFilms();
        this.geoFenceHandler = new GeoFenceHandler(this.getApplication().getApplicationContext(), this);
        this.getApplication().registerReceiver(geoFenceHandler, new IntentFilter(Intent.ACTION_RUN)); // either this or manifest..
    }

    public LiveData<List<Film>> getAllFilms() {
        return allFilms;
    }

    public LiveData<List<FilmCollection>> getFilmCollection() {
        return filmCollection;
    }

    public void insertfilmCollection(FilmCollection collection) {
        this.helperRepo.filmCollectionDatabaseEditHelper.insert(collection);
    }

    public void updateFilmColletion(FilmCollection collection) {
        this.helperRepo.filmCollectionDatabaseEditHelper.update(collection);
    }

    public StarWarsDataRepository getHelperRepo() {
        return helperRepo;
    }

    public GeoFenceHandler getGeoFenceHandler() {
        return geoFenceHandler;
    }

    public int getMarkerCounter() {
        return markerCounter;
    }

    public void setMarkerCounter(int markerCounter) {
        this.markerCounter = markerCounter;
    }
}
