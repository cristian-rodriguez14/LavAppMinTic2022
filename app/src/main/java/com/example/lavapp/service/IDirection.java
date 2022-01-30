package com.example.lavapp.service;

import  com.example.lavapp.model.DirectionMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDirection {
    @GET("maps/api/directions/json")
    Call<DirectionMap> getDirections(@Query("origin") String from,
                                     @Query("destination") String to,
                                     @Query("mode") String mode,
                                     @Query("transit_routing_preference") String preference,
                                     @Query("language") String es,
                                     @Query("key") String key);
}
