package com.example.ricardocastor.solapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

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
    private Date dataUpdate;

    @Ignore
    public Weather(int globalIdLocal, String tMax, String tMin, String predWindDir, String precipitaProb, String forecastDate){
        this.globalIdLocal = globalIdLocal;
        this.tMax = tMax;
        this.tMin = tMin;
        this.predWindDir = predWindDir;
        this.precipitaProb = precipitaProb;
        this.forecastDate = forecastDate;
    }

    public Weather (int id, int globalIdLocal, String tMax, String tMin, String predWindDir, String precipitaProb, String forecastDate){
        this.id = id;
        this.globalIdLocal = globalIdLocal;
        this.tMax = tMax;
        this.tMin = tMin;
        this.predWindDir = predWindDir;
        this.precipitaProb = precipitaProb;
        this.forecastDate = forecastDate;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Weather.id = id;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public String getTMax() {
        return tMax;
    }

    public void setTMax(String tMax) {
        this.tMax = tMax;
    }

    public String getTMin() {
        return tMin;
    }

    public void setTMin(String tMin) {
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

    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }
}
