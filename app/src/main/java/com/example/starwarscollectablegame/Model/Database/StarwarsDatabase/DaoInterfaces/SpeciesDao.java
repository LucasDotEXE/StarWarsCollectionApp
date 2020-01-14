package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Species;

import java.util.List;

@Dao
public interface SpeciesDao extends BaseStarWarsDao<Species> {

    @Query("SELECT * FROM species_table ORDER BY name ASC")
    LiveData<List<Species>> getAllSpecies();

    @Override
    @Query("DELETE FROM species_table")
    void deleteAll();
}
