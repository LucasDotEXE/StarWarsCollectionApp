package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

import org.json.JSONObject;

public interface SwapiEntryJsonFactory {
    SwapiEntry parseJsonToEntry(JSONObject jsonObject);
}
