package com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "species_table")
public class Species extends SwapiEntry implements Serializable {

    @PrimaryKey @ColumnInfo(name = "name") @NonNull
    private String name;

    @ColumnInfo(name = "classification")
    private String classification;
    @ColumnInfo(name = "designation")
    private String designation;
    @ColumnInfo(name = "average_height")
    private String averageHeight;
    @ColumnInfo(name = "average_lifespan")
    private String averageLifespan;
    @ColumnInfo(name = "eye_colors")
    private String eyeColors;
    @ColumnInfo(name = "hair_colors")
    private String hairColors;
    @ColumnInfo(name = "skin_colors")
    private String skinColors;
    @ColumnInfo(name = "home_world")
    private String homeWorld;
    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "people_urls")
    private ArrayList<String> peopleUrls;
    @ColumnInfo(name = "films_urls")
    private ArrayList<String> filmsUrls;

    public Species(String created, String edited, String url, String name, String classification, String designation, String averageHeight, String averageLifespan, String eyeColors, String hairColors, String skinColors, String homeWorld, String language, ArrayList<String> peopleUrls, ArrayList<String> filmsUrls) {
        super(created, edited, url);
        this.name = name;
        this.classification = classification;
        this.designation = designation;
        this.averageHeight = averageHeight;
        this.averageLifespan = averageLifespan;
        this.eyeColors = eyeColors;
        this.hairColors = hairColors;
        this.skinColors = skinColors;
        this.homeWorld = homeWorld;
        this.language = language;
        this.peopleUrls = peopleUrls;
        this.filmsUrls = filmsUrls;
    }

    public String getName() {
        return name;
    }

    public String getClassification() {
        return classification;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public String getAverageLifespan() {
        return averageLifespan;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public String getHairColors() {
        return hairColors;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public String getLanguage() {
        return language;
    }

    public ArrayList<String> getPeopleUrls() {
        return peopleUrls;
    }

    public ArrayList<String> getFilmsUrls() {
        return filmsUrls;
    }

    @Override
    public String toString() {
        return "Species{" +
                ", name='" + name + '\'' +
                ", classification='" + classification + '\'' +
                ", designation='" + designation + '\'' +
                ", averageHeight='" + averageHeight + '\'' +
                ", averageLifespan='" + averageLifespan + '\'' +
                ", eyeColors='" + eyeColors + '\'' +
                ", hairColors='" + hairColors + '\'' +
                ", skinColors='" + skinColors + '\'' +
                ", homeWorld='" + homeWorld + '\'' +
                ", language='" + language + '\'' +
                ", peopleUrls=" + peopleUrls +
                ", filmsUrls=" + filmsUrls +
                '}';
    }
}
