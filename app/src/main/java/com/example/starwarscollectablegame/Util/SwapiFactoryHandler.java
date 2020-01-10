package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Util.StarwarsFactory.FilmJsonFactory;
import com.example.starwarscollectablegame.Util.StarwarsFactory.PeopleJsonFactory;
import com.example.starwarscollectablegame.Util.StarwarsFactory.PlanetJsonFactory;
import com.example.starwarscollectablegame.Util.StarwarsFactory.SpeciesJsonFactory;
import com.example.starwarscollectablegame.Util.StarwarsFactory.StarshipJsonFactory;
import com.example.starwarscollectablegame.Util.StarwarsFactory.VehicleJsonFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SwapiFactoryHandler {


    public static ArrayList<SwapiEntry> parseSwapiPageJson(JSONObject jsonObject, StarWarsDataType type) {
        try {
            JSONArray results = jsonObject.getJSONArray("results");
            ArrayList<SwapiEntry> parsedResults = new ArrayList<>();
            for (int i = 0; i < results.length(); i++) {
                parsedResults.add(parseSwapiEntryJson(results.getJSONObject(i), type));
            }
            return parsedResults;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SwapiEntry parseSwapiEntryJson(JSONObject jsonObject, StarWarsDataType type) {
        SwapiEntry entry = parseTypeJson(jsonObject, type);

        return entry;
    }

    private static SwapiEntry parseTypeJson(JSONObject jsonObject, StarWarsDataType type) {
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
