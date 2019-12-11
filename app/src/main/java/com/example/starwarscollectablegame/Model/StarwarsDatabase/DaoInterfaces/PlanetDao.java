package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Planet;

import java.util.List;

@Dao
public interface PlanetDao extends BaseStarWarsDao<Planet>{

    @Query("SELECT * FROM planet_table ORDER BY name DESC")
    LiveData<List<Planet>> getAllPlanet();

    @Query("SELECT * FROM planet_table WHERE url LIKE :planetUrl")
    List<Planet> getPlanetsByUtl(String planetUrl);
}
