package com.hiddenbean.android.khbarmdinty.interfaces.Auth;

import com.hiddenbean.android.khbarmdinty.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginUserInterface {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("login")
    Call<User> loginUser(@Field("email") String email, @Field("password") String password);

}
