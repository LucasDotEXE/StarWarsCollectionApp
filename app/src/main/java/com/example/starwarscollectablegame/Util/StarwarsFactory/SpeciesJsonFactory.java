package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.starwarscollectablegame.Util.StarwarsFactory.JsonParsingMethods.jsonArrayToStringArray;

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

        try {
            String created = jsonObject.getString("created");
            String edited = jsonObject.getString("edited");
            String url = jsonObject.getString("url");

            String name = jsonObject.getString("name");
            String classification = jsonObject.getString("classification");
            String designation = jsonObject.getString("designation");
            String averageHeight = jsonObject.getString("average_height");
            String skinColors = jsonObject.getString("skin_colors");
            String hairColors = jsonObject.getString("hair_colors");
            String eyeColors = jsonObject.getString("eye_colors");
            String averageLifespan = jsonObject.getString("average_lifespan");
            String homeworld = jsonObject.getString("homeworld");
            String language = jsonObject.getString("language");

            ArrayList<String> people = jsonArrayToStringArray(jsonObject.getJSONArray("people"));
            ArrayList<String> films = jsonArrayToStringArray(jsonObject.getJSONArray("films"));

            return new Species(created, edited, url,
                    name, classification, designation,
                    averageHeight, averageLifespan,
                    eyeColors, hairColors,
                    skinColors, homeworld,
                    language, people, films);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
