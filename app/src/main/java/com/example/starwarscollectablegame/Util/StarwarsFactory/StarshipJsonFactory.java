package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.StarwarsData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.starwarscollectablegame.Util.JsonParsingMethods.jsonArrayToStringArray;

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
        Starship starship = new Starship();

        try {
            starship.setName(jsonObject.getString("name"));
            starship.setModel(jsonObject.getString("model"));
            starship.setManufacturer(jsonObject.getString("manufacturer"));
            starship.setCostInCredits(jsonObject.getString("cost_in_credits"));
            starship.setLength(jsonObject.getString("length"));
            starship.setMaxAtmospheringSpeed(jsonObject.getString("max_atmosphering_speed"));
            starship.setCrew(jsonObject.getString("crew"));
            starship.setPassengers(jsonObject.getString("passengers"));
            starship.setCargoCapacity(jsonObject.getString("cargo_capacity"));
            starship.setConsumables(jsonObject.getString("consumables"));
            starship.setHyperdriveRating(jsonObject.getString("hyperdrive_rating"));
            starship.setMglt(jsonObject.getString("MGLT"));
            starship.setStarshipClass(jsonObject.getString("starship_class"));

            starship.setPilotsUrls(jsonArrayToStringArray(jsonObject.getJSONArray("pilots")));
            starship.setFilmsUrls(jsonArrayToStringArray(jsonObject.getJSONArray("films")));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return starship;
    }
}
