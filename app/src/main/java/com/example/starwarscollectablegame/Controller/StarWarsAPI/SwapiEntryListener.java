package com.example.starwarscollectablegame.Controller.StarWarsAPI;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

public interface SwapiEntryListener {
    void onEntryAvailavle(SwapiEntry object, StarWarsDataType type);
    void onEntryError();
}
