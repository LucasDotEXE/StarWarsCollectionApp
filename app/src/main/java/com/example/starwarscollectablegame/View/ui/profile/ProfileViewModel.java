package com.example.starwarscollectablegame.View.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;

    private LiveData<List<PlayerData>> allPlayerData;

    public ProfileViewModel(Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);
        allPlayerData = repository.getAllPlayerData();

    }

    public StarWarsDataRepository getRepository() {
        return repository;
    }

    public void insert(PlayerData playerData) {
        repository.playerDataDatabaseEditHelper.insert(playerData);
    }

    public void update(PlayerData playerData) {
        repository.playerDataDatabaseEditHelper.update(playerData);
    }

    public void delete(PlayerData playerData) {
        repository.playerDataDatabaseEditHelper.delete(playerData);
    }

    public LiveData<List<PlayerData>> getAllPlayerData() {
        return allPlayerData;
    }

    public LiveData<List<PlayerData>> getPlayerById(int id) {
        return repository.playerDataDatabaseEditHelper.getPlayerById(id);
    }


}