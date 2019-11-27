package com.example.sep4android.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.R;

public class PlantInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlantInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is plant info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
