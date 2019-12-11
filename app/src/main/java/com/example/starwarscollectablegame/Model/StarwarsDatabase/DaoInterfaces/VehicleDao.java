package com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Vehicle;

import java.util.List;

@Dao
public interface VehicleDao extends BaseStarWarsDao<Vehicle> {

    @Query("SELECT * FROM vehicle_table ORDER BY name DESC")
    LiveData<List<Vehicle>> getAllVehicle();
}
