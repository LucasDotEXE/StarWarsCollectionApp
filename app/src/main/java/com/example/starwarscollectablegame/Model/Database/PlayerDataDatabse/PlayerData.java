package com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_table")
public class PlayerData {

    @PrimaryKey @NonNull
    private String player_name;

    private int avatar_id;

    public PlayerData(String player_name, int avatar_id) {
        this.player_name = player_name;
        this.avatar_id = avatar_id;
    }


    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(int avatar_id) {
        this.avatar_id = avatar_id;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                ", player_name='" + player_name + '\'' +
                ", avatar_id=" + avatar_id +
                '}';
    }
}
