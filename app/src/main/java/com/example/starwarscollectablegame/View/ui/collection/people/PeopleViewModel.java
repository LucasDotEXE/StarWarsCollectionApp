package com.example.starwarscollectablegame.View.ui.collection.people;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;

import java.util.List;

public class PeopleViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;

    private MutableLiveData<String> mText;
    private LiveData<List<People>> allPeople;

    public PeopleViewModel(@NonNull Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);
        this.allPeople = repository.getAllPeople();

        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<People>> getAllPeople() {
        return allPeople;
    }
}