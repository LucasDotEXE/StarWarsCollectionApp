package com.example.starwarscollectablegame.Controller.StarWarsAPI;

import com.example.starwarscollectablegame.Model.StarwarsData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import java.util.ArrayList;

public interface SwapiEntryPageListener {
    void onSwapiEntryPageListener(ArrayList<SwapiEntry> entries, StarWarsDataType type, int nextPage);
    void onSwapiEntryPageError();
}
