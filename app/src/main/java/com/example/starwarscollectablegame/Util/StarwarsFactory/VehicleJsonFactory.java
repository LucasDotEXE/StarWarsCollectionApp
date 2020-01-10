package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Vehicle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VehicleJsonFactory implements SwapiEntryJsonFactory {
    ;

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

        try {
            String created = jsonObject.getString("created");
            String edited = jsonObject.getString("edited");
            String url = jsonObject.getString("url");

            String name = jsonObject.getString("name");
            String model = jsonObject.getString("model");
            String manufacturer = jsonObject.getString("manufacturer");
            String cost_in_credits = jsonObject.getString("cost_in_credits");
            String length = jsonObject.getString("length");
            String max_atmosphering_speed = jsonObject.getString("max_atmosphering_speed");
            String crew = jsonObject.getString("crew");
            String passengers = jsonObject.getString("passengers");
            String cargo_capacity = jsonObject.getString("cargo_capacity");
            String consumables = jsonObject.getString("consumables");
            String vehicle_class = jsonObject.getString("vehicle_class");

            ArrayList<String> pilots = JsonParsingMethods.jsonArrayToStringArray(jsonObject.getJSONArray("pilots"));
            ArrayList<String> films = JsonParsingMethods.jsonArrayToStringArray(jsonObject.getJSONArray("films"));




            return new Vehicle(created, edited, url, name, model, vehicle_class, manufacturer, cost_in_credits,
                    length, crew, passengers, max_atmosphering_speed, cargo_capacity, consumables, pilots, films);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
