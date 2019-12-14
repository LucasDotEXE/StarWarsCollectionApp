package com.example.starwarscollectablegame.View.ui.map;

import android.content.Context;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "ODO APP";
    private final int PERMISSION_REQUEST_CODE = 698;
    final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;


    private LocationListener listener;
    private LocationManager locManager;
    private static long distanceInMeters;
    private static Location lastLocation = null;
    public static final String PERMISSION_STRING
            = android.Manifest.permission.ACCESS_FINE_LOCATION;

    private GoogleMap mMap;

    private Marker locationMarker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);


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

        this.mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                MarkerHandler.getInstance().handleMarkerClicked(marker, getContext());
            }
        });

//        int height = 150;
//        int width = 150;
//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hooded);
//        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
//        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);
//        LatLng avans = new LatLng(51.5773462, 4.7962926);
//        MarkerOptions markerOptions = new MarkerOptions().position(avans)
//                .title("Marker in Avans")
//                .icon(smallMarkerIcon);
//        mMap.addMarker(markerOptions);
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(avans).tilt(45).build()));



        setMapSettings();
        updateMapStyle();

        //Track Location
        setLocationListener();
        trackDistance();
    }

    private void setAdapter(GoogleMap.InfoWindowAdapter adapter) {
        mMap.setInfoWindowAdapter(adapter);
    }

    private void updateMapStyle() {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = this.mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(),
//                            R.raw.jedi_style_json));
                            R.raw.sith_style_json));
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
                Log.wtf(TAG, location.getLatitude() + " " + location.getLongitude());
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

        MarkerOptions markerOptions = MarkerHandler.getInstance().getRandomHiddenMarker(yourPosition, getResources());

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        updateCameraAndBounds(builder, yourPosition, markerOptions);

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