package com.example.sep4android.RDS;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestReponsitory {

    private MutableLiveData<String> text;
    private static TestReponsitory reponsitory;

    private TestReponsitory(){
        text = new MutableLiveData<>();
    }
    public static synchronized TestReponsitory getInstance() {
        if (reponsitory == null) {
            reponsitory = new TestReponsitory();
        }
        return reponsitory;
    }


    public LiveData<String> getText(){
        return text;
    }
    public void getTextFromApi()  {
        TestApi testApi = ServiceGenerator.getTestApi();
        Call<TestResponse> call = testApi.test();
        call.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
                if (response.code() == 200) {
                    //user.setValue( response.body().toString());
                    text.setValue(response.body().getTest());
                } else {
                    Log.i("TAT","Error");

                }
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Log.i("TAT","Error");            }
        });
    }
}
