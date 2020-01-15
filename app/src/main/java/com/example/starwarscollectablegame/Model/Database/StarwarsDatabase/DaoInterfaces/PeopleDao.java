package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;

import java.util.List;

@Dao
public interface PeopleDao extends BaseStarWarsDao<People> {

    @Query("SELECT * FROM people_table ORDER BY name ASC")
    LiveData<List<People>> getAllPeople();

    @Override
    @Query("DELETE FROM people_table")
    void deleteAll();

    @Query("SELECT * FROM people_table WHERE name=:name_id")
    LiveData<List<People>> getPeopleById(String name_id);
}
