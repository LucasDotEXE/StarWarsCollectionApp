package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Planet;

import java.util.List;

public interface PlanetDao extends BaseStarWarsDao<Planet>{

    @Query("SELECT * FROM planet_table WHERE url LIKE :planetUrl")
    List<Planet> getPlanetsByUtl(String planetUrl);
}
