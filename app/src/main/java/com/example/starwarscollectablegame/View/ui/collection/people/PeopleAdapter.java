package com.example.starwarscollectablegame.View.ui.collection.people;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.R;
import com.example.starwarscollectablegame.View.DetailFragments.PersonActivity;
import com.example.starwarscollectablegame.View.DetailFragments.PersonFragment;
import com.example.starwarscollectablegame.View.ui.collection.films.FilmAdapter;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private static final String TAG = "PeopleAdapter";

    private List<People> people;

    public PeopleAdapter(List<People> people) {
        this.people = people;
    }

    public void SetPeople(List<People> people) {
        this.people = people;
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
        People people = this.people.get(position);
        holder.name.setText(people.getName());
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), PersonFragment.class);
                    People person = people.get(PeopleViewHolder.super.getAdapterPosition());
                    intent.putExtra("object", person);
                    intent.putExtra("level", 2);
                    view.getContext().startActivity(intent);

                }
            });
        }
    }
}
