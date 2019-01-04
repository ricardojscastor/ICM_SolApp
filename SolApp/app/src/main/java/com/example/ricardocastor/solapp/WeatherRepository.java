package com.example.ricardocastor.solapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 5;

    private final WeatherAPI weatherAPI;
    private final WeatherDao weatherDao;
    private final Executor executor;

    @Inject
    public WeatherRepository(WeatherAPI weatherAPI, WeatherDao weatherDao, Executor executor){
        this.weatherAPI = weatherAPI;
        this.weatherDao = weatherDao;
        this.executor = executor;
    }

    public LiveData<List<Weather>> getWeatherByLocal(int id){
        refreshWeather(id);
        return weatherDao.getWeatherByLocal(id);
    }

    private void refreshWeather(final int id) {
        executor.execute(() -> {
            boolean weatherExists = (weatherDao.hasWeather(id, getMaxRefreshDate(new Date())) != null);
            if (!weatherExists) {
                weatherAPI.getWeatherByLocal(id).enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, final Response<Weather> response) {
                        Toast.makeText(App.context, "Data refreshed from network!", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            Weather weather = response.body();
                            weather.setDataUpdate(new Date().toString());
                            weatherDao.insert(weather);
                        });
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                    }
                });
            }
        });
    }

    private String getMaxRefreshDate (Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime().toString();
    }

}
