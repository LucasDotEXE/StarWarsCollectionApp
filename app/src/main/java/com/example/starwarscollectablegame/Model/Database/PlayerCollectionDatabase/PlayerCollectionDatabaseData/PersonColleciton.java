package com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "person_collection", primaryKeys = {"player_name", "person_id"})
public class PersonColleciton extends collectionBase {

    @NonNull
    private String player_name;
    @NonNull
    private String person_id;

    private int level;

    private int exp;

    public PersonColleciton(@NonNull String player_name, String person_id, int level, int exp) {
        this.player_name = player_name;
        this.person_id = person_id;
        this.level = level;
        this.exp = exp;
    }

    @NonNull
    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(@NonNull String player_name) {
        this.player_name = player_name;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "PersonColleciton{" +
                "player_name='" + player_name + '\'' +
                ", person_id=" + person_id +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }
}
