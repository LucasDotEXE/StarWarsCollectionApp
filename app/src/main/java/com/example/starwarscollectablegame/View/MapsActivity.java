package com.example.starwarscollectablegame.View;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.starwarscollectablegame.Controller.StarWarsAPI.StarwarsApiManager;
import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryListener;
import com.example.starwarscollectablegame.Model.StarwarsData.Film;
import com.example.starwarscollectablegame.Model.StarwarsData.People;
import com.example.starwarscollectablegame.Model.StarwarsData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsData.Species;
import com.example.starwarscollectablegame.Model.StarwarsData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;
import com.example.starwarscollectablegame.Model.StarwarsData.Vehicle;
import com.example.starwarscollectablegame.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SwapiEntryListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        StarwarsApiManager apiManager = new StarwarsApiManager(this);

        apiManager.getSwapiEntry(this, 1, StarWarsDataType.PLANET);


        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onEntryAvailavle(SwapiEntry object, StarWarsDataType type) {
        switch (type) {
            case FILM: {
                Log.e("TEST", ((Film) object).toString());
                break;
            }
            case PEOPLE: {
                Log.e("TEST", ((People) object).toString());
                break;
            }
            case PLANET: {
                Log.e("TEST", ((Planet) object).toString());
                break;
            }
            case SPECIES: {
                Log.e("TEST", ((Species) object).toString());
                break;
            }
            case VIHICLE: {
                Log.e("TEST", ((Vehicle) object).toString());
                break;
            }
            case STARSHIP: {
                Log.e("TEST", ((Starship) object).toString());
                break;
            }
        }
    }

    @Override
    public void onEntryError() {

    }



}
