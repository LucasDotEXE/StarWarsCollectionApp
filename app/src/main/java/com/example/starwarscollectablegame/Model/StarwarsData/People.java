package com.example.starwarscollectablegame.Model.StarwarsData;

import java.io.Serializable;
import java.util.ArrayList;

public class People extends SwapiEntry implements Serializable {

    private String name;
    private String eyeColor;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHomeWorldUrl() {
        return homeWorldUrl;
    }

    public void setHomeWorldUrl(String homeWorldUrl) {
        this.homeWorldUrl = homeWorldUrl;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public ArrayList<String> getFilmsUrls() {
        return filmsUrls;
    }

    public void setFilmsUrls(ArrayList<String> filmsUrls) {
        this.filmsUrls = filmsUrls;
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

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
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
