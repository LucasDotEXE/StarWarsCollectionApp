package com.example.starwarscollectablegame.Model.StarwarsDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.starwarscollectablegame.Controller.StarWarsAPI.StarwarsApiManager;
import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryPageListener;
import com.example.starwarscollectablegame.Model.PlayerCollectionDatabase.DaoInterfaces.FilmCollectionDao;
import com.example.starwarscollectablegame.Model.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.FilmDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.PeopleDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.PlanetDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.SpeciesDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.StarshipDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.VehicleDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Vehicle;
import com.example.starwarscollectablegame.Util.Converters;

import java.util.ArrayList;

@Database(entities = {Film.class, People.class, Planet.class, Species.class, Starship.class, Vehicle.class
                     , FilmCollection.class}, version = 12)
@TypeConverters({Converters.class})
public abstract class StarWarsDatabase extends RoomDatabase{

    private static final String TAG = "StarWarsDatabase";

    private static StarWarsDatabase instance;

    public abstract FilmDao filmDao();
    public abstract PeopleDao peopleDao();
    public abstract PlanetDao planetDao();
    public abstract SpeciesDao speciesDao();
    public abstract StarshipDao starshipDao();
    public abstract VehicleDao vehicleDao();

    public abstract FilmCollectionDao filmCollectionDao();


    public static synchronized StarWarsDatabase getInstance(final Context context, final SwapiEntryPageListener listener) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StarWarsDatabase.class, "star_wars_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                        }
                    })
                    .build();
        }

        return instance;
    }


//    public void getAllFilmCollectionByPlayerId(int id) {
//        new GetAllFilmByIdAsyncTask(this.filmCollectionDao()).execute(id);
//    }
//
//
//    private static class GetAllFilmByIdAsyncTask extends AsyncTask<Integer, Void, Void> {
//
//        private FilmCollectionDao dao;
//
//        private GetAllFilmByIdAsyncTask(FilmCollectionDao dao) {
//            this.dao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(Integer... item) {
//            this.dao.getFilmCollectionByPlayerID(item[0]);
//            return null;
//        }
//
//    }


}
