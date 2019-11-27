package com.example.sep4android.RDS;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {

    @GET("users/{userID}")
    Call<UserResponse> userInfo(@Path("userID") String text);

}
