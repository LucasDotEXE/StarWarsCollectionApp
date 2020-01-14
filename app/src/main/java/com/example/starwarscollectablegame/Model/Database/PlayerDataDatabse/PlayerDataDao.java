package com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;

import java.util.List;

@Dao
public interface PlayerDataDao {

    @Insert
    void insert(PlayerData data);

    @Update
    void update(PlayerData data);

    @Delete
    void delete(PlayerData data);

    @Query("DELETE FROM PLAYER_TABLE")
    void deleteAll();

    @Query("SELECT * FROM player_table ORDER BY player_name ASC")
    LiveData<List<PlayerData>> getAllPlayers();

    @Query("SELECT * FROM player_table WHERE player_name=:player_name")
    LiveData<List<PlayerData>> getPlayerByName(String player_name);
}
