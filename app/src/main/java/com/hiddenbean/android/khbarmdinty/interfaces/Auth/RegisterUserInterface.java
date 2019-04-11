package com.hiddenbean.android.khbarmdinty.interfaces.Auth;

import com.hiddenbean.android.khbarmdinty.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterUserInterface {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("register")
    Call<User> registerUser(@Field("first_name") String first_name, @Field("last_name") String last_name, @Field("email") String email, @Field("password") String password);

}
