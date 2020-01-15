package com.example.starwarscollectablegame.View.ui.collection.people;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.PersonColleciton;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.R;
import com.example.starwarscollectablegame.View.DetailFragments.PersonActivity;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private static final String TAG = "PeopleAdapter";

    private List<PersonColleciton> personCollecitons;
    private List<People> people;

    private StarWarsDataRepository repository;

    public PeopleAdapter(StarWarsDataRepository repository) {
        this.personCollecitons = new ArrayList<>();
        this.people = new ArrayList<>();
        this.repository = repository;
    }

    public void setPeople(List<People> films) {
        this.people = films;
        this.notifyDataSetChanged();
    }

    public void setPeopleCollection(List<PersonColleciton> filmCollections) {
        this.personCollecitons = filmCollections;
    }

    @NonNull
    @Override
    public PeopleAdapter.PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_collection_film , parent, false);
        return new PeopleAdapter.PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.PeopleViewHolder viewHolder, int position) {
        PersonColleciton collection = personCollecitons.get(position);

        People people = null;

        viewHolder.lvlBarr.setRating(collection.getLevel());
        for (People film1 : this.people) {
            if (film1.getName().equals(collection.getPerson_id())) {
                people = film1;
                Log.wtf(film1.getName(), collection.getPerson_id());
            }
        }
        if (people == null) {
            Log.wtf("guhfewriugfhewoifheqwoiuhgqwoeiufhoqwiuh", "dfiuhrofiuywgqeoefiugw  oiug");
            return;
        }
        Log.wtf("Getting film from database Film adapter", people.toString());
        viewHolder.name.setText(people.getName());
//        viewHolder.id.setText("Episode: " + people.get());
    }

    @Override
    public int getItemCount() {
        return personCollecitons.size();
    }

    public class  PeopleViewHolder extends RecyclerView.ViewHolder {

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
                    Intent intent = new Intent(view.getContext(), PersonActivity.class);
                    People person = people.get(PeopleViewHolder.super.getAdapterPosition());

                    PersonColleciton colleciton = null;
                    for (PersonColleciton personColleciton : personCollecitons) {
                        if (personColleciton.getPerson_id().equals(person.getName())) {
                            colleciton = personColleciton;
                        }
                    }
                    if (colleciton != null) {

                        intent.putExtra("level", colleciton.getLevel());
                    } else {
                        intent.putExtra("level", 2);
                    }
                    intent.putExtra("object", person);
                    view.getContext().startActivity(intent);


                }
            });

        }
    }
}
