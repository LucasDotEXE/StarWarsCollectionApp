package com.example.starwarscollectablegame.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.BaseStarWarsDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.FilmDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.PeopleDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.PlanetDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.SpeciesDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.StarshipDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.VehicleDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DatabaseEditHelper;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarWarsDatabase;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Vehicle;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class StarWarsDataRepository {

    private FilmDao filmDao;
    private PeopleDao peopleDao;
    private PlanetDao planetDao;
    private SpeciesDao speciesDao;
    private StarshipDao starshipDao;
    private VehicleDao vehicleDao;

    private LiveData<List<Film>> allFilms;
    private LiveData<List<People>> allPeople;
    private LiveData<List<Planet>> allPlanet;
    private LiveData<List<Species>> allSpecies;
    private LiveData<List<Starship>> allStarship;
    private LiveData<List<Vehicle>> allVehicle;

    public DatabaseEditHelper<Film> filmDatabaseEditor;
    public DatabaseEditHelper<People> peopleDatabaseEditor;
    public DatabaseEditHelper<Planet> planetDatabaseEditor;
    public DatabaseEditHelper<Species> speciesDatabaseEditor;
    public DatabaseEditHelper<Starship> starshipDatabaseEditor;
    public DatabaseEditHelper<Vehicle> vehicleDatabaseEditor;


    public StarWarsDataRepository(Application application) {
        StarWarsDatabase database = StarWarsDatabase.getInstance(application);
        this.filmDao = database.filmDao();
        this.peopleDao = database.peopleDao();
        this.planetDao = database.planetDao();
        this.speciesDao = database.speciesDao();
        this.starshipDao = database.starshipDao();
        this.vehicleDao = database.vehicleDao();

        this.allFilms = this.filmDao.getAllFilms();
        this.allPeople = this.peopleDao.getAllPeople();
        this.allPlanet= this.planetDao.getAllPlanet();
        this.allSpecies = this.speciesDao.getAllSpecies();
        this.allStarship = this.starshipDao.getAllStarship();
        this.allVehicle = this.vehicleDao.getAllVehicle();

        this.filmDatabaseEditor = new DatabaseEditHelper<>(this.filmDao);
        this.peopleDatabaseEditor = new DatabaseEditHelper<>(this.peopleDao);
        this.planetDatabaseEditor = new DatabaseEditHelper<>(this.planetDao);
        this.speciesDatabaseEditor = new DatabaseEditHelper<>(this.speciesDao);
        this.starshipDatabaseEditor = new DatabaseEditHelper<>(this.starshipDao);
        this.vehicleDatabaseEditor = new DatabaseEditHelper<>(this.vehicleDao);
    }


    public void deleteAllFilms() {
        new DeleteAllNotesAsyncTask(filmDao).execute();
    }



    public List<Planet> getPlanetByUrl(String url) {
        return  this.planetDao.getPlanetsByUtl(url);
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

    public LiveData<People> getPeopleByName(String name) {
        /*AsyncTask<String, Void, LiveData<List<People>>> task = new getPeopleByNameAsyncTask(peopleDao, name);
        task.execute();
*/
        return this.peopleDao.getPeopleByName(name);

    }


    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private FilmDao noteDao;

        private DeleteAllNotesAsyncTask(FilmDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllFilms();
            return null;
        }
    }

    /*private class getPeopleByNameAsyncTask extends AsyncTask<String, Void, LiveData<List<People>>>{
        private PeopleDao peopleDao;

        public getPeopleByNameAsyncTask(PeopleDao peopleDao, String name) {
            this.peopleDao = peopleDao;

        }

        @Override
        protected LiveData<List<People>> doInBackground(String... strings) {
            return peopleDao.getPeopleByName(strings[0]);
        }
    }*/
}
