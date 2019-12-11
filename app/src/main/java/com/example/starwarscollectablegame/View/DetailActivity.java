package com.example.starwarscollectablegame.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.starwarscollectablegame.R;
import com.example.starwarscollectablegame.View.DetailFragments.PersonFragment;
import com.example.starwarscollectablegame.ViewModel.PeopleFragmentViewModel;

public class DetailActivity extends AppCompatActivity  implements PersonFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        PeopleFragmentViewModel viewModel = new PeopleFragmentViewModel(getApplication());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment infoFragment = new PersonFragment();
        Bundle bundle = new Bundle();
        String testName ="Luke Skywalker" ;
        bundle.putString("name", testName);
        infoFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment, infoFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction() {

    }
}
