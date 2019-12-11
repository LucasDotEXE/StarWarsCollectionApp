package com.example.starwarscollectablegame.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.View.DetailActivity;

import java.util.Objects;

public class PeopleFragmentViewModel extends AndroidViewModel {

    private LiveData<People> people;
    StarWarsDataRepository repo;

    public LiveData<People> getPeople(String name)
    {
        if (people==null)
        {
            this.people = repo.getPeopleByName(name);
        }
        return people;
    }

    public PeopleFragmentViewModel(@NonNull DetailActivity application) {
        super(application);
        this.repo = new StarWarsDataRepository(application);
    }

}
