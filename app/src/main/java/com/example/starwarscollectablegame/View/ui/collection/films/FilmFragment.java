package com.example.starwarscollectablegame.View.ui.collection.films;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;
import com.example.starwarscollectablegame.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FilmFragment extends Fragment {

    private FilmViewModel filmViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_film, container, false);
        filmViewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
        final TextView textView = root.findViewById(R.id.text_gallery);
//        filmViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        final RecyclerView recyclerView = root.findViewById(R.id.filmRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final FilmAdapter adapter = new FilmAdapter(new ArrayList<Film>());
        recyclerView.setAdapter(adapter);

        filmViewModel.getAllFilms().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                adapter.setFilms(films);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }
}