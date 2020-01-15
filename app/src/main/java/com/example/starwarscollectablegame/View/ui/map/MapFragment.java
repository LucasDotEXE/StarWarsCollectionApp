package com.example.starwarscollectablegame.View.ui.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapFragment";
    private final int PERMISSION_REQUEST_CODE = 698;
    final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    private LatLng yourPosition = new LatLng(0,0);

    private LocationListener listener;
    private LocationManager locManager;
    private static long distanceInMeters;
    private static Location lastLocation = null;
    public static final String PERMISSION_STRING
            = android.Manifest.permission.ACCESS_FINE_LOCATION;

    private GoogleMap mMap;

    private Marker locationMarker;

    MapViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        if (savedInstanceState != null) {
            viewModel = (MapViewModel) savedInstanceState.getSerializable("viewmodel");
            addMarkersToMap(viewModel.markers);
        }
        else
            viewModel = ViewModelProviders.of(this).get(MapViewModel.class);


        viewModel.getFilmCollection().observe(this, new Observer<List<FilmCollection>>() {
            @Override
            public void onChanged(List<FilmCollection> filmCollections) {
                for (FilmCollection filmCollection : filmCollections) {
                    Log.d("TEST ONCREATE MAPFRAG", filmCollection.toString());
                }
            }
        });
        viewModel.getAllFilms().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                Log.wtf(TAG, films.toString());
                for (Film film : films) {
//                    viewModel.updateFilmColletion(new FilmCollection(1, "Test", film.getEpisodeId(), 0, 0));
                }
            }
        });

