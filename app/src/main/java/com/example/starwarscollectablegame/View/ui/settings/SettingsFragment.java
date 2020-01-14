package com.example.starwarscollectablegame.View.ui.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.starwarscollectablegame.Controller.StarWarsAPI.SwapiEntryPageListener;
import com.example.starwarscollectablegame.Model.StarWarsDataRepository;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Planet;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Species;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Starship;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.Vehicle;
import com.example.starwarscollectablegame.R;
import com.example.starwarscollectablegame.View.MainActivity;

import java.util.ArrayList;

public class SettingsFragment extends Fragment implements SwapiEntryPageListener {

    private static final String TAG = "SettingsFragment";

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_settings, container, false);

        SharedPreferences sharedPref = root.getContext().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();




        final Switch swich = root.findViewById(R.id.theme_switch);
        swich.setChecked(sharedPref.getBoolean(getString(R.string.preferences_theme_use_sith), false));

        if (swich.isChecked()) {
            swich.setText(getString(R.string.settings_switch_on));
        } else {
            swich.setText(getString(R.string.settings_switch_off));
        }

        swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(getString(R.string.preferences_theme_use_sith),
                        isChecked).apply();
                editor.putBoolean(getString(R.string.preferences_theme_open_settings),
                        true).apply();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        final Button refreshDatabase = root.findViewById(R.id.dataBaseRefresh);
        final SwapiEntryPageListener listener = this;
        final Context context = getContext();
        refreshDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle(getString(R.string.settings_popup_title));
                builder.setMessage(getString(R.string.settings_popup_message));
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                settingsViewModel.getRepository().clearDatabase();
                                StarWarsDataRepository.fillDatabase(listener, context);
                            }
                        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();




            }
        });

        return root;
    }

    @Override
    public void onSwapiEntryPageListener(ArrayList<SwapiEntry> entries, StarWarsDataType type, int nextPage) {
        switch (type) {
            case STARSHIP: {
                for (SwapiEntry entry : entries) {
                    Starship starship = (Starship) entry;
                    settingsViewModel.getRepository().starshipDatabaseEditor.insert(starship);
                }
                break;
            }
            case VIHICLE: {
                for (SwapiEntry entry : entries) {
                    Vehicle vehicle = (Vehicle) entry;
                    settingsViewModel.getRepository().vehicleDatabaseEditor.insert(vehicle);
                }
                break;
            }
            case SPECIES: {
                for (SwapiEntry entry : entries) {
                    Species species = (Species) entry;
                    settingsViewModel.getRepository().speciesDatabaseEditor.insert(species);
                }
                break;
            }
            case PLANET: {
                for (SwapiEntry entry : entries) {
                    Planet planet = (Planet) entry;
                    settingsViewModel.getRepository().planetDatabaseEditor.insert(planet);
                }
                break;
            }
            case PEOPLE: {
                for (SwapiEntry entry : entries) {
                    People people = (People) entry;
                    settingsViewModel.getRepository().peopleDatabaseEditor.insert(people);
                }
                break;
            }
            case FILM: {
                for (SwapiEntry entry : entries) {
                    Film film = (Film) entry;

                    settingsViewModel.getRepository().filmDatabaseEditor.insert(film);
                }
                break;
            }
        }
        if (nextPage != -1) {
            StarWarsDataRepository.getApiManager().getSwapiEntryPage(this, nextPage, type);
        } else {
            Log.i(TAG, "Finnished List recieving");
        }
        Log.i(TAG, "Recieved Swapi data Page: " + nextPage + " data type: " + type);
    }

    @Override
    public void onSwapiEntryPageError() {

    }
}