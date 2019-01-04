package com.example.ricardocastor.solapp;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    private String city;
    private String data;

    private TextView date;
    private TextView local;
    private TextView temp;
    private TextView wind;

    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        date = (TextView) findViewById(R.id.textView3);
        local = (TextView) findViewById(R.id.textView4);
        temp = (TextView) findViewById(R.id.textView5);
        wind = (TextView) findViewById(R.id.textView6);
        data = getIntent().getStringExtra("data");
        city = getIntent().getStringExtra("city");
        int id = 1151300;

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.init(id, data);

        weatherViewModel.getWeather().observe(this, weather -> {
            local.setText(city);
            date.setText(weather.getForecastDate());
            temp.setText("TMin: " + weather.gettMin() + " - TMax: " + weather.gettMax());
            wind.setText(weather.getPredWindDir());
        });

    }
}
