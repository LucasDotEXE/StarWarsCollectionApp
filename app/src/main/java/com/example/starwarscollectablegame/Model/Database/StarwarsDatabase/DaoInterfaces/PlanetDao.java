package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Planet;

import java.util.List;

@Dao
public interface PlanetDao extends BaseStarWarsDao<Planet>{

    @Query("SELECT * FROM planet_table ORDER BY name ASC")
    LiveData<List<Planet>> getAllPlanet();

    @Query("SELECT * FROM planet_table WHERE url LIKE :planetUrl")
    List<Planet> getPlanetsByUtl(String planetUrl);

    @Override
    @Query("DELETE FROM planet_table")
    void deleteAll();
}
