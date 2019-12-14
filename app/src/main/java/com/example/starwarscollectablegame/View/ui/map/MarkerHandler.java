package com.example.starwarscollectablegame.View.ui.map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.starwarscollectablegame.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerHandler {

    private static MarkerHandler instance;

    private MarkerHandler() {

    }

    public static MarkerHandler getInstance() {
        if (instance == null) {
            instance = new MarkerHandler();
        }
        return instance;
    }

    public void handleMarkerClicked(Marker marker, Context context) {
        String title = marker.getTitle().split(" ")[1].toLowerCase();
        String snipet = marker.getSnippet();

        Log.e("asdasdasdasdasd", title);

        switch (title) {
            case ("film") : {

                break;
            }
            case ("asd") : {

                break;
            }
            case ("egr") : {

                break;
            }
        }


    }

    public MarkerOptions getLocationMarker(LatLng yourPosition, Resources resources) {
        int height = 100;
        int width = 100;
        Bitmap b = BitmapFactory.decodeResource(resources, R.drawable.ic_hooded);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);
        MarkerOptions markerOptions = new MarkerOptions().position(yourPosition)
                .title("Your Location")
                .icon(smallMarkerIcon)
                .anchor(0.5f, .05f);
        return markerOptions;
    }

    public MarkerOptions getRandomHiddenMarker(LatLng yourPosition, Resources resources) {
        Bitmap b = BitmapFactory.decodeResource(resources, R.drawable.ic_hooded);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);
        MarkerOptions markerOptions = new MarkerOptions().position(yourPosition)
                .snippet("Tap to Reveal\n What you got")
                .icon(smallMarkerIcon)
                .anchor(0.5f, .05f);

        double random = Math.random()*6;
        if (random < 1 ) {
            markerOptions.title("Hidden Film");
        } else if (random < 2 && random > 1) {
            markerOptions.title("Hidden People");
        } else if (random < 3 && random > 2) {
            markerOptions.title("Hidden Planet");
        } else if (random < 4 && random > 3) {
            markerOptions.title("Hidden Species");
        } else if (random < 5 && random > 4) {
            markerOptions.title("Hidden Starship");
        } else if (random < 6 && random > 5) {
            markerOptions.title("Hidden Vehicle");
        }
        return markerOptions;
    }

}