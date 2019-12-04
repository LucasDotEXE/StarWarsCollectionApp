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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(String averageHeight) {
        this.averageHeight = averageHeight;
    }

    public String getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(String averageLifespan) {
        this.averageLifespan = averageLifespan;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public void setEyeColors(String eyeColors) {
        this.eyeColors = eyeColors;
    }

    public String getHairColors() {
        return hairColors;
    }

    public void setHairColors(String hairColors) {
        this.hairColors = hairColors;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public void setSkinColors(String skinColors) {
        this.skinColors = skinColors;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<String> getPeopleUrls() {
        return peopleUrls;
    }

    public void setPeopleUrls(ArrayList<String> peopleUrls) {
        this.peopleUrls = peopleUrls;
    }

    public ArrayList<String> getFilmsUrls() {
        return filmsUrls;
    }

    public void setFilmsUrls(ArrayList<String> filmsUrls) {
        this.filmsUrls = filmsUrls;
    }

    @Override
    public String toString() {
        return "Species{" +
                "name='" + name + '\'' +
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
