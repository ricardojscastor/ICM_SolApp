package com.example.ricardocastor.solapp;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        int id = forecastJson.getInt(IPMA_LID);

        for (int i = 0; i < jsonWeatherArray.length(); i++) {
            JSONObject dayForecast = jsonWeatherArray.getJSONObject(i);
            Weather weather = fromJson(dayForecast, id);
            weatherEntries[i] = weather;
        }
        return weatherEntries;
    }

    private static Weather fromJson(final JSONObject dayForecast, int id) throws JSONException {

        String max = dayForecast.getString(IPMA_TMAX);
        String min = dayForecast.getString(IPMA_TMIN);
        String prec = dayForecast.getString(IPMA_PREC);
        String windDirection = dayForecast.getString(IPMA_WNDDIR);
        String dat = dayForecast.getString(IPMA_DATE);
        //String udate = dayForecast.getString(IPMA_UDATE);

        return new Weather(id, max, min, windDirection, prec, dat);
    }

    public static Weather[] parse(final String forecastJsonStr) throws JSONException {
        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        return fromJson(forecastJson);
    }

}
