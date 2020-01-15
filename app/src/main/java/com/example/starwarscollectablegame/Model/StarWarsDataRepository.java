package com.example.starwarscollectablegame.Model;

import android.app.Application;
import android.app.Person;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.starwarscollectablegame.Controller.StarWarsAPI.StarwarsApiManager;
import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryPageListener;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DaoInterfaces.FilmCollectionDao;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DaoInterfaces.PeopleCollectionDao;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.PersonColleciton;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerDataDao;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerDataDatabaseEditHelper;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.FilmDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.PeopleDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.PlanetDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.SpeciesDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.StarshipDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.VehicleDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DatabaseEditHelper;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarWarsDatabase;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StarWarsDataRepository implements SwapiEntryPageListener {

    //starwars data
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

    private FilmCollectionDao filmCollectionDao;
    private PeopleCollectionDao peopleCollectionDao;

    private LiveData<List<FilmCollection>> filmCollection;
    private LiveData<List<PersonColleciton>> peopleCollection;

    public com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DatabaseEditHelper<FilmCollection> filmCollectionDatabaseEditHelper;
    public com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DatabaseEditHelper<PersonColleciton> personCollectionDatabaseEditHelper;


    public PlayerDataDatabaseEditHelper playerDataDatabaseEditHelper;

    private PlayerDataDao playerDataDao;

    private LiveData<List<PlayerData>> allPlayerData;


    private static StarwarsApiManager apiManager;

    public static StarwarsApiManager getApiManager() {
        return apiManager;
    }

    private Application application;

    public StarWarsDataRepository(Application application) {
        this.application = application;
        StarWarsDatabase database = StarWarsDatabase.getInstance(application, this);
        apiManager = new StarwarsApiManager(application);
        this.filmDao = database.filmDao();
        this.peopleDao = database.peopleDao();
        this.planetDao = database.planetDao();
        this.speciesDao = database.speciesDao();
        this.starshipDao = database.starshipDao();
        this.vehicleDao = database.vehicleDao();

        this.allFilms = this.filmDao.getAllFilms();
        this.allPeople = this.peopleDao.getAllPeople();
        this.allPlanet = this.planetDao.getAllPlanet();
        this.allSpecies = this.speciesDao.getAllSpecies();
        this.allStarship = this.starshipDao.getAllStarship();
        this.allVehicle = this.vehicleDao.getAllVehicle();

        this.filmDatabaseEditor = new DatabaseEditHelper<>(this.filmDao);
        this.peopleDatabaseEditor = new DatabaseEditHelper<>(this.peopleDao);
        this.planetDatabaseEditor = new DatabaseEditHelper<>(this.planetDao);
        this.speciesDatabaseEditor = new DatabaseEditHelper<>(this.speciesDao);
        this.starshipDatabaseEditor = new DatabaseEditHelper<>(this.starshipDao);
        this.vehicleDatabaseEditor = new DatabaseEditHelper<>(this.vehicleDao);

        this.filmCollectionDao = database.filmCollectionDao();
        this.peopleCollectionDao = database.peopleCollectionDao();

        this.filmCollection = this.filmCollectionDao.getFilmCollection();
        this.peopleCollection = this.peopleCollectionDao.getPeopleCollection();


        this.playerDataDao = database.playerDataDao();
        this.playerDataDatabaseEditHelper = new PlayerDataDatabaseEditHelper(this.playerDataDao);

        this.allPlayerData = this.playerDataDao.getAllPlayers() ;


        this.filmCollectionDatabaseEditHelper = new com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DatabaseEditHelper<>(this.filmCollectionDao);
        this.personCollectionDatabaseEditHelper = new com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DatabaseEditHelper<>(this.peopleCollectionDao);

    }

    public static void fillDatabase(SwapiEntryPageListener listener, Context context) {
        StarwarsApiManager apiManager = new StarwarsApiManager(context);
        Log.i(TAG, "Filling database");
        apiManager.getSwapiEntryPage(listener, StarWarsDataType.FILM);
        apiManager.getSwapiEntryPage(listener, StarWarsDataType.PEOPLE);
        apiManager.getSwapiEntryPage(listener, StarWarsDataType.PLANET);
        apiManager.getSwapiEntryPage(listener, StarWarsDataType.SPECIES);
        apiManager.getSwapiEntryPage(listener, StarWarsDataType.STARSHIP);
        apiManager.getSwapiEntryPage(listener, StarWarsDataType.VIHICLE);
    }

    public void clearDatabase() {
        this.filmDatabaseEditor.deleteAll();
        this.peopleDatabaseEditor.deleteAll();
        this.planetDatabaseEditor.deleteAll();
        this.speciesDatabaseEditor.deleteAll();
        this.starshipDatabaseEditor.deleteAll();
        this.vehicleDatabaseEditor.deleteAll();
        this.playerDataDatabaseEditHelper.deleteAll();
        this.filmCollectionDatabaseEditHelper.delleteAll();
    }

    public LiveData<List<Film>> getFilmById(int id) {
        try {
//            Film film = new GetFilmByIdAsyncTask(this.filmDao).execute(2).get();
//            Log.wtf("starwarsRepo", film.toString());
            return new GetFilmByIdAsyncTask(this.filmDao).execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<People>> getPersonById(String name) {
        try {
//            Film film = new GetFilmByIdAsyncTask(this.filmDao).execute(2).get();
//            Log.wtf("starwarsRepo", film.toString());
            return new GetPeopleByIdAsyncTask(this.peopleDao).execute(name).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<FilmCollection>> getFilmCollectionByName(String name) {
        try {
            return new GetFilmCollectionByIdAsyncTask(filmCollectionDao).execute(name).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Log.wtf(TAG, "OOps couldn't get FilmCollectione by Id");
        return new LiveData<List<FilmCollection>>() {
        };
    }

    public LiveData<List<PersonColleciton>> getPeopleCollectionByName (String name) {
        try {
            return new GetPeopleCollectionByIdAsyncTask(peopleCollectionDao).execute(name).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Log.wtf(TAG, "Oops couldn't get PersonCollectione by Id");
        return new LiveData<List<PersonColleciton>>() {
        };
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

    public LiveData<List<FilmCollection>> getFilmCollection() {
        return filmCollection;
    }

    public LiveData<List<PersonColleciton>> getPeopleCollection() {
        return peopleCollection;
    }

    public LiveData<List<PlayerData>> getAllPlayerData() {
        return this.allPlayerData;
    }

    /*private static class GetHomeworldNameFromPeopleAsyncTask extends AsyncTask<People, void, <LiveData<List<Planet>>>
    */

    private static class GetFilmByIdAsyncTask extends AsyncTask<Integer, Void, LiveData<List<Film>>> {

        private FilmDao filmDao;

        public GetFilmByIdAsyncTask(FilmDao filmDao) {
            this.filmDao = filmDao;
        }

        @Override
        protected LiveData<List<Film>> doInBackground(Integer... integers) {
            LiveData<List<Film>> film = filmDao.getFilmById(integers[0]);
            return film;
        }
    }

    private static class GetPeopleByIdAsyncTask extends AsyncTask<String, Void, LiveData<List<People>>> {

        private PeopleDao filmDao;

        public GetPeopleByIdAsyncTask(PeopleDao filmDao) {
            this.filmDao = filmDao;
        }

        @Override
        protected LiveData<List<People>> doInBackground(String... strings) {
            LiveData<List<People>> film = filmDao.getPeopleById(strings[0]);
            return film;
        }
    }

    private static class GetFilmCollectionByIdAsyncTask extends AsyncTask<String, Void, LiveData<List<FilmCollection>>> {

        private FilmCollectionDao filmDao;

        public GetFilmCollectionByIdAsyncTask(FilmCollectionDao filmDao) {
            this.filmDao = filmDao;
        }

        @Override
        protected LiveData<List<FilmCollection>> doInBackground(String... strings) {
            LiveData<List<FilmCollection>> film = filmDao.getFilmCollectionByPlayerID(strings[0]);
            return film;
        }
    }

    private static class GetPeopleCollectionByIdAsyncTask extends AsyncTask<String, Void, LiveData<List<PersonColleciton>>> {
        private PeopleCollectionDao peopleDao;

        public GetPeopleCollectionByIdAsyncTask(PeopleCollectionDao peopleDao) {
            this.peopleDao = peopleDao;
        }

        @Override
        protected LiveData<List<PersonColleciton>> doInBackground(String... strings) {
            LiveData<List<PersonColleciton>> person = peopleDao.getPeopleCollectionByPlayerID(strings[0]);
            return person;
        }
    }

    @Override
    public void onSwapiEntryPageListener(ArrayList<SwapiEntry> entries, StarWarsDataType type, int nextPage) {
        switch (type) {
            case STARSHIP: {
                for (SwapiEntry entry : entries) {
                    Starship starship = (Starship) entry;
                    starshipDatabaseEditor.insert(starship);
                }
                break;
            }
            case VIHICLE: {
                for (SwapiEntry entry : entries) {
                    Vehicle vehicle = (Vehicle) entry;
                    vehicleDatabaseEditor.insert(vehicle);
                }
                break;
            }
            case SPECIES: {
                for (SwapiEntry entry : entries) {
                    Species species = (Species) entry;
                    speciesDatabaseEditor.insert(species);
                }
                break;
            }
            case PLANET: {
                for (SwapiEntry entry : entries) {
                    Planet planet = (Planet) entry;
                    planetDatabaseEditor.insert(planet);
                }
                break;
            }
            case PEOPLE: {
                for (SwapiEntry entry : entries) {
                    People people = (People) entry;
                    peopleDatabaseEditor.insert(people);
                }
                break;
            }
            case FILM: {
                for (SwapiEntry entry : entries) {
                    Film film = (Film) entry;
                    synchronized (this) {
                        filmDatabaseEditor.insert(film);
                    }
                }
                break;
            }
        }
        if (nextPage != -1) {
            apiManager.getSwapiEntryPage(this, nextPage, type);
        } else {
            Log.i(TAG, "Finnished List recieving");
        }
        Log.i(TAG, "Recieved Swapi data Page: " + nextPage + " data type: " + type);
    }

    private static final String TAG = "StarWarsDataRepository";

    @Override
    public void onSwapiEntryPageError() {
        Log.wtf(TAG, "Error Entry page getting");
    }
}
