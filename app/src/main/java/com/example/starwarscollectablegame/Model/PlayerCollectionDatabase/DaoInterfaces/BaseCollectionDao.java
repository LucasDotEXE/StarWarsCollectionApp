package com.example.starwarscollectablegame.Model.PlayerCollectionDatabase.DaoInterfaces;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface BaseCollectionDao<T> {

    @Insert
    void insert(T item);

    @Update
    void update(T item);

    @Delete
    void delete(T item);

    void deleteAll();
}
