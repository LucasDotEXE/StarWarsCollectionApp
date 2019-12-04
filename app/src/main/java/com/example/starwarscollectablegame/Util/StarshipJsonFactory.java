package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONObject;

public class StarshipJsonFactory extends SwapiEntryJsonFactory {

    private static StarshipJsonFactory instance;

    private StarshipJsonFactory() {
    }

    public static StarshipJsonFactory getInstance() {
        if (instance == null) {
            instance = new StarshipJsonFactory();
        }
        return instance;
    }

    @Override
    public SwapiEntry parseJsonToEntry(JSONObject jsonObject) {
        return null;
    }
}
