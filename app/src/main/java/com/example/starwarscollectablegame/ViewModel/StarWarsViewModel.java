package com.example.starwarscollectablegame.ViewModel;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;

import java.util.List;

public class StarWarsViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;
    private LiveData<List<Film>> allFilms;

    public StarWarsViewModel(@NonNull Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);
        this.allFilms = repository.getAllFilms();
    }

    public void insert(Film film) {
        this.repository.insertFilm(film);
    }

    public void update(Film film) {
        this.repository.updateFilm(film);
    }

    public void delete(Film film) {
        this.repository.deleteFilm(film);
    }

    public LiveData<List<Film>> getAllFilms() {
        return allFilms;
    }
}
