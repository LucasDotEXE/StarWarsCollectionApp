package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

import org.json.JSONObject;

public class VehicleJsonFactory implements SwapiEntryJsonFactory {

    private static VehicleJsonFactory instance;

    private VehicleJsonFactory() {
    }

    public static VehicleJsonFactory getInstance() {
        if (instance == null) {
            instance = new VehicleJsonFactory();
        }
        return instance;
    }
    @Override
    public SwapiEntry parseJsonToEntry(JSONObject jsonObject) {
        return null;
    }
}
