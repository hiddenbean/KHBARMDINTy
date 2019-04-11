package com.hiddenbean.android.khbarmdinty.interfaces.Auth;

import com.hiddenbean.android.khbarmdinty.models.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateTokenInterface {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("token")
    Call<Token> updateToken(@Field("email") String email, @Field("password") String password);

}
