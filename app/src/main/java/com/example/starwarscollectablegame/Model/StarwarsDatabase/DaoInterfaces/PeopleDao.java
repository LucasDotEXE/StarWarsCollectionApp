package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;

import java.util.List;

@Dao
public interface PeopleDao extends BaseStarWarsDao<People> {

    @Query("SELECT * FROM people_table ORDER BY name DESC")
    LiveData<List<People>> getAllPeople();
}
