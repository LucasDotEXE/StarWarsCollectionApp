package com.example.starwarscollectablegame.View.ui.collection.people;

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
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.PersonColleciton;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.R;

import java.util.ArrayList;
import java.util.List;

public class PeopleFragment extends Fragment {

    private PeopleViewModel peopleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_collection_film, container, false);
        peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

        final RecyclerView recyclerView = root.findViewById(R.id.filmRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        final PeopleAdapter adapter = new PeopleAdapter(peopleViewModel.getStarWarsDataRepository());
        recyclerView.setAdapter(peopleViewModel.getPeopleAdapter());

        peopleViewModel.getStarWarsDataRepository().getAllPeople().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> people) {
//                Log.wtf(TAG, people.toString());
                peopleViewModel.getPeopleAdapter().setPeople(people);
                peopleViewModel.getPeopleAdapter().notifyDataSetChanged();
            }
        });

        return root;
    }

    private static final String TAG = "PeopleFragment";
    @Override
    public void onResume() {
        super.onResume();

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = sharedPref.edit();
        String playerName = sharedPref.getString(getString(R.string.preferences_player_id), "");

        Log.wtf(TAG, "Ik heb het id: " + playerName);
        this.peopleViewModel.getStarWarsDataRepository().getPeopleCollectionByName(playerName).observe(this, new Observer<List<PersonColleciton>>() {
            @Override
            public void onChanged(List<PersonColleciton> filmCollections) {
                Log.wtf("Help", filmCollections.toString());
                peopleViewModel.reloadList(filmCollections);
            }
        });
    }
}