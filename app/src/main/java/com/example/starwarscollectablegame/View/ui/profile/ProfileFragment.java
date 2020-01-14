package com.example.starwarscollectablegame.View.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.FilmCollection;
import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private ProfileViewAdapter adapter;

    private static final String TAG = "ProfileFragment";
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final Button playerButton = root.findViewById(R.id.add_player);
        final TextView playerName = root.findViewById(R.id.player_name);
        final FloatingActionButton addPlayerButton = root.findViewById(R.id.add_player_button);
        final RecyclerView recyclerView = root.findViewById(R.id.player_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final LifecycleOwner owner = this;
        final Context context = this.getContext();

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();


        Log.wtf(TAG, "Is het de defoult val?  :   " + sharedPref.getInt(getString(R.string.preferences_player_id), 437854378));

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileViewModel.getAllPlayerData().observe(owner, new Observer<List<PlayerData>>() {
                    @Override
                    public void onChanged(final List<PlayerData> playerData) {
                        profileViewModel.getRepository().getAllFilms().observe(owner, new Observer<List<Film>>() {
                            @Override
                            public void onChanged(List<Film> films) {
                                for (Film film : films) {
                                    profileViewModel.getRepository().filmCollectionDatabaseEditHelper.insert(
                                            new FilmCollection(playerData.size(), playerName.getText().toString(),
                                                    film.getEpisodeId(), 0, 0));
                                }
                                profileViewModel.getRepository().getAllFilms().removeObserver(this);
                                playerName.setText("");
                            }
                        });
                        profileViewModel.insert(new PlayerData(playerData.size(), playerName.getText().toString(), 2));

                        Toast.makeText(context, "Inserted PlayerData: " + playerData.size() + "  " + playerName.getText().toString(), Toast.LENGTH_SHORT).show();
                        playerName.setText("");
                        profileViewModel.getAllPlayerData().removeObserver(this);
                    }
                });
            }
        });

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPlayerActivity.class);
                startActivity(intent);
            }
        });


        final ProfileViewAdapter adapter = new ProfileViewAdapter(getContext());
        recyclerView.setAdapter(adapter);

        profileViewModel.getAllPlayerData().observe(getViewLifecycleOwner(), new Observer<List<PlayerData>>() {
            @Override
            public void onChanged(List<PlayerData> playerData) {
                adapter.setPlayerData(playerData);
            }
        });


        return root;
    }
}