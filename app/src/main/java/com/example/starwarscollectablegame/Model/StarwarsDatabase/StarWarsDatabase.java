package com.example.starwarscollectablegame.Model.StarwarsDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.FilmDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Vehicle;
import com.example.starwarscollectablegame.Util.Converters;

@Database(entities = {Film.class, People.class, Planet.class, Species.class, Starship.class, Vehicle.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class StarWarsDatabase extends RoomDatabase {


    private static StarWarsDatabase instance;

    public abstract FilmDao filmDao();

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
