package com.example.starwarscollectablegame.View.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;

public class ProfileViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;

    public ProfileViewModel(Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);

    }

    public StarWarsDataRepository getRepository() {
        return repository;
    }
}