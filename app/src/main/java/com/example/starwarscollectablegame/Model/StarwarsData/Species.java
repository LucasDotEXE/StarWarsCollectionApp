package com.example.starwarscollectablegame.Model.StarwarsData;

import java.io.Serializable;
import java.util.ArrayList;

public class Species extends SwapiEntry implements Serializable {

    private String name;
    private String classification;
    private String designation;
    private String averageHeight;
    private String averageLifespan;
    private String eyeColors;
    private String hairColors;
    private String skinColors;
    private String homeWorld;
    private String language;



    private ArrayList<String> peopleUrls;
    private ArrayList<String> filmsUrls;
}
