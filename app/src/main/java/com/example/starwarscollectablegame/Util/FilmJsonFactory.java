package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONObject;

public class FilmJsonFactory extends SwapiEntryJsonFactory {

    private static FilmJsonFactory instance;

    private FilmJsonFactory() {
    }

    public static FilmJsonFactory getInstance() {
        if (instance == null) {
            instance = new FilmJsonFactory();
        }
        return instance;
    }

    @Override
    public SwapiEntry parseJsonToEntry(JSONObject jsonObject) {
        return null;
    }
}
