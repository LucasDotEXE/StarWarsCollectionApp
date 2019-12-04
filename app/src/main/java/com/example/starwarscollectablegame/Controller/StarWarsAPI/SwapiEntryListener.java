package com.example.starwarscollectablegame.Controller.StarWarsAPI;

import com.example.starwarscollectablegame.Model.StarwarsData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

public interface SwapiEntryListener {
    void onEntryAvailavle(SwapiEntry object, StarWarsDataType type);
    void onEntryError();
}
