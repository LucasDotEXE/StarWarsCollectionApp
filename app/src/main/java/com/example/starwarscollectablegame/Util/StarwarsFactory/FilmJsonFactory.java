package com.example.starwarscollectablegame.Util.StarwarsFactory;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.starwarscollectablegame.Model.StarwarsData.Film;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.starwarscollectablegame.Util.JsonParsingMethods.jsonArrayToStringArray;

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
        Film film = new Film();

        try {
            film.setTitle(jsonObject.getString("title"));
            film.setEpisodeId(jsonObject.getInt("episode_id"));
            film.setOpeningCrawl(jsonObject.getString("opening_crawl"));
            film.setDirector(jsonObject.getString("director"));
            film.setProducer(jsonObject.getString("producer"));
            film.setReleaseDate(jsonObject.getString("release_date"));

            JSONArray characters = jsonObject.getJSONArray("characters");
            JSONArray planets = jsonObject.getJSONArray("planets");
            JSONArray starships = jsonObject.getJSONArray("starships");
            JSONArray vehicles = jsonObject.getJSONArray("vehicles");
            JSONArray species = jsonObject.getJSONArray("species");

            film.setCharactersUrls(jsonArrayToStringArray(characters));
            film.setPlanetsUrls(jsonArrayToStringArray(planets));
            film.setStarshipsUrls(jsonArrayToStringArray(starships));
            film.setVehiclesUrls(jsonArrayToStringArray(vehicles));
            film.setSpeciesUrls(jsonArrayToStringArray(species));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return film;
    }


}
