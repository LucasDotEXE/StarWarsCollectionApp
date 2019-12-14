package com.example.starwarscollectablegame.View.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.starwarscollectablegame.R;
import com.example.starwarscollectablegame.View.MainActivity;

public class SettingsFragment extends Fragment {

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

        return root;
    }
}