package com.example.starwarscollectablegame.View.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.starwarscollectablegame.Model.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private static final String TAG = "ProfileFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final Button playerButton = root.findViewById(R.id.add_player);
        final TextView playerName = root.findViewById(R.id.player_name);

        final Spinner spinner = root.findViewById(R.id.spinner);

        final LifecycleOwner owner = this;
        final Context context = this.getContext();

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();


        Log.wtf(TAG, "Is het de defoult val?  :   " + sharedPref.getInt(getString(R.string.preferences_player_id), 437854378));
        spinner.setSelection(sharedPref.getInt(getString(R.string.preferences_player_id), 0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String playerName = (String) parent.getItemAtPosition(position);
                if (playerName.split(" ").length <= 0) {
                    Log.wtf(TAG, "ELVIS IS EXITING THE METHOD");
                    return;
                }
                String thing = playerName.split(" ")[0];
                int playerId = Integer.parseInt(thing.substring(0, thing.length()-1));

                Log.w(TAG, "Ewa ik zet het id naar: " + playerId);
                editor.putInt(getString(R.string.preferences_player_id), playerId).commit();
                Log.wtf(TAG, "Is het de defoult val?  :   " + sharedPref.getInt(getString(R.string.preferences_player_id), 437854378));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        profileViewModel.getRepository().getFilmCollection().observe(this, new Observer<List<FilmCollection>>() {
            @Override
            public void onChanged(List<FilmCollection> filmCollections) {

                ArrayList<String> playerId = new ArrayList<>();
                ArrayList<String> playerNames = new ArrayList<>();
                for (FilmCollection filmCollection : filmCollections) {
                    if (!playerId.contains(filmCollection.getPlayer_id() + "")) {
                        playerId.add(filmCollection.getPlayer_id() + "");
                        playerNames.add(filmCollection.getPlayer_id() + ".  " +filmCollection.getPlayer_name());
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                       android.R.layout.simple_spinner_item, playerNames.toArray(new String[] {}));
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });


        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileViewModel.getRepository().getFilmCollection().observe(owner, new Observer<List<FilmCollection>>() {
                    @Override
                    public void onChanged(List<FilmCollection> filmCollections) {
                        final ArrayList<String> playerId = new ArrayList<>();
                        for (FilmCollection filmCollection : filmCollections) {
                            if (!playerId.contains(filmCollection.getPlayer_id() + "")) {
                                playerId.add(filmCollection.getPlayer_id() + "");
                            }
                        }

                        profileViewModel.getRepository().getAllFilms().observe(owner, new Observer<List<Film>>() {
                            @Override
                            public void onChanged(List<Film> films) {
                                for (Film film : films) {
                                    Log.wtf(TAG, "Added Filmcolleciont, " + playerId.size());
                                    profileViewModel.getRepository().filmCollectionDatabaseEditHelper.insert(new FilmCollection(playerId.size(), playerName.getText().toString(), film.getEpisodeId(), 0, 0));
                                }
                                profileViewModel.getRepository().getAllFilms().removeObserver(this);
                                playerName.setText("");
                            }
                        });
                        profileViewModel.getRepository().getFilmCollection().removeObserver(this);
                    }
                });
            }
        });



        return root;
    }
}