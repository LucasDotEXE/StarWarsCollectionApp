package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "people_table")
public class People extends SwapiEntry implements Serializable {

    @PrimaryKey @ColumnInfo(name = "name") @NonNull
    private String name;

    @ColumnInfo(name = "eye_color")
    private String eyeColor;
    @ColumnInfo(name = "birth_year")
    private String birthYear;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "hair_color")
    private String hairColor;
    @ColumnInfo(name = "height")
    private String height;
    @ColumnInfo(name = "home_world_url")
    private String homeWorldUrl;
    @ColumnInfo(name = "mass")
    private String mass;
    @ColumnInfo(name = "skin_color")
    private String skinColor;

    @ColumnInfo(name = "film_url")
    private ArrayList<String> filmsUrls;
    @ColumnInfo(name = "species_url")
    private ArrayList<String> speciesUrls;
    @ColumnInfo(name = "starships_url")
    private ArrayList<String> starshipsUrls;
    @ColumnInfo(name = "vehicles_url")
    private ArrayList<String> vehiclesUrls;



    public People(String created, String edited, String url, String name, String eyeColor, String birthYear, String gender, String hairColor, String height, String homeWorldUrl, String mass, String skinColor, ArrayList<String> filmsUrls, ArrayList<String> speciesUrls, ArrayList<String> starshipsUrls, ArrayList<String> vehiclesUrls) {
        super(created, edited, url);
        this.name = name;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
        this.hairColor = hairColor;
        this.height = height;
        this.homeWorldUrl = homeWorldUrl;
        this.mass = mass;
        this.skinColor = skinColor;
        this.filmsUrls = filmsUrls;
        this.speciesUrls = speciesUrls;
        this.starshipsUrls = starshipsUrls;
        this.vehiclesUrls = vehiclesUrls;
    }

    public String getName() {
        return name;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getHeight() {
        return height;
    }

    public String getHomeWorldUrl() {
        return homeWorldUrl;
    }

    public String getMass() {
        return mass;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public ArrayList<String> getFilmsUrls() {
        return filmsUrls;
    }

    public ArrayList<String> getSpeciesUrls() {
        return speciesUrls;
    }

    public ArrayList<String> getStarshipsUrls() {
        return starshipsUrls;
    }

    public ArrayList<String> getVehiclesUrls() {
        return vehiclesUrls;
    }

    @Override
    public String toString() {
        return "People{" +
                ", name='" + name + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", height='" + height + '\'' +
                ", homeWorldUrl='" + homeWorldUrl + '\'' +
                ", mass='" + mass + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", filmsUrls=" + filmsUrls +
                ", speciesUrls=" + speciesUrls +
                ", starshipsUrls=" + starshipsUrls +
                ", vehiclesUrls=" + vehiclesUrls +
                '}';
    }
}
