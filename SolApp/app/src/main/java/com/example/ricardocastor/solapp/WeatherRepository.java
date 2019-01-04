package com.example.ricardocastor.solapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class WeatherRepository {

    Application application;

    private static String url = "http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/";

    private static int FRESH_TIMEOUT_IN_MINUTES = 5;

    private final WeatherDao weatherDao;

    public WeatherRepository(Application application) {
        WeatherRoomDatabase db = WeatherRoomDatabase.getDatabase(application);
        weatherDao = db.weatherDao();
    }

    public LiveData<Weather> getWeatherByLocalDate(final int id, final String date) {
        refreshWeather(id, date);
        return weatherDao.getWeatherByLocalDate(id, date);
    }

    private void refreshWeather(final int id, final String date) {
        String url_weather = url + id + ".json";
        RequestQueue queue = Volley.newRequestQueue(application.getApplicationContext());
        boolean weatherExists = (weatherDao.hasWeather(id, date, getMaxRefreshDate(new Date())) != null);
        if (!weatherExists) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_weather, null, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Weather[] weatherArr = IPMAJsonParser.parse(response.toString());
                        for (int i = 0; i < weatherArr.length; i++) {
                            Weather weather = weatherArr[i];
                            weather.setDataUpdate(new Date());
                            insert(weather);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley error", error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }
    }

    private Date getMaxRefreshDate(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }

    private static class insertAsyncTask extends AsyncTask<Weather, Void, Void> {

        private WeatherDao mAsyncTaskDao;

        insertAsyncTask(WeatherDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Weather... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private void insert(Weather weather) {
        new insertAsyncTask(weatherDao).execute(weather);
    }

}