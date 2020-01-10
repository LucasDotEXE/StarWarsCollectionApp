package com.example.starwarscollectablegame.View.ui.collection.people;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        final PeopleAdapter adapter = new PeopleAdapter(new ArrayList<People>());
        recyclerView.setAdapter(adapter);

        peopleViewModel.getAllPeople().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> people) {
                adapter.setFilms(people);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }
}