package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;

import java.util.List;

@Dao
public interface FilmDao extends BaseStarWarsDao<Film>{

    @Query("SELECT * FROM film_table ORDER BY episode_id ASC")
    LiveData<List<Film>> getAllFilms();

    @Query("SELECT * FROM film_table WHERE episode_id=:film_id")
    LiveData<List<Film>> getFilmById(int film_id);

    @Override
    @Query("DELETE FROM film_table")
    void deleteAll();
}
