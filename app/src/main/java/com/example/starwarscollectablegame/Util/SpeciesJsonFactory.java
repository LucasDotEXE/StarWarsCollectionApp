package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONObject;

public class SpeciesJsonFactory extends SwapiEntryJsonFactory {

    private static SpeciesJsonFactory instance;

    private SpeciesJsonFactory() {
    }

    public static SpeciesJsonFactory getInstance() {
        if (instance == null) {
            instance = new SpeciesJsonFactory();
        }
        return instance;
    }

    @Override
    public SwapiEntry parseJsonToEntry(JSONObject jsonObject) {
        return null;
    }
}
