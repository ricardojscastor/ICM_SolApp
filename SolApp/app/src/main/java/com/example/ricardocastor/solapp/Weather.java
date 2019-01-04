package com.example.ricardocastor.solapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "weather_table", indices = {@Index(value = {"globalIdLocal", "forecastDate"})})
public class Weather {

    @PrimaryKey(autoGenerate = true)
    private static int id;
    private int globalIdLocal;
    private String tMax;
    private String tMin;
    private String predWindDir;
    private String precipitaProb;
    private String forecastDate;
    private String dataUpdate;

    @Ignore
    public Weather(int globalIdLocal, String tMax, String tMin, String predWindDir, String precipitaProb, String forecastDate, String dataUpdate){
        this.globalIdLocal = globalIdLocal;
        this.tMax = tMax;
        this.tMin = tMin;
        this.predWindDir = predWindDir;
        this.precipitaProb = precipitaProb;
        this.forecastDate = forecastDate;
        this.dataUpdate = dataUpdate;
    }

    public Weather (int id, int globalIdLocal, String tMax, String tMin, String predWindDir, String precipitaProb, String forecastDate, String dataUpdate){
        this.id = id;
        this.globalIdLocal = globalIdLocal;
        this.tMax = tMax;
        this.tMin = tMin;
        this.predWindDir = predWindDir;
        this.precipitaProb = precipitaProb;
        this.forecastDate = forecastDate;
        this.dataUpdate = dataUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public String gettMax() {
        return tMax;
    }

    public void settMax(String tMax) {
        this.tMax = tMax;
    }

    public String gettMin() {
        return tMin;
    }

    public void settMin(String tMin) {
        this.tMin = tMin;
    }

    public String getPredWindDir() {
        return predWindDir;
    }

    public void setPredWindDir(String predWindDir) {
        this.predWindDir = predWindDir;
    }

    public String getPrecipitaProb() {
        return precipitaProb;
    }

    public void setPrecipitaProb(String precipitaProb) {
        this.precipitaProb = precipitaProb;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(String dataUpdate) {
        this.dataUpdate = dataUpdate;
    }
}
