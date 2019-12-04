package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.StarwarsData.People;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.starwarscollectablegame.Util.JsonParsingMethods.jsonArrayToStringArray;

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
        People person = new People();

        try {
            person.setName(jsonObject.getString("name"));
            person.setHeight(jsonObject.getString("height"));
            person.setMass(jsonObject.getString("mass"));
            person.setHairColor(jsonObject.getString("hair_color"));
            person.setSkinColor(jsonObject.getString("skin_color"));
            person.setEyeColor(jsonObject.getString("eye_color"));
            person.setBirthYear(jsonObject.getString("birth_year"));
            person.setGender(jsonObject.getString("gender"));
            person.setHomeWorldUrl(jsonObject.getString("homeworld"));

            person.setFilmsUrls(jsonArrayToStringArray(jsonObject.getJSONArray("films")));
            person.setSpeciesUrls(jsonArrayToStringArray(jsonObject.getJSONArray("species")));
            person.setVehiclesUrls(jsonArrayToStringArray(jsonObject.getJSONArray("vehicles")));
            person.setStarshipsUrls(jsonArrayToStringArray(jsonObject.getJSONArray("starships")));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return person;
    }
}
