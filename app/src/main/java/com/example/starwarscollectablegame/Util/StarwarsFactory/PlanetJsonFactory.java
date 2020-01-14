package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.starwarscollectablegame.Util.StarwarsFactory.JsonParsingMethods.jsonArrayToStringArray;

public class PlanetJsonFactory implements SwapiEntryJsonFactory {

    private static PlanetJsonFactory instance;

    private PlanetJsonFactory() {
    }

    public static PlanetJsonFactory getInstance() {
        if (instance == null) {
            instance = new PlanetJsonFactory();
        }
        return instance;
    }

    @Override
    public SwapiEntry parseJsonToEntry(JSONObject jsonObject) {

        try {
            String created = jsonObject.getString("created");
            String edited = jsonObject.getString("edited");
            String url = jsonObject.getString("url");

            String name = jsonObject.getString("name");
            String rotationPeriod = jsonObject.getString("rotation_period");
            String orbital_period = jsonObject.getString("orbital_period");
            String diameter = jsonObject.getString("diameter");
            String climate = jsonObject.getString("climate");
            String gravity = jsonObject.getString("gravity");
            String terrain = jsonObject.getString("terrain");
            String surface_water = jsonObject.getString("surface_water");
            String population = jsonObject.getString("population");

            ArrayList<String> residents = jsonArrayToStringArray(jsonObject.getJSONArray("residents"));
            ArrayList<String> films = jsonArrayToStringArray(jsonObject.getJSONArray("films"));


            return new Planet(created, edited, url, name, diameter, gravity, population, climate, terrain, rotationPeriod, orbital_period, surface_water,
            residents, films);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
