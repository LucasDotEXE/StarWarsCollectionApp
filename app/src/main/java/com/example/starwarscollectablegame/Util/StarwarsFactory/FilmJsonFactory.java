package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.starwarscollectablegame.Util.StarwarsFactory.JsonParsingMethods.jsonArrayToStringArray;

public class FilmJsonFactory implements SwapiEntryJsonFactory {

    private static FilmJsonFactory instance;

    private FilmJsonFactory() {
    }

    public static FilmJsonFactory getInstance() {
        if (instance == null) {
            instance = new FilmJsonFactory();
        }
        return instance;
    }

    @Override
    public SwapiEntry parseJsonToEntry(JSONObject jsonObject) {
        try {
            String created = jsonObject.getString("created");
            String edited = jsonObject.getString("edited");
            String url = jsonObject.getString("url");

            String title =  jsonObject.getString("title");
            int episodeId = jsonObject.getInt("episode_id");
            String openingCrawl = jsonObject.getString("opening_crawl");
            String director = jsonObject.getString("director");
            String producer = jsonObject.getString("producer");
            String releaseDate = jsonObject.getString("release_date");

            ArrayList<String> characters = jsonArrayToStringArray(jsonObject.getJSONArray("characters"));
            ArrayList<String> planets = jsonArrayToStringArray(jsonObject.getJSONArray("planets"));
            ArrayList<String> starship = jsonArrayToStringArray(jsonObject.getJSONArray("starships"));
            ArrayList<String> vehicles = jsonArrayToStringArray(jsonObject.getJSONArray("vehicles"));
            ArrayList<String> species =  jsonArrayToStringArray(jsonObject.getJSONArray("species"));
            return new Film(created, edited, url, title, episodeId, openingCrawl, director,producer, releaseDate,
                    species, starship, vehicles, planets, characters);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
