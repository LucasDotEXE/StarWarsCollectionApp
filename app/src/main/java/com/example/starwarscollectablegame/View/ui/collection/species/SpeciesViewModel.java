package com.example.starwarscollectablegame.View.ui.collection.species;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpeciesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpeciesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}