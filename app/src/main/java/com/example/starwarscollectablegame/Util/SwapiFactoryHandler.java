package com.example.starwarscollectablegame.Util;

import android.util.Log;

import com.example.starwarscollectablegame.Model.StarwarsData.Film;
import com.example.starwarscollectablegame.Model.StarwarsData.People;
import com.example.starwarscollectablegame.Model.StarwarsData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsData.Species;
import com.example.starwarscollectablegame.Model.StarwarsData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;
import com.example.starwarscollectablegame.Model.StarwarsData.Vehicle;

import org.json.JSONObject;

public class SwapiFactoryHandler {

    public static SwapiEntry parseJson(JSONObject jsonObject, StarWarsDataType type) {
        switch (type) {
            case FILM:
                return FilmJsonFactory.getInstance().parseJsonToEntry(jsonObject);
            case PEOPLE:
                return PeopleJsonFactory.getInstance().parseJsonToEntry(jsonObject);
            case PLANET:
                return PlanetJsonFactory.getInstance().parseJsonToEntry(jsonObject);
            case SPECIES:
                return SpeciesJsonFactory.getInstance().parseJsonToEntry(jsonObject);
            case VIHICLE:
                return VehicleJsonFactory.getInstance().parseJsonToEntry(jsonObject);
            case STARSHIP:
                return StarshipJsonFactory.getInstance().parseJsonToEntry(jsonObject);
        }
        return null;
    }
}
