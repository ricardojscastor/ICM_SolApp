package com.example.ricardocastor.solapp;

import android.support.annotation.NonNull;

class WeatherResponse {

    @NonNull
    private final Weather[] mWeatherForecast;

    public WeatherResponse(@NonNull final Weather[] weatherForecast) {
        mWeatherForecast = weatherForecast;
    }

    public Weather[] getWeatherForecast() {
        return mWeatherForecast;
    }

}
