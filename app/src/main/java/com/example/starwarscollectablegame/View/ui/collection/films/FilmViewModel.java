package com.example.starwarscollectablegame.View.ui.collection.films;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;

import java.util.List;

public class FilmViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;

    private MutableLiveData<String> mText;
    private LiveData<List<Film>> allFilms;

    public FilmViewModel(@NonNull Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);
        this.allFilms =
        this.allFilms = repository.getAllFilms();



        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Film>> getAllFilms() {
        return allFilms;
    }
}