package com.example.referendum_chat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoDBApiInterface {
    @GET("geo/cities?countryIds=ke&limit=10&minPopulation=65000")

    Call<GeoDBCitiesSearchResponse> getCities(
            @Query("countryIds") String countryIds,
            @Query("term") String term
    );

}
