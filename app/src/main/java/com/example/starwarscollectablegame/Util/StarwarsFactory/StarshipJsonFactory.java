package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.starwarscollectablegame.Util.StarwarsFactory.JsonParsingMethods.jsonArrayToStringArray;

public class StarshipJsonFactory implements SwapiEntryJsonFactory {

    private static StarshipJsonFactory instance;

    private StarshipJsonFactory() {
    }

    public static StarshipJsonFactory getInstance() {
        if (instance == null) {
            instance = new StarshipJsonFactory();
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
            String model = jsonObject.getString("model");
            String manufacturer = jsonObject.getString("manufacturer");
            String costInCredits = jsonObject.getString("cost_in_credits");
            String length = jsonObject.getString("length");
            String maxAtmospheringSpeed = jsonObject.getString("max_atmosphering_speed");
            String crew = jsonObject.getString("crew");
            String passengers = jsonObject.getString("passengers");
            String cargoCapacity = jsonObject.getString("cargo_capacity");
            String consumables = jsonObject.getString("consumables");
            String hyperdriveRating = jsonObject.getString("hyperdrive_rating");
            String mglt = jsonObject.getString("MGLT");
            String starshipClass = jsonObject.getString("starship_class");

            ArrayList<String> pilots = jsonArrayToStringArray(jsonObject.getJSONArray("pilots"));
            ArrayList<String> films = jsonArrayToStringArray(jsonObject.getJSONArray("films"));

            return new Starship(created, edited, url, name, model, manufacturer, costInCredits, length, crew,
                    passengers, maxAtmospheringSpeed, cargoCapacity, consumables, mglt, starshipClass, hyperdriveRating, pilots, films);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
