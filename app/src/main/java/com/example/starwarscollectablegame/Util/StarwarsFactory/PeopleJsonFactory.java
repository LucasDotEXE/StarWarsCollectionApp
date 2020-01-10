package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.starwarscollectablegame.Util.StarwarsFactory.JsonParsingMethods.jsonArrayToStringArray;

public class PeopleJsonFactory implements SwapiEntryJsonFactory {

    private static PeopleJsonFactory instance;

    private PeopleJsonFactory() {
    }

    public static PeopleJsonFactory getInstance() {
        if (instance == null) {
            instance = new PeopleJsonFactory();
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
            String height = jsonObject.getString("height");
            String mass = jsonObject.getString("mass");
            String hairColor = jsonObject.getString("hair_color");
            String skinColor = jsonObject.getString("skin_color");
            String eyeColor =  jsonObject.getString("eye_color");
            String birthYear = jsonObject.getString("birth_year");
            String gender = jsonObject.getString("gender");
            String homeWorld = jsonObject.getString("homeworld");

            ArrayList<String> films = jsonArrayToStringArray(jsonObject.getJSONArray("films"));
            ArrayList<String> species = jsonArrayToStringArray(jsonObject.getJSONArray("species"));
            ArrayList<String> vehicles = jsonArrayToStringArray(jsonObject.getJSONArray("vehicles"));
            ArrayList<String> starships = jsonArrayToStringArray(jsonObject.getJSONArray("starships"));

            return new People(created, edited, url, name, eyeColor, birthYear, gender, hairColor, height, homeWorld, mass, skinColor,
                    films, species, starships, vehicles);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
