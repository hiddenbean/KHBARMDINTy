package com.hiddenbean.android.khbarmdinty.interfaces.Auth;

import com.hiddenbean.android.khbarmdinty.resources.UserResource;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserInformationInterface {

    @Headers("Accept: application/json")
    @GET("user")
    Call<UserResource> userInformation(@Header("authorization") String bearerToken);

}
