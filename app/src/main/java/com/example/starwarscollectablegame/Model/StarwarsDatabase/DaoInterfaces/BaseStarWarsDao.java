package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface BaseStarWarsDao<T> {

    @Insert
    void insert(T film);

    @Update
    void update(T item);

    @Delete
    void delete(T item);
}
