package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Starship;

import java.util.List;

@Dao
public interface StarshipDao extends BaseStarWarsDao<Starship> {

    @Query("SELECT * FROM starship_table ORDER BY name ASC")
    LiveData<List<Starship>> getAllStarship();

    @Override
    @Query("DELETE FROM starship_table")
    void deleteAll();
}
