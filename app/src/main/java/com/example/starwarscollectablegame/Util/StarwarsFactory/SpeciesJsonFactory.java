package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.StarwarsData.Species;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.starwarscollectablegame.Util.JsonParsingMethods.jsonArrayToStringArray;

public class SpeciesJsonFactory implements SwapiEntryJsonFactory {

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
        Species species = new Species();

        try {
            species.setName(jsonObject.getString("name"));
            species.setClassification(jsonObject.getString("classification"));
            species.setDesignation(jsonObject.getString("designation"));
            species.setAverageHeight(jsonObject.getString("average_height"));
            species.setSkinColors(jsonObject.getString("skin_colors"));
            species.setHairColors(jsonObject.getString("hair_colors"));
            species.setEyeColors(jsonObject.getString("eye_colors"));
            species.setAverageLifespan(jsonObject.getString("average_lifespan"));
            species.setHomeWorld(jsonObject.getString("homeworld"));
            species.setLanguage(jsonObject.getString("language"));

            species.setPeopleUrls(jsonArrayToStringArray(jsonObject.getJSONArray("people")));
            species.setFilmsUrls(jsonArrayToStringArray(jsonObject.getJSONArray("films")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return species;
    }
}
