package com.example.starwarscollectablegame.Model.StarwarsData;

import java.io.Serializable;

public class Starship extends Vehicle implements Serializable {

    public String starshipClass;

    public String hyperdriveRating;

    public String mglt;


    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public void setHyperdriveRating(String hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    public String getMglt() {
        return mglt;
    }

    public void setMglt(String mglt) {
        this.mglt = mglt;
    }

    @Override
    public String toString() {

        return "Starship{" +
                "starshipClass='" + starshipClass + '\'' +
                ", hyperdriveRating='" + hyperdriveRating + '\'' +
                ", mglt='" + mglt + '\'' +
                '}' + "\n" +
                super.toString();
    }
}
