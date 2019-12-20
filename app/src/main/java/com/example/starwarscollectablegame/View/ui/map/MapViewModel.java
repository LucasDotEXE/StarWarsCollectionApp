package com.example.starwarscollectablegame.View.ui.map;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.starwarscollectablegame.Model.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;

import java.util.List;

public class MapViewModel extends AndroidViewModel {

//    private PlayerCollectionDataRepository repository;
    private StarWarsDataRepository helperRepo;

    private LiveData<List<FilmCollection>> filmCollection;
    private LiveData<List<Film>> allFilms;

    public MapViewModel(@NonNull Application application) {
        super(application);
//        this.repository = new PlayerCollectionDataRepository(application);
        this.helperRepo = new StarWarsDataRepository(application);

        filmCollection = this.helperRepo.getFilmCollection();
        allFilms = helperRepo.getAllFilms();
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
}
