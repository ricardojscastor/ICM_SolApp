package com.example.ricardocastor.solapp;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;

public class IPMAJsonParser {

    private static final String IPMA_DATA = "data";
    private static final String IPMA_TMAX = "tMax";
    private static final String IPMA_TMIN = "tMin";
    private static final String IPMA_LID = "globalIdLocal";
    private static final String IPMA_WNDDIR = "predWindDir";
    private static final String IPMA_PREC = "precipitaProb";
    private static final String IPMA_DATE = "forecastDate";
    private static final String IPMA_UDATE = "dataUpdate";

    private static Weather[] fromJson(final JSONObject forecastJson) throws JSONException {
        JSONArray jsonWeatherArray = forecastJson.getJSONArray(IPMA_DATA);

        Weather[] weatherEntries = new Weather[jsonWeatherArray.length()];

        long normalizedUtcStartDay = System.currentTimeMillis();

        for (int i = 0; i < jsonWeatherArray.length(); i++) {
            JSONObject dayForecast = jsonWeatherArray.getJSONObject(i);
            long dateTimeMillis = normalizedUtcStartDay + TimeUnit.DAYS.toMillis(1) * i;//SunshineDateUtils.DAY_IN_MILLIS * i;
            Weather weather = fromJson(dayForecast, dateTimeMillis);
            weatherEntries[i] = weather;
        }
        return weatherEntries;
    }

    private static Weather fromJson(final JSONObject dayForecast, long dateTimeMillis) throws JSONException {

        String max = dayForecast.getString(IPMA_TMAX);
        String min = dayForecast.getString(IPMA_TMIN);
        String prec = dayForecast.getString(IPMA_PREC);
        String windDirection = dayForecast.getString(IPMA_WNDDIR);
        String dat = dayForecast.getString(IPMA_DATE);
        String udate = dayForecast.getString(IPMA_UDATE);
        int id = dayForecast.getInt(IPMA_LID);

        return new Weather(id, max, min, windDirection, prec, dat, udate);
    }

    @Nullable
    WeatherResponse parse(final String forecastJsonStr) throws JSONException {
        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        Weather[] weatherForecast = fromJson(forecastJson);
        return new WeatherResponse(weatherForecast);
    }

}
