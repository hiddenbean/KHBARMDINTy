package com.hiddenbean.android.khbarmdinty.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceProvider {

    private static final String BASE_URL = "http://192.168.1.9/api/";

    public static Gson gson = new GsonBuilder()
                                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .create();

    public static final Retrofit RETROFIT = new Retrofit.Builder()
                                                    .baseUrl(BASE_URL)
                                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                                    .build();
}
