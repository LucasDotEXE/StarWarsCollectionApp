package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Species;

import java.util.List;

@Dao
public interface SpeciesDao extends BaseStarWarsDao<Species> {

    @Query("SELECT * FROM species_table ORDER BY name DESC")
    LiveData<List<Species>> getAllSpecies();
}
