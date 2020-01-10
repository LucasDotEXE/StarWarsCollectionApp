package com.example.starwarscollectablegame.View.ui.collection.people;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.R;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private static final String TAG = "FilmAdapter";

    private List<People> people;

    public PeopleAdapter(List<People> films) {
        this.people = films;
    }

    public void setFilms(List<People> films) {
        this.people = films;
    }

    @NonNull
    @Override
    public PeopleAdapter.PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_collection_film , parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        People film = people.get(position);


        holder.name.setText(film.getName());
        holder.lvlBarr.setRating(2);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView id;
        public RatingBar lvlBarr;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.film_item_name);
            this.id = itemView.findViewById(R.id.film_item_id);
            this.lvlBarr = itemView.findViewById(R.id.film_item_lvl);
        }
    }
}
