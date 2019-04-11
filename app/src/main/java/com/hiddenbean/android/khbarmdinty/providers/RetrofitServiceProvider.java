package com.hiddenbean.android.khbarmdinty.providers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceProvider {

    private static final String BASE_URL = "http://10.0.2.2/api/";

    public static final Retrofit RETROFIT = new Retrofit.Builder()
                                                    .baseUrl(BASE_URL)
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .build();
}
