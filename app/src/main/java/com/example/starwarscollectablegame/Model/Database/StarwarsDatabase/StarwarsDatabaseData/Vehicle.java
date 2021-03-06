package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "vehicle_table", primaryKeys = {"name", "model"})
public class Vehicle extends SwapiEntry implements Serializable {

    @ColumnInfo(name = "name") @NonNull
    private String name;
    @ColumnInfo(name = "model") @NonNull
    private String model;
    @ColumnInfo(name = "vehicle_class")
    private String vehicleClass;
    @ColumnInfo(name = "manufacturer")
    private String manufacturer;
    @ColumnInfo(name = "cost_in_credits")
    private String costInCredits;
    @ColumnInfo(name = "length")
    private String length;
    @ColumnInfo(name = "crew")
    private String crew;
    @ColumnInfo(name = "passengers")
    private String passengers;
    @ColumnInfo(name = "max_atmospherics_speed")
    private String maxAtmospheringSpeed;
    @ColumnInfo(name = "cargo_capacity")
    private String cargoCapacity;
    @ColumnInfo(name = "consumables")
    private String consumables;

    @ColumnInfo(name = "pilots_urls")
    private ArrayList<String> pilotsUrls;
    @ColumnInfo(name = "film_urls")
    private ArrayList<String> filmsUrls;

    public Vehicle(String created, String edited, String url, String name, String model, String vehicleClass, String manufacturer, String costInCredits, String length, String crew, String passengers, String maxAtmospheringSpeed, String cargoCapacity, String consumables, ArrayList<String> pilotsUrls, ArrayList<String> filmsUrls) {
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
        this.pilotsUrls = pilotsUrls;
        this.filmsUrls = filmsUrls;
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

    public ArrayList<String> getPilotsUrls() {
        return pilotsUrls;
    }

    public ArrayList<String> getFilmsUrls() {
        return filmsUrls;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", vehicleClass='" + vehicleClass + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", costInCredits='" + costInCredits + '\'' +
                ", length='" + length + '\'' +
                ", crew='" + crew + '\'' +
                ", passengers='" + passengers + '\'' +
                ", maxAtmospheringSpeed='" + maxAtmospheringSpeed + '\'' +
                ", cargoCapacity='" + cargoCapacity + '\'' +
                ", consumables='" + consumables + '\'' +
                ", pilotsUrls=" + pilotsUrls +
                ", filmsUrls=" + filmsUrls +
                '}';
    }
}
