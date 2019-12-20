package com.example.starwarscollectablegame.View.ui.collection.films;

import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private static final String TAG = "FilmAdapter";

    private List<FilmCollection> filmCollections;
    private List<Film> films;

    private StarWarsDataRepository repository;

    public FilmAdapter(StarWarsDataRepository repository) {
        this.filmCollections = new ArrayList<>();
        this.films = new ArrayList<>();
        this.repository = repository;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
        this.notifyDataSetChanged();
    }

    public void setFilmCollections(List<FilmCollection> filmCollections) {
        this.filmCollections = filmCollections;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_collection_film , parent, false);
        return new FilmAdapter.FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        FilmCollection collection = filmCollections.get(position);

        Film film = null;

        holder.lvlBarr.setRating(collection.getLevel());
        for (Film film1 : films) {
            if (film1.getEpisodeId() == collection.getFilm_id()) {
                film = film1;
            }
        }
        if (film == null) {
            return;
        }
        Log.wtf("Getting film from database Film adapter", film.toString());
        holder.name.setText(film.getTitle());
        holder.id.setText("Episode: " + film.getEpisodeId());
    }

    @Override
    public int getItemCount() {
            return filmCollections.size();
    }

    public class  FilmViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView id;
        public RatingBar lvlBarr;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.film_item_name);
            this.id = itemView.findViewById(R.id.film_item_id);
            this.lvlBarr = itemView.findViewById(R.id.film_item_lvl);
        }
    }
}
