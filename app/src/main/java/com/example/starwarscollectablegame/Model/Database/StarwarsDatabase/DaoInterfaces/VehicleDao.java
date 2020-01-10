package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Vehicle;

import java.util.List;

@Dao
public interface VehicleDao extends BaseStarWarsDao<Vehicle> {

    @Query("SELECT * FROM vehicle_table ORDER BY name ASC")
    LiveData<List<Vehicle>> getAllVehicle();

    @Override
    @Query("DELETE FROM vehicle_table")
    void deleteAll();
}
