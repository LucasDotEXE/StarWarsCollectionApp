package com.example.starwarscollectablegame.Model.StarwarsDatabaseData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "starship_table")
public class Starship extends SwapiEntry implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int starship_id;

    @ColumnInfo(name = "name")
    private String name;
    private String model;
    private String vehicleClass;
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

    public Starship(String created, String edited, String url, String name, String model, String vehicleClass, String manufacturer, String costInCredits, String length, String crew, String passengers, String maxAtmospheringSpeed, String cargoCapacity, String consumables, String mglt, String starshipClass, String hyperdriveRating, ArrayList<String> pilotsUrls, ArrayList<String> filmsUrls) {
        super(created, edited, url);
        this.name = name;
        this.model = model;
        this.vehicleClass = vehicleClass;
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

    public int getStarship_id() {
        return starship_id;
    }

    public void setStarship_id(int starship_id) {
        this.starship_id = starship_id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleClass() {
        return vehicleClass;
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
}
