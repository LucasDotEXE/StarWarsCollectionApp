package com.example.starwarscollectablegame.Model.StarwarsData;

import java.io.Serializable;
import java.util.ArrayList;

public class People extends SwapiEntry implements Serializable {

    private String name;
    private String birthYear;
    private String gender;
    private String hairColor;
    private String height;
    private String homeWorldUrl;
    private String mass;
    private String skinColor;


    private ArrayList<String> filmsUrls;
    private ArrayList<String> speciesUrls;
    private ArrayList<String> starshipsUrls;
    private ArrayList<String> vehiclesUrls;
}
