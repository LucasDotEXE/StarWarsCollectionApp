package com.example.starwarscollectablegame.View.ui.collection.planets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.starwarscollectablegame.R;

public class PlanetsFragment extends Fragment {

    private PlanetsViewModel planetsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        planetsViewModel =
                ViewModelProviders.of(this).get(PlanetsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collection, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        planetsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}