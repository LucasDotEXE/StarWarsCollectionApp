package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryPageListener;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DaoInterfaces.FilmCollectionDao;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerDataDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.FilmDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.PeopleDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.PlanetDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.SpeciesDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.StarshipDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.DaoInterfaces.VehicleDao;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Vehicle;
import com.example.starwarscollectablegame.Util.Converters;

@Database(entities ={Film.class, People.class, Planet.class, Species.class, Starship.class, Vehicle.class,
                     FilmCollection.class,
                     PlayerData.class}
                     , version = 13)
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

    public abstract PlayerDataDao playerDataDao();


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
