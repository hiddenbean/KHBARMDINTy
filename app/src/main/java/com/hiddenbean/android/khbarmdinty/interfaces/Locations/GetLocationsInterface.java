package com.hiddenbean.android.khbarmdinty.interfaces.Locations;

import com.hiddenbean.android.khbarmdinty.resources.LocationsResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GetLocationsInterface {
    @Headers("Accept: application/json")
    @GET("locations")
    Call<LocationsResource> getLocations(@Header("authorization") String bearerToken);
}
