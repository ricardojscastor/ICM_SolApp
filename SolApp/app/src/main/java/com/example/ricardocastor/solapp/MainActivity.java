package com.example.ricardocastor.solapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button check;
    private EditText city;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check = (Button) findViewById(R.id.button);
        city = (EditText) findViewById(R.id.editText);
        date = (EditText) findViewById(R.id.editText2);
    }

    public void weather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        String d = date.getText().toString();
        String c = city.getText().toString();
        intent.putExtra("city", c);
        intent.putExtra("data", d);
        startActivity(intent);
    }
}
