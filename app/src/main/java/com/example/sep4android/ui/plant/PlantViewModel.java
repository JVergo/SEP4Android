package com.example.sep4android.ui.plant;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.R;

public class PlantViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlantViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is plant fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}