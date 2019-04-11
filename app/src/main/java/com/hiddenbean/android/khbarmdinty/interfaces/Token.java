package com.hiddenbean.android.khbarmdinty.interfaces;

import com.hiddenbean.android.khbarmdinty.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Token {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("token")
    Call <com.hiddenbean.android.khbarmdinty.models.Token>updateToken(@Field("email") String email, @Field("password") String password);
}
