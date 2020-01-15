package com.example.starwarscollectablegame.Model.GeoFencing;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.example.starwarscollectablegame.View.ui.map.MapViewModel;
import com.example.starwarscollectablegame.View.ui.map.MarkerHandler;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class GeoFenceHandler extends BroadcastReceiver {
    private static final String TAG = "GeoFenceHandler";
    private final int range = 50;
    private final int expiration = 60000;
    private  MapViewModel viewModel;
    private List<Geofence> geofenceList;
    private GeofencingClient geofencingClient;
    private Context context;
    private PendingIntent geofencePendingIntent;

    public GeoFenceHandler(Context context, MapViewModel viewModel)
    {
        this.context = context;
        this.viewModel = viewModel;
        this.geofencingClient = new GeofencingClient(this.context);
        this.geofenceList = new ArrayList<>();

    }
    public void addGeoFence(double lat, double lon, String key)
    {
        geofenceList.add(new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this
                // geofence.
                .setRequestId(key)

                .setCircularRegion(
                        lat,
                        lon,
                        range
                )
                .setExpirationDuration(expiration)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .setLoiteringDelay(10)
                .build());

        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Geofences added
                        Log.i(TAG, "Geofences added");
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add geofences
                        Log.e(TAG, "Geofence adding failed");
                        Log.i(TAG, "Geofence adding failed");

                        // ...
                    }
                });
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceList);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (geofencePendingIntent != null) {
            return geofencePendingIntent;
        }
        Intent intent = new Intent(this.context, GeoFenceHandler.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        geofencePendingIntent = PendingIntent.getBroadcast(this.context, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return geofencePendingIntent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent received");

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.e(TAG, "Error");
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            for (Geofence geofence : triggeringGeofences)
            {
                /*String key = geofence.getRequestId();
                Marker m = viewModel.markers.get(key);
                String titleType = m.getTitle().split(" ")[1].toLowerCase();

                if (titleType == "film")
                {
                    MarkerHandler.updataRandomFilm(viewModel.getFilmCollection().getValue(),viewModel.getHelperRepo(),this.context,viewModel.getApplication());

                }*/
            }


        } else {
            // Log the error.
            Log.e(TAG, "error");
        }
    }
}
