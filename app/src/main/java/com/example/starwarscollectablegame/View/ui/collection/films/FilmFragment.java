package com.example.starwarscollectablegame.View.ui.collection.films;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;

import java.util.List;

public class FilmFragment extends Fragment {

    private FilmViewModel filmViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_collection_film, container, false);

        this.filmViewModel = ViewModelProviders.of(this).get(FilmViewModel.class);

        final RecyclerView recyclerView = root.findViewById(R.id.filmRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(this.filmViewModel.getFilmAdapter());

        filmViewModel.getAllFilms().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                filmViewModel.getFilmAdapter().setFilms(films);
                for (Film film : films) {
//                    Log.wtf("FilmPrint", film.toString());
//                    filmViewModel.getStarWarsDataRepository().
//                            filmCollectionDatabaseEditHelper.insert(new FilmCollection(1, film.getEpisodeId(), 0));
                }
            }
        });

        return root;
    }

    private static final String TAG = "FilmFragment";
    @Override
    public void onResume() {
        super.onResume();

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = sharedPref.edit();
        String playerName = sharedPref.getString(getString(R.string.preferences_player_id), "");

        Log.wtf(TAG, "Ik heb het id: " + playerName);
        this.filmViewModel.getStarWarsDataRepository().getFilmCollectionByName(playerName).observe(this, new Observer<List<FilmCollection>>() {
            @Override
            public void onChanged(List<FilmCollection> filmCollections) {
                Log.wtf("Help", filmCollections.toString());
                filmViewModel.reloadList(filmCollections);
            }
        });
    }
}