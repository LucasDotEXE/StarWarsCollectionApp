package com.example.starwarscollectablegame.View.ui.settings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;

public class SettingsViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;

    public SettingsViewModel(Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);
    }

    public StarWarsDataRepository getRepository() {
        return repository;
    }
}