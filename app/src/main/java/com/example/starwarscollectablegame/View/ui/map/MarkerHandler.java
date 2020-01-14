package com.example.starwarscollectablegame.View.ui.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.util.List;

import static com.example.starwarscollectablegame.View.ui.profile.AvatarViewAdapter.avatarStrings;

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

    public void handleMarkerClicked(Marker marker, final Context context, final LifecycleOwner lifecycleOwner, final StarWarsDataRepository repository) {
        String titleType = marker.getTitle().split(" ")[1].toLowerCase();
        String snipet = marker.getSnippet();

//        Log.e("asdasdasdasdasd", title);
        final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_id), Context.MODE_PRIVATE);
        if (titleType.equals("film")) {
            final LiveData<List<FilmCollection>> playerFilmCollecion = repository.getFilmCollectionByName(sharedPref.getString(context.getString(R.string.preferences_player_id), ""));
            playerFilmCollecion.observe(lifecycleOwner, new Observer<List<FilmCollection>>() {
                @Override
                public void onChanged(List<FilmCollection> filmCollections) {
                    updataRandomFilm(filmCollections, repository, context, lifecycleOwner);
                    playerFilmCollecion.removeObserver(this);
                }
            });
        } else if (titleType.equals("person")) {

        } else if (titleType.equals("planet")) {

        } else if (titleType.equals("species")) {

        } else if (titleType.equals("starship")) {

        } else if (titleType.equals("vehicle")) {

        } else if (titleType.equals("location")) {

            return;
        }
        marker.remove();
    }

    private void updataRandomFilm(final List<FilmCollection> filmCollections, final StarWarsDataRepository repository, final Context context, LifecycleOwner lifecycleOwner) {
        if (filmCollections.isEmpty())
            return;

        int i = (int) (Math.random() * filmCollections.size());
        final FilmCollection collection = filmCollections.get(i);
        int level = collection.getLevel();
        if (level >= 3) {
            int collectiveLevel = 0;
            for (FilmCollection filmCollection : filmCollections) {
                collectiveLevel += filmCollection.getLevel();
            }
            if (collectiveLevel == filmCollections.size()*3) {
                Toast.makeText(context, "You have every film maxed", Toast.LENGTH_SHORT).show();
            } else {
                updataRandomFilm(filmCollections, repository, context, lifecycleOwner);
            }
        } else {
            collection.setLevel(level + 1);
            repository.filmCollectionDatabaseEditHelper.update(collection);
            repository.getFilmById(collection.getFilm_id()).observe(lifecycleOwner, new Observer<List<Film>>() {
                @Override
                public void onChanged(List<Film> films) {
                    Toast.makeText(context,
                            films.get(0).getTitle() + " Leveled Up to Level " + collection.getLevel(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public MarkerOptions getLocationMarker(LatLng yourPosition, Resources resources, int avatarId) {
        int height = 100;
        int width = 100;


        InputStream imageStream = resources.openRawResource(avatarId);
        Bitmap b = BitmapFactory.decodeStream(imageStream);
//        Bitmap b = BitmapFactory.decodeResource(resources, R.drawable.ic_hooded);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


        MarkerOptions markerOptions = new MarkerOptions().position(yourPosition)
                .title("Your Location")
                .icon(smallMarkerIcon)
                .anchor(0.5f, .05f);
        return markerOptions;
    }

    public MarkerOptions getRandomHiddenMarker(LatLng yourPosition, Resources resources, Context context) {
        double standardDif = 0.001;

        double latDif = (Math.random() * standardDif * 2) - standardDif;
        double longDif = (Math.random() * standardDif * 2) - standardDif;

        LatLng diviatedPosition = new LatLng(
                yourPosition.latitude + latDif,
                yourPosition.longitude + longDif);

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
                MarkerOptions markerOptions = new MarkerOptions()
                .position(diviatedPosition)
                .snippet("Tap to Reveal\n What you got")
                .icon(smallMarkerIcon)
                .anchor(0.5f, .05f);

        double random = Math.random() * 1;
        if (random < 1) {
            markerOptions.title("Hidden Film");
        } else if (random < 2 && random > 1) {
            markerOptions.title("Hidden Person");
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