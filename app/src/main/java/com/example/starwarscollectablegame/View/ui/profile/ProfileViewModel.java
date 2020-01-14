package com.example.starwarscollectablegame.View.ui.profile;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;

    private LiveData<List<PlayerData>> allPlayerData;

    public ProfileViewModel(Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);
        allPlayerData = repository.getAllPlayerData();

    }

    public void insert(final PlayerData playerData, LifecycleOwner owner) {
            repository.playerDataDatabaseEditHelper.insert(playerData);
//        for (Film film : repository.getAllFilms().getValue()) {
//            Log.wtf("TEST at ProfileViewModel", playerData.getPlayer_name() + " added " + film.getEpisodeId());
//            repository.filmCollectionDatabaseEditHelper.insert(new FilmCollection(
//                    playerData.getPlayer_name(), film.getEpisodeId(), 0, 0));
//        }

//                    owner, new Observer<List<Film>>() {
//                @Override
//                public void onChanged(List<Film> films) {
//                    for (Film film : films) {
//                        Log.wtf("TEST at ProfileViewModel", playerData.getPlayer_name() + " added " + film.getEpisodeId());
//                        repository.filmCollectionDatabaseEditHelper.insert(new FilmCollection(
//                                playerData.getPlayer_name(), film.getEpisodeId(), 0, 0));
//                    }
//                    repository.getAllFilms().removeObserver(this);
//                }
//            });
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


    public StarWarsDataRepository getRepository() {
        return repository;
    }
}