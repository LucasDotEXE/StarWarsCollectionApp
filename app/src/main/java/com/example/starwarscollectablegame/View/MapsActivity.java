package com.example.starwarscollectablegame.View;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.starwarscollectablegame.Controller.StarWarsAPI.StarwarsApiManager;
import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryListener;
import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryPageListener;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Vehicle;
import com.example.starwarscollectablegame.R;
import com.example.starwarscollectablegame.ViewModel.swDatabaseViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SwapiEntryListener, SwapiEntryPageListener {

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;

    private swDatabaseViewModel swDatabaseViewModel;

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

//        apiManager.getSwapiEntryPage(this, StarWarsDataType.VIHICLE);

        mapFragment.getMapAsync(this);

        //
        this.swDatabaseViewModel = ViewModelProviders.of(this).get(swDatabaseViewModel.class);
        this.swDatabaseViewModel.getAllFilms().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {

            }
        });

        this.swDatabaseViewModel.getAllPeople().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> people) {

            }
        });

        this.swDatabaseViewModel.getAllPlanet().observe(this, new Observer<List<Planet>>() {
            @Override
            public void onChanged(List<Planet> people) {

            }
        });

        this.swDatabaseViewModel.getAllSpecies().observe(this, new Observer<List<Species>>() {
            @Override
            public void onChanged(List<Species> people) {

            }
        });

        this.swDatabaseViewModel.getAllStarship().observe(this, new Observer<List<Starship>>() {
            @Override
            public void onChanged(List<Starship> people) {
                for (Starship person : people) {
                    Log.i(TAG, person.toString());
                }
            }
        });

        this.swDatabaseViewModel.getAllVehicle().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(List<Vehicle> people) {
                for (Vehicle person : people) {
                    Log.i(TAG, person.toString());
                }
            }
        });
        //this.swDatabaseViewModel.deleteAllFilms();
        //
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
                            this, R.raw.sith_style_json));
//                            this, R.raw.test));

            if (!success) {
                Log.e("MAPACtivity", "Style parsing failed.");
            } else {
                Log.i("MapAct", "Map style succesfully loaded");
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
        switch (type) {
            case FILM: {
                for (SwapiEntry entry : entries) {
                    Film film = (Film) entry;
                    this.swDatabaseViewModel.insert(film);
                }
                break;
            }
            case PEOPLE: {
                for (SwapiEntry entry : entries) {
                    People film = (People) entry;
                    this.swDatabaseViewModel.insert(film);
                }
                break;
            }
            case PLANET: {
                for (SwapiEntry entry : entries) {
                    Planet film = (Planet) entry;
                    this.swDatabaseViewModel.insert(film);
                }
                break;
            }
            case SPECIES: {
                for (SwapiEntry entry : entries) {
                    Species film = (Species) entry;
                    this.swDatabaseViewModel.insert(film);
                }
                break;
            }
            case VIHICLE: {
                for (SwapiEntry entry : entries) {
                    Vehicle film = (Vehicle) entry;
                    this.swDatabaseViewModel.insert(film);
                }
                break;
            }
            case STARSHIP: {
                for (SwapiEntry entry : entries) {
                    Starship film = (Starship) entry;
                    this.swDatabaseViewModel.insert(film);
                }
                break;
            }
        }
        if (nextPage != -1) {
            apiManager.getSwapiEntryPage(this, nextPage, type);
        }
    }

    @Override
    public void onSwapiEntryPageError() {

    }
}
