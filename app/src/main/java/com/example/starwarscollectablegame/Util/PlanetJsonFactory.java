package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.StarwarsData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

public class PlanetJsonFactory extends SwapiEntryJsonFactory {

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
        Planet entry = new Planet();

        try {
            entry.setDiameter(jsonObject.getString("diameter"));
            entry.setName(jsonObject.getString("name"));





        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entry;
    }
}
