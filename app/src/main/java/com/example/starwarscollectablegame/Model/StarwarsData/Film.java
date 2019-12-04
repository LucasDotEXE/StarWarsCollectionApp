package com.example.starwarscollectablegame.Model.StarwarsData;

import java.io.Serializable;
import java.util.ArrayList;

public class Film extends SwapiEntry implements Serializable {

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
}
