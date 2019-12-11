package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;

import java.util.List;

@Dao
public interface FilmDao extends BaseStarWarsDao<Film>{

    @Query("SELECT * FROM film_table ORDER BY episode_id DESC")
    LiveData<List<Film>> getAllFilms();

    @Query("DELETE FROM film_table")
    void deleteAllFilms();
}
