package com.example.referendum_chat.network;

import com.example.referendum_chat.models.GeoDBCitiesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoDBApiInterface {
//    @GET("geo/cities?countryIds=ke&limit=10&minPopulation=65000&sort=-population")
    @GET("geo/cities?")

    Call<GeoDBCitiesSearchResponse> getCities(
            @Query("countryIds") String countryIds,
            @Query("limit") int limit,
            @Query("minPopulation") int minPopulation,
            @Query("sort") String population
    );

}
