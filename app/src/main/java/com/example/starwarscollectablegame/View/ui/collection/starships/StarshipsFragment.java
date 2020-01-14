package com.example.starwarscollectablegame.View.ui.collection.starships;

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

public class StarshipsFragment extends Fragment {

    private StarshipsViewModel StarshipsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StarshipsViewModel =
                ViewModelProviders.of(this).get(StarshipsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collection, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        textView.setText(R.string.wip_fragment);
        return root;
    }
}