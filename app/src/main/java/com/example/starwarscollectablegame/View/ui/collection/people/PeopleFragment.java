package com.example.starwarscollectablegame.View.ui.collection.people;

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

public class PeopleFragment extends Fragment {

    private PeopleViewModel peopleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        peopleViewModel =
                ViewModelProviders.of(this).get(PeopleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collection, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        peopleViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}