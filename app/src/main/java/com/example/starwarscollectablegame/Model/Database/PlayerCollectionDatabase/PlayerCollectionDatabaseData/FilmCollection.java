package com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "film_collection", primaryKeys = {"player_name", "film_id"})
public class FilmCollection extends ewa{

    @NonNull
    private String player_name;
    @NonNull
    private int film_id;

    private int level;

    private int exp;

    public FilmCollection(String player_name, int film_id, int level, int exp) {
        this.player_name = player_name;
        this.film_id = film_id;
        this.level = level;
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public int getExp() {
        return exp;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "FilmCollection{" +
                ", player_name='" + player_name + '\'' +
                ", film_id=" + film_id +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }
}
