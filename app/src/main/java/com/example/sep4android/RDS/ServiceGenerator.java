package com.example.sep4android.RDS;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            //http://localhost:8080/pmi/api/users/naya7777@gmail.com
            .baseUrl("http://10.152.218.9:8080/pmi/api/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static UserApi userApi = retrofit.create(UserApi.class);

    private static TestApi testApi = retrofit.create(TestApi.class);

    public static UserApi getUserApi() {
        return userApi;
    }

    public static TestApi getTestApi() {
        return testApi;
    }

}
