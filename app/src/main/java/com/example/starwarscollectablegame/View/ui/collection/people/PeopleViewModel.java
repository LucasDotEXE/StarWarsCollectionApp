package com.example.starwarscollectablegame.View.ui.collection.people;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.PersonColleciton;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;

import java.util.List;

public class PeopleViewModel extends AndroidViewModel {

    private StarWarsDataRepository starWarsDataRepository;
//    private PlayerCollectionDataRepository collectionDataRepository;

    private LiveData<List<People>> allFilms;
    private LiveData<List<PersonColleciton>> filmCollection;

    private PeopleAdapter peopleAdapter;

    public PeopleViewModel(@NonNull Application application) {
        super(application);
        this.starWarsDataRepository = new StarWarsDataRepository(application);
//        this.collectionDataRepository = new PlayerCollectionDataRepository(application);
        this.allFilms = this.starWarsDataRepository.getAllPeople();
        this.filmCollection = this.starWarsDataRepository.getPeopleCollection();

        this.peopleAdapter = new PeopleAdapter(starWarsDataRepository);

    }

    public LiveData<List<People>> getAllFilms() {
        return allFilms;
    }

    public LiveData<List<PersonColleciton>> getFilmCollection() {
        return filmCollection;
    }

    public StarWarsDataRepository getStarWarsDataRepository() {
        return starWarsDataRepository;
    }

//    public PlayerCollectionDataRepository getCollectionDataRepository() {
//        return collectionDataRepository;
//    }

    public void reloadList(List<PersonColleciton> filmCollections) {
        peopleAdapter.setPeopleCollection(filmCollections);
        peopleAdapter.notifyDataSetChanged();
    }

    public PeopleAdapter getPeopleAdapter() {
        return peopleAdapter;
    }


}