package com.example.starwarscollectablegame.View.ui.map;

import android.app.Person;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.PersonColleciton;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
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

    public static void handleMarkerClicked(Marker marker, final Context context, final LifecycleOwner lifecycleOwner, final StarWarsDataRepository repository) {
        if (marker.getTitle().equals(context.getString(R.string.marker_yourlocation))) {
            return;
        }
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
        } else if (titleType.equals("person") || titleType.equals("persoon")) {
            Log.wtf("sdgfqweyqwduyfgeqiuhfqwiuyf", "dfhasdifuhidufhqwf");
            final LiveData<List<PersonColleciton>> playerFilmCollecion = repository.getPeopleCollectionByName(sharedPref.getString(context.getString(R.string.preferences_player_id), ""));
            playerFilmCollecion.observe(lifecycleOwner, new Observer<List<PersonColleciton>>() {
                @Override
                public void onChanged(List<PersonColleciton> filmCollections) {
                    updataRandomPerson(filmCollections, repository, context, lifecycleOwner);
                    playerFilmCollecion.removeObserver(this);
                }
            });

        } else if (titleType.equals("planet")) {

        } else if (titleType.equals("species")) {

        } else if (titleType.equals("starship")) {

        } else if (titleType.equals("vehicle")) {

        }
        marker.remove();
    }

    public static void updataRandomFilm(final List<FilmCollection> filmCollections, final StarWarsDataRepository repository, final Context context, LifecycleOwner lifecycleOwner) {
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
                Toast.makeText(context, context.getString(R.string.markerhandler_maxed_film), Toast.LENGTH_SHORT).show();
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
                            films.get(0).getTitle() + context.getString(R.string.markerhanlder_levelup) + collection.getLevel(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public static void updataRandomPerson(final List<PersonColleciton> personCollecitons, final StarWarsDataRepository repository, final Context context, LifecycleOwner lifecycleOwner) {
        if (personCollecitons.isEmpty())
            return;

        int i = (int) (Math.random() * personCollecitons.size());
        final PersonColleciton collection = personCollecitons.get(i);
        int level = collection.getLevel();
        if (level >= 3) {
            int collectiveLevel = 0;
            for (PersonColleciton filmCollection : personCollecitons) {
                collectiveLevel += filmCollection.getLevel();
            }
            if (collectiveLevel == personCollecitons.size()*3) {
                Toast.makeText(context, context.getString(R.string.markerhandler_maxed_film), Toast.LENGTH_SHORT).show();
            } else {
                updataRandomPerson(personCollecitons, repository, context, lifecycleOwner);
            }
        } else {
            collection.setLevel(level + 1);
            repository.personCollectionDatabaseEditHelper.update(collection);
            repository.getPersonById(collection.getPerson_id()).observe(lifecycleOwner, new Observer<List<People>>() {
                @Override
                public void onChanged(List<People> films) {
                    Toast.makeText(context,
                            films.get(0).getName() + context.getString(R.string.markerhanlder_levelup) + collection.getLevel(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public static MarkerOptions getLocationMarker(LatLng yourPosition, FragmentActivity activity, int avatarId) {
        int height = 100;
        int width = 100;
        InputStream imageStream = activity.getResources().openRawResource(avatarId);
        Bitmap b = BitmapFactory.decodeStream(imageStream);
//        Bitmap b = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_hooded);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        SharedPreferences sharedPref = activity.getSharedPreferences(activity.getString(R.string.preference_id), Context.MODE_PRIVATE);
        String playerName = sharedPref.getString(activity.getString(R.string.preferences_player_id), "Please Select A Profile");

        MarkerOptions markerOptions = new MarkerOptions().position(yourPosition)
                .title(activity.getString(R.string.marker_yourlocation))
                .icon(smallMarkerIcon)
                .anchor(0.5f, .05f);

        return markerOptions;
    }

    public static MarkerOptions getRandomHiddenMarker(LatLng yourPosition, Resources resources, Context context) {
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
                .snippet(context.getString(R.string.markerhandler_clicktoreveal))
                .icon(smallMarkerIcon)
                .anchor(0.5f, .05f);

        double random = Math.random() * 2;
        if (random < 1) {
            markerOptions.title(context.getString(R.string.markerhandler_hidden_film));
        } else if (random < 2 && random > 1) {
            markerOptions.title(context.getString(R.string.markerhandler_hidden_people));
        } else if (random < 3 && random > 2) {
            markerOptions.title(context.getString(R.string.markerhandler_hidden_planet));
        } else if (random < 4 && random > 3) {
            markerOptions.title(context.getString(R.string.markerhandler_hidden_species));
        } else if (random < 5 && random > 4) {
            markerOptions.title(context.getString(R.string.markerhandler_hidden_starship));
        } else if (random < 6 && random > 5) {
            markerOptions.title(context.getString(R.string.markerhandler_hidden_vehicle));
        }
        return markerOptions;
    }

}