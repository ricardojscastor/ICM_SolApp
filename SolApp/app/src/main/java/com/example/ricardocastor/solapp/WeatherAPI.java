package com.example.ricardocastor.solapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherAPI {

    @GET("/locals/{id}")
    Call<List<Weather>> getWeatherByLocal(@Path("id") int id);

    @GET("/locals/{id}/{data}")
    Call<Weather> getWeatherByLocalDate(@Path("id") int id, @Path("data") String data);

}
