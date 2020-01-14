package com.example.starwarscollectablegame.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Vehicle;

import java.util.List;

public class swDatabaseViewModel extends AndroidViewModel {

    private StarWarsDataRepository repository;

    private LiveData<List<Film>> allFilms;
    private LiveData<List<People>> allPeople;
    private LiveData<List<Planet>> allPlanet;
    private LiveData<List<Species>> allSpecies;
    private LiveData<List<Starship>> allStarship;
    private LiveData<List<Vehicle>> allVehicle;

    public swDatabaseViewModel(@NonNull Application application) {
        super(application);
        this.repository = new StarWarsDataRepository(application);
        this.allFilms = repository.getAllFilms();
        this.allPeople = repository.getAllPeople();
        this.allPlanet = repository.getAllPlanet();
        this.allSpecies = repository.getAllSpecies();
        this.allStarship = repository.getAllStarship();
        this.allVehicle = repository.getAllVehicle();

    }

    public void insert(Film film) {
        this.repository.filmDatabaseEditor.insert(film);
    }

    public void update(Film film) {
        this.repository.filmDatabaseEditor.update(film);
    }

    public void delete(Film film) {
        this.repository.filmDatabaseEditor.delete(film);
    }

    public void insert(People people) {
        this.repository.peopleDatabaseEditor.insert(people);
    }

    public void update(People people) {
        this.repository.peopleDatabaseEditor.update(people);
    }

    public void delete(People people) {
        this.repository.peopleDatabaseEditor.delete(people);
    }

    public void insert(Planet planet) {
        this.repository.planetDatabaseEditor.insert(planet);
    }

    public void update(Planet planet) {
        this.repository.planetDatabaseEditor.update(planet);
    }

    public void delete(Planet planet) {
        this.repository.planetDatabaseEditor.delete(planet);
    }

    public void insert(Species species) {
        this.repository.speciesDatabaseEditor.insert(species);
    }

    public void update(Species species) {
        this.repository.speciesDatabaseEditor.update(species);
    }

    public void delete(Species species) {
        this.repository.speciesDatabaseEditor.delete(species);
    }

    public void insert(Starship starship) {
        this.repository.starshipDatabaseEditor.insert(starship);
    }

    public void update(Starship starship) {
        this.repository.starshipDatabaseEditor.update(starship);
    }

    public void delete(Starship starship) {
        this.repository.starshipDatabaseEditor.delete(starship);
    }

    public void insert(Vehicle vehicle) {
        this.repository.vehicleDatabaseEditor.insert(vehicle);
    }

    public void update(Vehicle vehicle) {
        this.repository.vehicleDatabaseEditor.update(vehicle);
    }

    public void delete(Vehicle vehicle) {
        this.repository.vehicleDatabaseEditor.delete(vehicle);
    }

    public LiveData<List<Film>> getAllFilms() {
        return allFilms;
    }

    public LiveData<List<People>> getAllPeople() {
        return allPeople;
    }

    public LiveData<List<Planet>> getAllPlanet() {
        return allPlanet;
    }

    public LiveData<List<Species>> getAllSpecies() {
        return allSpecies;
    }

    public LiveData<List<Starship>> getAllStarship() {
        return allStarship;
    }

    public LiveData<List<Vehicle>> getAllVehicle() {
        return allVehicle;
    }
}
