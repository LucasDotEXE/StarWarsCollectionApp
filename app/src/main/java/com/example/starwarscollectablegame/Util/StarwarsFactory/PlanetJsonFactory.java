package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.StarwarsData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.starwarscollectablegame.Util.JsonParsingMethods.jsonArrayToStringArray;

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
        Planet planet = new Planet();

        try {
            planet.setName(jsonObject.getString("name"));
            planet.setRotationPeriod(jsonObject.getString("rotation_period"));
            planet.setOrbitalPeriod(jsonObject.getString("orbital_period"));
            planet.setDiameter(jsonObject.getString("diameter"));
            planet.setClimate(jsonObject.getString("climate"));
            planet.setGravity(jsonObject.getString("gravity"));
            planet.setTerrain(jsonObject.getString("terrain"));
            planet.setSurfaceWater(jsonObject.getString("surface_water"));
            planet.setPopulation(jsonObject.getString("population"));

            planet.setResidentsUrls(jsonArrayToStringArray(jsonObject.getJSONArray("residents")));
            planet.setFilmsUrls(jsonArrayToStringArray(jsonObject.getJSONArray("films")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return planet;
    }
}
