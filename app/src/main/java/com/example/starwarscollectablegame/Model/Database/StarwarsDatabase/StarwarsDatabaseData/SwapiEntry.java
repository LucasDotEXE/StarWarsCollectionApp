package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData;

import androidx.room.ColumnInfo;

public abstract class SwapiEntry {

    @ColumnInfo(name = "created")
    private String created;
    @ColumnInfo(name = "edited")
    private String edited;
    @ColumnInfo(name = "url")
    private String url;

    public SwapiEntry(String created, String edited, String url) {
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }
}
