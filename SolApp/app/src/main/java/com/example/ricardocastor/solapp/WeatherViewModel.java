package com.example.ricardocastor.solapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;

    private LiveData<List<Weather>> weather;

    public WeatherViewModel (Application application){
        super(application);

    }

}
