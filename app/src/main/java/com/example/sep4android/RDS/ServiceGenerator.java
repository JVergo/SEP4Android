package com.example.sep4android.RDS;

/**
 * This is the ServiceGenerator class which sets the base url of the server.
 * Retrofit in this case is used.
 * For the detailed communication with the API, this class needs to call the object UserApi.
 *
 * */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    //set the base Url
    // remember to change the ip address every time server reconnected
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://10.152.210.99:8080/pmi/api/")
            .addConverterFactory(GsonConverterFactory.create());

    //Retrofit built
    private static Retrofit retrofit = retrofitBuilder.build();

    private static UserApi userApi = retrofit.create(UserApi.class);

    public static UserApi getUserApi() {
        return userApi;
    }
}
