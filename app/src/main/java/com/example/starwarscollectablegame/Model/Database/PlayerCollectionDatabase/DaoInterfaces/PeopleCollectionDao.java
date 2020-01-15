package com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DaoInterfaces;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.PersonColleciton;

import java.util.List;

@Dao
public interface PeopleCollectionDao extends BaseCollectionDao<PersonColleciton> {

    @Query("SELECT * FROM person_collection ORDER BY player_name")
    LiveData<List<PersonColleciton>> getPeopleCollection();


    @Query("SELECT * FROM person_collection WHERE player_name LIKE :player_name ORDER BY person_id ASC")
    LiveData<List<PersonColleciton>> getPeopleCollectionByPlayerID(String player_name);

    @Query("DELETE FROM person_collection")
    void deleteAll();

    @Query("DELETE FROM person_collection WHERE player_name LIKE :player_name")
    void deleteFilmPlayerCollection(String player_name);
}
