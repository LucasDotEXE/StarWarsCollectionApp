package com.example.starwarscollectablegame.View.ui.collection.films;

import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private static final String TAG = "FilmAdapter";

    private List<Film> films;

    public FilmAdapter(List<Film> films) {
        this.films = films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
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
        Film film = films.get(position);


        holder.name.setText(film.getTitle());
        holder.id.setText("Episode: " + film.getEpisodeId());
        holder.lvlBarr.setRating(2);
    }

    @Override
    public int getItemCount() {
            return films.size();
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
