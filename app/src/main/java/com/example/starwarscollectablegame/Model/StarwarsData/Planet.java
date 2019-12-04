package com.example.starwarscollectablegame.Model.StarwarsData;

import java.io.Serializable;
import java.util.ArrayList;

public class Planet extends SwapiEntry implements Serializable {

    private String name;
    private String diameter;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;

    private String rotationPeriod;
    private String orbitalPeriod;
    private String surfaceWater;

    private ArrayList<String> residentsUrls;

    private ArrayList<String> filmsUrls;
}