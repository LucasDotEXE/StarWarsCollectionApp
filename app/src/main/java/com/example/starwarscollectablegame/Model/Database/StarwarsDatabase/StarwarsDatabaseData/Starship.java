package com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "starship_table", primaryKeys = {"name", "model"})
public class Starship extends SwapiEntry implements Serializable {

    @ColumnInfo(name = "name") @NonNull
    private String name;
    @ColumnInfo(name = "model") @NonNull
    private String model;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;
    private String cargoCapacity;
    private String consumables;
    private String mglt;
    private String starshipClass;
    private String hyperdriveRating;

    private ArrayList<String> pilotsUrls;
    private ArrayList<String> filmsUrls;

    public Starship(String created, String edited, String url, String name, String model, String manufacturer, String costInCredits, String length, String crew, String passengers, String maxAtmospheringSpeed, String cargoCapacity, String consumables, String mglt, String starshipClass, String hyperdriveRating, ArrayList<String> pilotsUrls, ArrayList<String> filmsUrls) {
        super(created, edited, url);
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.costInCredits = costInCredits;
        this.length = length;
        this.crew = crew;
        this.passengers = passengers;
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
        this.cargoCapacity = cargoCapacity;
        this.consumables = consumables;
        this.mglt = mglt;
        this.starshipClass = starshipClass;
        this.hyperdriveRating = hyperdriveRating;
        this.pilotsUrls = pilotsUrls;
        this.filmsUrls = filmsUrls;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCostInCredits() {
        return costInCredits;
    }

    public String getLength() {
        return length;
    }

    public String getCrew() {
        return crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public String getMglt() {
        return mglt;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public ArrayList<String> getPilotsUrls() {
        return pilotsUrls;
    }

    public ArrayList<String> getFilmsUrls() {
        return filmsUrls;
    }

    @Override
    public String toString() {
        return "Starship{" +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", costInCredits='" + costInCredits + '\'' +
                ", length='" + length + '\'' +
                ", crew='" + crew + '\'' +
                ", passengers='" + passengers + '\'' +
                ", maxAtmospheringSpeed='" + maxAtmospheringSpeed + '\'' +
                ", cargoCapacity='" + cargoCapacity + '\'' +
                ", consumables='" + consumables + '\'' +
                ", mglt='" + mglt + '\'' +
                ", starshipClass='" + starshipClass + '\'' +
                ", hyperdriveRating='" + hyperdriveRating + '\'' +
                ", pilotsUrls=" + pilotsUrls +
                ", filmsUrls=" + filmsUrls +
                '}';
    }
}
