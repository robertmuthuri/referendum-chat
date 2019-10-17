package com.example.referendum_chat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoDBApiInterface {
    @GET("geo/cities")
    Call<GeoDBCitiesSearchResponse> getCities(
//            @Query("country") String country,
//            @Query("term") String term
    );

}
