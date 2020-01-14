package com.example.starwarscollectablegame.View.DetailFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarWarsDatabase;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.People;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PersonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private int level;
    private People people;

    public PersonFragment() {
        // Required empty public constructor
    }


    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null)
        {
            //Todo: Parcelable
            people = (People) getArguments().getSerializable("object");
            level = getArguments().getInt("level");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_person, container, false);
        TextView eyecolor = view.findViewById(R.id.people_fragment_eyecolor);
        TextView birthyear = view.findViewById(R.id.people_fragment_birthyear);
        TextView gender = view.findViewById(R.id.people_fragment_gender);
        TextView haircolor = view.findViewById(R.id.people_fragment_haircolor);
        TextView height = view.findViewById(R.id.people_fragment_height);
        TextView homeworld = view.findViewById(R.id.people_fragment_homeworld);
        TextView mass = view.findViewById(R.id.people_fragment_mass);
        RatingBar ratingBar = view.findViewById(R.id.people_fragment_ratingbar);


        ratingBar.setRating(this.level);
        if (level >= 1){
            gender.setText(this.people.getGender());
            homeworld.setText(people.getHomeWorldUrl());
        }
        if (level >= 2)
        {
            eyecolor.setText(this.people.getEyeColor());
            birthyear.setText(this.people.getBirthYear());
            haircolor.setText(this.people.getHairColor());
        }
        if (level >= 3)
        {
            height.setText(people.getHeight());
            mass.setText(people.getMass());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
        // TODO: Update argument type and name
    }
}
