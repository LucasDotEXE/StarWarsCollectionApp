package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONObject;

public abstract class SwapiEntryJsonFactory {
    private SwapiEntryJsonFactory instance;

    public abstract SwapiEntry parseJsonToEntry(JSONObject jsonObject);
}
