package com.example.ricardocastor.solapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather weather);

    @Query("SELECT * FROM weather_table WHERE globalIdLocal = :localId")
    LiveData<List<Weather>> getWeatherByLocal(int localId);

    @Query("SELECT * FROM weather_table WHERE globalIdLocal = :localId AND forecastDate = :data")
    LiveData<Weather> getWeatherByLocalDate(int localId, String data);

    @Query("SELECT * FROM weather_table WHERE globalIdLocal = :localId AND forecastDate = :data AND dataUpdate > :lastUpdate LIMIT 1")
    Weather hasWeather(int localId, String data, Date lastUpdate);

}
