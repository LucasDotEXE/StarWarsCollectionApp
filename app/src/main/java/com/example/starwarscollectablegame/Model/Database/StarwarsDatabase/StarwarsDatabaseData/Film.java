package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "film_table")
public class Film extends SwapiEntry implements Serializable {

    @PrimaryKey @ColumnInfo(name = "episode_id") @NonNull
    private int episodeId;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "opening_crawl")
    private String openingCrawl;
    @ColumnInfo(name = "director")
    private String director;
    @ColumnInfo(name = "producer")
    private String producer;
    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "species_url")
    private ArrayList<String> speciesUrls;
    @ColumnInfo(name = "starships_url")
    private ArrayList<String> starshipsUrls;
    @ColumnInfo(name = "vehicles_url")
    private ArrayList<String> vehiclesUrls;
    @ColumnInfo(name = "planets_url")
    private ArrayList<String> planetsUrls;
    @ColumnInfo(name = "characters_url")
    private ArrayList<String> charactersUrls;

    public Film(String created, String edited, String url, String title, int episodeId, String openingCrawl, String director, String producer, String releaseDate, ArrayList<String> speciesUrls, ArrayList<String> starshipsUrls, ArrayList<String> vehiclesUrls, ArrayList<String> planetsUrls, ArrayList<String> charactersUrls) {
        super(created, edited, url);
        this.title = title;
        this.episodeId = episodeId;
        this.openingCrawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.speciesUrls = speciesUrls;
        this.starshipsUrls = starshipsUrls;
        this.vehiclesUrls = vehiclesUrls;
        this.planetsUrls = planetsUrls;
        this.charactersUrls = charactersUrls;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getReleaseDate() {
        return releaseDate;
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

    public ArrayList<String> getPlanetsUrls() {
        return planetsUrls;
    }

    public ArrayList<String> getCharactersUrls() {
        return charactersUrls;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", episodeId=" + episodeId +
               // ", openingCrawl='" + openingCrawl + '\'' +
//                ", director='" + director + '\'' +
//                ", producer='" + producer + '\'' +
//                ", releaseDate='" + releaseDate + '\'' +
//                ", speciesUrls=" + speciesUrls +
//                ", starshipsUrls=" + starshipsUrls +
//                ", vehiclesUrls=" + vehiclesUrls +
//                ", planetsUrls=" + planetsUrls +
//                ", charactersUrls=" + charactersUrls +
                '}';
    }
}
