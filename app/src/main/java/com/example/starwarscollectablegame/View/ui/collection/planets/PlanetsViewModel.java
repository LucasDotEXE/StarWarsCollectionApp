package com.example.starwarscollectablegame.View.ui.collection.planets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlanetsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlanetsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}