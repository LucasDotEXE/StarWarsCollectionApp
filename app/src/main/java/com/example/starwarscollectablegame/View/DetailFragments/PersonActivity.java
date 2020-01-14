package com.example.starwarscollectablegame.View.DetailFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Person;
import android.os.Bundle;

import com.example.starwarscollectablegame.Model.Database.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.R;

public class PersonActivity extends AppCompatActivity implements PersonFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        fragmentManager = getSupportFragmentManager();
        Bundle data = new Bundle();
        data.putSerializable("object", getIntent().getExtras().getSerializable("object"));
        data.putInt("level", getIntent().getExtras().getInt("level"));
        Fragment newFragment = new PersonFragment();
        newFragment.setArguments(data);
        fragmentManager.beginTransaction().replace(R.id.person_activity_frame, newFragment).commit();
    }

    @Override
    public void onFragmentInteraction() {

    }
}
