package com.example.starwarscollectablegame.View.ui.collection.vehicles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VehiclesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VehiclesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}