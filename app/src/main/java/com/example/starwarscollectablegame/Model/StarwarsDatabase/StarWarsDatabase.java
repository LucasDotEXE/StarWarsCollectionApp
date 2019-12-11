package com.example.starwarscollectablegame.Model.StarwarsDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

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
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Vehicle;
import com.example.starwarscollectablegame.Util.Converters;

@Database(entities = {Film.class, People.class, Planet.class, Species.class, Starship.class, Vehicle.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class StarWarsDatabase extends RoomDatabase {


    private static StarWarsDatabase instance;

    public abstract FilmDao filmDao();
    public abstract PeopleDao peopleDao();
    public abstract PlanetDao planetDao();
    public abstract SpeciesDao speciesDao();
    public abstract StarshipDao starshipDao();
    public abstract VehicleDao vehicleDao();

    public static synchronized StarWarsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StarWarsDatabase.class, "star_wars_database")
                    .fallbackToDestructiveMigration()
//                    .addCallback(roomPopulationCallback)
                    .build();
        }

        return instance;
    }
}
