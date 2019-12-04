package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONObject;

public class VehicleJsonFactory extends SwapiEntryJsonFactory {

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
