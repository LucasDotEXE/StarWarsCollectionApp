package com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData;

public enum StarWarsDataType {
    FILM("films"), PEOPLE("people"), PLANET("planets"), SPECIES("species"), STARSHIP("starships"), VIHICLE("vehicles");

    private String dataType;

    private StarWarsDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }
}
