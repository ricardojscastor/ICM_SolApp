package com.example.ricardocastor.solapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

@Database(entities = {Weather.class}, version = 2)
@TypeConverters(DateConverter.class)
public abstract class WeatherRoomDatabase extends RoomDatabase {

    private static volatile WeatherRoomDatabase INSTANCE;

    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract WeatherDao weatherDao();

    public static WeatherRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (WeatherRoomDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WeatherRoomDatabase.class, "weather_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
