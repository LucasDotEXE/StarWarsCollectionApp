package com.example.starwarscollectablegame.Model.StarwarsData;

import java.io.Serializable;
import java.util.ArrayList;

public class Film extends SwapiEntry implements Serializable {

    public Film(String title, int episodeId, String openingCrawl, String director, String producer, ArrayList<String> speciesUrls, ArrayList<String> starshipsUrls, ArrayList<String> vehiclesUrls, ArrayList<String> planetsUrls, ArrayList<String> charactersUrls) {
        this.title = title;
        this.episodeId = episodeId;
        this.openingCrawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.speciesUrls = speciesUrls;
        this.starshipsUrls = starshipsUrls;
        this.vehiclesUrls = vehiclesUrls;
        this.planetsUrls = planetsUrls;
        this.charactersUrls = charactersUrls;
    }

    private String title;
    private int episodeId;
    private String openingCrawl;
    private String director;
    private String producer;

    private ArrayList<String> speciesUrls;
    private ArrayList<String> starshipsUrls;
    private ArrayList<String> vehiclesUrls;
    private ArrayList<String> planetsUrls;
    private ArrayList<String> charactersUrls;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public ArrayList<String> getSpeciesUrls() {
        return speciesUrls;
    }

    public void setSpeciesUrls(ArrayList<String> speciesUrls) {
        this.speciesUrls = speciesUrls;
    }

    public ArrayList<String> getStarshipsUrls() {
        return starshipsUrls;
    }

    public void setStarshipsUrls(ArrayList<String> starshipsUrls) {
        this.starshipsUrls = starshipsUrls;
    }

    public ArrayList<String> getVehiclesUrls() {
        return vehiclesUrls;
    }

    public void setVehiclesUrls(ArrayList<String> vehiclesUrls) {
        this.vehiclesUrls = vehiclesUrls;
    }

    public ArrayList<String> getPlanetsUrls() {
        return planetsUrls;
    }

    public void setPlanetsUrls(ArrayList<String> planetsUrls) {
        this.planetsUrls = planetsUrls;
    }

    public ArrayList<String> getCharactersUrls() {
        return charactersUrls;
    }

    public void setCharactersUrls(ArrayList<String> charactersUrls) {
        this.charactersUrls = charactersUrls;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", episodeId=" + episodeId +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", speciesUrls=" + speciesUrls +
                ", starshipsUrls=" + starshipsUrls +
                ", vehiclesUrls=" + vehiclesUrls +
                ", planetsUrls=" + planetsUrls +
                ", charactersUrls=" + charactersUrls +
                '}';
    }
}
