package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "planet_table")
public class Planet extends SwapiEntry implements Serializable {

    @PrimaryKey @ColumnInfo(name = "name") @NonNull
    private String name;

    @ColumnInfo(name = "diameter")
    private String diameter;
    @ColumnInfo(name = "gravity")
    private String gravity;
    @ColumnInfo(name = "population")
    private String population;
    @ColumnInfo(name = "climate")
    private String climate;
    @ColumnInfo(name = "terrain")
    private String terrain;
    @ColumnInfo(name = "rotation_period")
    private String rotationPeriod;
    @ColumnInfo(name = "orbital_period")
    private String orbitalPeriod;
    @ColumnInfo(name = "surface_water")
    private String surfaceWater;

    @ColumnInfo(name = "residents_url")
    private ArrayList<String> residentsUrls;
    @ColumnInfo(name = "film_url")
    private ArrayList<String> filmsUrls;

    public Planet(String created, String edited, String url, String name, String diameter, String gravity, String population, String climate, String terrain, String rotationPeriod, String orbitalPeriod, String surfaceWater, ArrayList<String> residentsUrls, ArrayList<String> filmsUrls) {
        super(created, edited, url);
        this.name = name;
        this.diameter = diameter;
        this.gravity = gravity;
        this.population = population;
        this.climate = climate;
        this.terrain = terrain;
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.surfaceWater = surfaceWater;
        this.residentsUrls = residentsUrls;
        this.filmsUrls = filmsUrls;
    }

    public String getName() {
        return name;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getGravity() {
        return gravity;
    }

    public String getPopulation() {
        return population;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public ArrayList<String> getResidentsUrls() {
        return residentsUrls;
    }

    public ArrayList<String> getFilmsUrls() {
        return filmsUrls;
    }

    @Override
    public String toString() {
        return "Planet{" +
                ", name='" + name + '\'' +
                ", diameter='" + diameter + '\'' +
                ", gravity='" + gravity + '\'' +
                ", population='" + population + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", rotationPeriod='" + rotationPeriod + '\'' +
                ", orbitalPeriod='" + orbitalPeriod + '\'' +
                ", surfaceWater='" + surfaceWater + '\'' +
                ", residentsUrls=" + residentsUrls +
                ", filmsUrls=" + filmsUrls +
                '}';
    }
}