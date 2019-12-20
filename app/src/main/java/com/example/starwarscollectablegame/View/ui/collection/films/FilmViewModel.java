package com.example.starwarscollectablegame.View.ui.collection.films;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.starwarscollectablegame.Model.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;

import java.util.List;

public class FilmViewModel extends AndroidViewModel {

    private StarWarsDataRepository starWarsDataRepository;
//    private PlayerCollectionDataRepository collectionDataRepository;

    private LiveData<List<Film>> allFilms;
    private LiveData<List<FilmCollection>> filmCollection;

    private FilmAdapter filmAdapter;

    public FilmViewModel(@NonNull Application application) {
        super(application);
        this.starWarsDataRepository = new StarWarsDataRepository(application);
//        this.collectionDataRepository = new PlayerCollectionDataRepository(application);
        this.allFilms = this.starWarsDataRepository.getAllFilms();
        this.filmCollection = this.starWarsDataRepository.getFilmCollection();

        this.filmAdapter = new FilmAdapter(starWarsDataRepository);

    }

    public LiveData<List<Film>> getAllFilms() {
        return allFilms;
    }

    public LiveData<List<FilmCollection>> getFilmCollection() {
        return filmCollection;
    }

    public StarWarsDataRepository getStarWarsDataRepository() {
        return starWarsDataRepository;
    }

//    public PlayerCollectionDataRepository getCollectionDataRepository() {
//        return collectionDataRepository;
//    }

    public void reloadList(List<FilmCollection> filmCollections) {
        filmAdapter.setFilmCollections(filmCollections);
        filmAdapter.notifyDataSetChanged();
    }

    public FilmAdapter getFilmAdapter() {
        return filmAdapter;
    }


}