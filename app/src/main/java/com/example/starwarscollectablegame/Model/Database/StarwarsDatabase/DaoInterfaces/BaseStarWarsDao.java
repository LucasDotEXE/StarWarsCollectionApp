package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

public interface BaseStarWarsDao<T extends SwapiEntry> {

    @Insert
    void insert(T item);

    @Update
    void update(T item);

    @Delete
    void delete(T item);

    void deleteAll();
}
