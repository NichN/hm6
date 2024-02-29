package com.example.citylist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class CityDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        TextView cityTextview =(TextView) findViewById(R.id.cityTextView);
        TextView locationTextView=(TextView) findViewById(R.id.locationTextView);

        Intent intent = getIntent();
        String selectedCity = intent.getStringExtra("selected_city");
        Location selectedLocation =(Location) intent.getSerializableExtra("selected_location");
        if(selectedCity != null && selectedLocation != null){
            cityTextview.setText(selectedCity);
            locationTextView.setText(selectedLocation.toString());
        }
    }
}