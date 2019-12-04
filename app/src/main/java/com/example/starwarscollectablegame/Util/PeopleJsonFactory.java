package com.example.starwarscollectablegame.Util;

import com.example.starwarscollectablegame.Model.StarwarsData.People;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

public class PeopleJsonFactory extends SwapiEntryJsonFactory {

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
            person.setBirthYear(jsonObject.getString("birth_year"));
            person.setEyeColor(jsonObject.getString("eye_color"));




        } catch (JSONException e) {
            e.printStackTrace();
        }


        return person;
    }
}