//        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.fragment_map);
//        getFragmentManager().findFragmentById(R.id.fragment_map);
//        mapFragment.getMapAsync(mapViewModel);

        if (mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
            mapFrag.getMapAsync(this);
        }

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        setAdapter(new InfoWindowAdapter(getActivity()));

        final LifecycleOwner lifecycleOwner = this;
        this.mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                MarkerHandler.handleMarkerClicked(marker, getContext(), lifecycleOwner, viewModel.getHelperRepo());
            }
        });

        /*set map appearance and behaviour*/
        setMapSettings();
        updateMapStyle();

        //Track Location
        setLocationListener();
        trackDistance();

        startSpawning();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putSerializable("viewmodel", viewModel);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void addMarkersToMap(List<Marker> markers)
    {
        Context context = this.getContext();
        Resources resources = getResources();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_id), Context.MODE_PRIVATE);
        boolean useSith = sharedPref.getBoolean(context.getString(R.string.preferences_theme_use_sith), false);

        Bitmap b;
        if (useSith) {
            b = BitmapFactory.decodeResource(resources, R.drawable.ic_question_red);
        } else  {
            b = BitmapFactory.decodeResource(resources, R.drawable.ic_question_blue);
        }

        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        //starts random spawning of markers
        for (Marker marker : markers) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(marker.getPosition())
                    .snippet(marker.getSnippet())
                    .icon(smallMarkerIcon)
                    .anchor(0.5f, .05f);
            mMap.addMarker(markerOptions);
        }
    }



    private void startSpawning() {
        spawnHandler = new Handler();
        spawnHandler.post(spawnNewCollectionMarker);
    }
    private Handler spawnHandler;
    private Runnable spawnNewCollectionMarker = new Runnable() {
        @Override
        public void run() {
            int min = 0;
            int sec = 5;

            int timeInMs = sec * 1000 + min * 60000;
            spawnHandler.postDelayed(spawnNewCollectionMarker, timeInMs);

            /*if (viewModel.markers.values().size() >= 5) {
                Marker marker = viewModel.markers.get(0);
                marker.remove();
                String key = viewModel.markers.keySet().
                viewModel.markers.re


            }*/

            if (viewModel.markers.size() >= 5)
            {
                Marker m = viewModel.markers.get(00);
                m.remove();
                viewModel.markers.remove(m);
            }


            try {
                        Marker m =
                        mMap.addMarker(
                                MarkerHandler.getRandomHiddenMarker(yourPosition ,
                                        getResources(), getContext()));
//                        viewModel.getGeoFenceHandler().addGeoFence(
//                                m.getPosition().latitude,
//                                m.getPosition().longitude,
//                                Integer.toString(viewModel.getMarkerCounter()));
                        viewModel.markers.add(m);
                        //viewModel.markers.put(Integer.toString(viewModel.getMarkerCounter()),m);

            } catch (IllegalStateException ex) {
                Log.w(TAG, "UnstableTimerMethod replaced with new one");
            }
        }
    };

    private void setAdapter(GoogleMap.InfoWindowAdapter adapter) {
        mMap.setInfoWindowAdapter(adapter);
    }

    private void updateMapStyle() {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.

            SharedPreferences sharedPref = Objects.requireNonNull(getContext()).getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);

            boolean useSith = sharedPref.getBoolean(getString(R.string.preferences_theme_use_sith), false);
            boolean success;
            if (useSith) {
                success = this.mMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getContext(),
                                R.raw.sith_style_json));
            } else  {
                success = this.mMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getContext(),
                                R.raw.jedi_style_json));
            }

            if (!success) {
                Log.e("MAPACtivity", "Style parsing failed.");
            } else {
                Log.i("MapAct", "Map style succesfully loaded");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MAPACtivity", "Can't find style. Error: ", e);
        }
    }

    private void setMapSettings() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMinZoomPreference(15);
        mMap.setMaxZoomPreference(20);
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(false);
    }

    private void setLocationListener() {
        this.listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distanceInMeters += location.distanceTo(lastLocation);
                lastLocation = location;
                Log.i(TAG, location.getLatitude() + " " + location.getLongitude());
                updatePositionMarker(location);
            }

            @Override
            public void onProviderDisabled(String arg0) {
            }

            @Override
            public void onProviderEnabled(String arg0) {
            }

            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle bundle) {
            }
        };

        this.locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    private void updatePositionMarker(Location location) {

        final LatLng yourPosition = new LatLng(location.getLatitude(), location.getLongitude());
        this.yourPosition = yourPosition;

        final SharedPreferences sharedPref = Objects.requireNonNull(getContext()).getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        final String name = sharedPref.getString(getString(R.string.preferences_player_id), "Not Found");
//        final String name = "Lucas";
        viewModel.getHelperRepo().playerDataDatabaseEditHelper.getPlayerByName(name).observe(getViewLifecycleOwner(), new Observer<List<PlayerData>>() {
            @Override
            public void onChanged(List<PlayerData> playerData) {
                int avatar_id = R.raw.icon_luke_skywalker;
                if (!playerData.isEmpty()) {
                    avatar_id = playerData.get(0).getAvatar_id();
                }
                    MarkerOptions markerOptions = MarkerHandler.getLocationMarker(yourPosition, Objects.requireNonNull(getActivity()), avatar_id);

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();

                    updateCameraAndBounds(builder, yourPosition, markerOptions);

                viewModel.getHelperRepo().playerDataDatabaseEditHelper.getPlayerByName(name).removeObserver(this);
            }
        });
    }

    private void updateCameraAndBounds(LatLngBounds.Builder builder, final LatLng yourPosition, MarkerOptions markerOptions) {
        if (this.locationMarker != null) {
            builder.include(locationMarker.getPosition());
            this.locationMarker.remove();
        }
        this.locationMarker = this.mMap.addMarker(markerOptions);

        builder.include(yourPosition);
        LatLngBounds bounds = builder.build();
        mMap.setLatLngBoundsForCameraTarget(bounds);

        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
                .target(yourPosition)
                .zoom(mMap.getCameraPosition().zoom)
                .tilt(mMap.getCameraPosition().tilt)
                .bearing(mMap.getCameraPosition().bearing)
                .build()));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(yourPosition);
                LatLngBounds bounds = builder.build();
                mMap.setLatLngBoundsForCameraTarget(bounds);
            }
        }, 3000);
    }

    private void trackDistance() {
        if (ContextCompat.checkSelfPermission(getContext(), PERMISSION_STRING)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {PERMISSION_STRING}, PERMISSION_REQUEST_CODE);
            return;
        }
        locManager.requestLocationUpdates(LOCATION_PROVIDER, 5000,   1, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult granted!");
                trackDistance();
            } else {
                Log.d(TAG, "onRequestPermissionsResult NOT granted!");
            }
        }
    }
}