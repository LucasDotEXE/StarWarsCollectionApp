package com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;

import java.util.List;

@Dao
public interface FilmCollectionDao extends BaseCollectionDao<FilmCollection> {

    @Query("SELECT * FROM film_collection ORDER BY player_id ASC")
    LiveData<List<FilmCollection>> getFilmCollection();


    @Query("SELECT * FROM film_collection WHERE player_id LIKE :playerid ORDER BY film_id ASC")
    LiveData<List<FilmCollection>> getFilmCollectionByPlayerID(int playerid);

    @Query("DELETE FROM film_collection")
    void deleteAll();

    @Query("DELETE FROM film_collection WHERE player_id LIKE :player_id")
    void deleteFilmPlayerCollection(int player_id);
}
