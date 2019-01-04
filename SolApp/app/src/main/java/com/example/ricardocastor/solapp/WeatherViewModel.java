package com.example.ricardocastor.solapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;

    private LiveData<Weather> weather;

    public WeatherViewModel (Application application){
        super(application);
        this.weatherRepository = new WeatherRepository(application);
    }

    public void init(int globalIdLocal, String date) {
        weather = weatherRepository.getWeatherByLocalDate(globalIdLocal, date);
    }

    public LiveData<Weather> getWeather() {
        return this.weather;
    }


}
