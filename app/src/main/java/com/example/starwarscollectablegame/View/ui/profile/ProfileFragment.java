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
import androidx.annotation.Nullable;
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
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    public static final int ADD_NOTE_REQUEST = 1;

    private ProfileViewModel profileViewModel;

    private ProfileViewAdapter adapter;

    private static final String TAG = "ProfileFragment";
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_profile, container, false);

        final FloatingActionButton addPlayerButton = root.findViewById(R.id.add_player_button);
        final RecyclerView recyclerView = root.findViewById(R.id.player_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        this.adapter = new ProfileViewAdapter(getContext());
        recyclerView.setAdapter(adapter);

        Log.wtf(TAG, "Is het de defoult val?  :   " + sharedPref.getString(getString(R.string.preferences_player_id), "Not Found"));

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPlayerActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });



        profileViewModel.getAllPlayerData().observe(getViewLifecycleOwner(), new Observer<List<PlayerData>>() {
            @Override
            public void onChanged(List<PlayerData> playerData) {
                adapter.setPlayerData(playerData);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            final String name = Objects.requireNonNull(data).getStringExtra(AddPlayerActivity.EXTRA_NAME);
            int avatarId = data.getIntExtra(AddPlayerActivity.EXTRA_AVATARID, -1);

            PlayerData player = new PlayerData(name, avatarId);
            if (!this.adapter.getPlayerNames().contains(name)) {
                profileViewModel.insert(player, getViewLifecycleOwner());
                profileViewModel.getRepository().getAllFilms().observe(getViewLifecycleOwner(), new Observer<List<Film>>() {
                    @Override
                    public void onChanged(List<Film> films) {
                        for (Film film : films) {
                            Log.wtf("TEST at ProfileViewModel", name + " added " + film.getEpisodeId());
                            profileViewModel.getRepository().filmCollectionDatabaseEditHelper.insert(new FilmCollection(
                                    name, film.getEpisodeId(), 0, 0));
                        }
                        profileViewModel.getRepository().getAllFilms().removeObserver(this);
                    }
                });

                Toast.makeText(getContext(), "Added " + name, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Name Already existed", Toast.LENGTH_SHORT).show();
            }
        } else if (false){

        }

    }
}