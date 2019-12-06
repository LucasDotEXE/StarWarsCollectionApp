package com.example.starwarscollectablegame.View;

import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.starwarscollectablegame.Controller.StarWarsAPI.StarwarsApiManager;
import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryListener;
import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryPageListener;
import com.example.starwarscollectablegame.Model.StarwarsData.Film;
import com.example.starwarscollectablegame.Model.StarwarsData.People;
import com.example.starwarscollectablegame.Model.StarwarsData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsData.Species;
import com.example.starwarscollectablegame.Model.StarwarsData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;
import com.example.starwarscollectablegame.Model.StarwarsData.Vehicle;
import com.example.starwarscollectablegame.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SwapiEntryListener, SwapiEntryPageListener {

    private GoogleMap mMap;

    private StarwarsApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        this.apiManager = new StarwarsApiManager(this);

//        apiManager.getSwapiEntry(this, 1, StarWarsDataType.FILM);
//        apiManager.getSwapiEntry(this, 1, StarWarsDataType.PEOPLE);
//        apiManager.getSwapiEntry(this, 1, StarWarsDataType.PLANET);
//        apiManager.getSwapiEntry(this, 1, StarWarsDataType.SPECIES);
//        apiManager.getSwapiEntry(this, 7, StarWarsDataType.STARSHIP);
//        apiManager.getSwapiEntry(this, 7, StarWarsDataType.VIHICLE);

        apiManager.getSwapiEntryPage(this, 1, StarWarsDataType.SPECIES);

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
        mMap = googleMap;;

        // Add a marker in Sydney and move the camera
        LatLng avans = new LatLng(51.5773462, 4.7962926);
        mMap.addMarker(new MarkerOptions().position(avans).title("Marker in Avans"));
        mMap.setMinZoomPreference(15);
        mMap.setMaxZoomPreference(20);
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);
//        mMap.setMapType();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(avans).tilt(45).build()));

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.sith_style_json)); //raw vind hem wel gewoon
//                            this, R.raw.jedi_style_json)); //raw vind hem wel gewoon

            if (!success) {
                Log.e("MAPACtivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MAPACtivity", "Can't find style. Error: ", e);
        }

    }

    @Override
    public void onEntryAvailavle(SwapiEntry object, StarWarsDataType type) {
//        switch (type) {
//            case FILM: {
//                Log.e("TEST", ((Film) object).toString());
//                break;
//            }
//            case PEOPLE: {
//                Log.e("TEST", ((People) object).toString());
//                break;
//            }
//            case PLANET: {
//                Log.e("TEST", ((Planet) object).toString());
//                break;
//            }
//            case SPECIES: {
//                Log.e("TEST", ((Species) object).toString());
//                break;
//            }
//            case VIHICLE: {
//                Log.e("TEST", ((Vehicle) object).toString());
//                break;
//            }
//            case STARSHIP: {
//                Log.e("TEST", ((Starship) object).toString());
//                break;
//            }
//        }
    }

    @Override
    public void onEntryError() {

    }


    @Override
    public void onSwapiEntryPageListener(ArrayList<SwapiEntry> entries, StarWarsDataType type, int nextPage) {
//        switch (type) {
//            case FILM: {
//                for (SwapiEntry entry : entries) {
//                    Log.e("TEST", ((Film) entry).toString());
//                }
//                break;
//            }
//            case PEOPLE: {
////                Log.e("TEST", ((People) object).toString());
//                break;
//            }
//            case PLANET: {
////                Log.e("TEST", ((Planet) object).toString());
//                break;
//            }
//            case SPECIES: {
//                for (SwapiEntry entry : entries) {
//                    Log.e("TEST", ((Species) entry).toString());
//                }
//                break;
//            }
//            case VIHICLE: {
////                Log.e("TEST", ((Vehicle) object).toString());
//                break;
//            }
//            case STARSHIP: {
////                Log.e("TEST", ((Starship) object).toString());
//                break;
//            }
//        }
        if (nextPage != -1) {
            apiManager.getSwapiEntryPage(this, nextPage, type);
        }
    }

    @Override
    public void onSwapiEntryPageError() {

    }
}
