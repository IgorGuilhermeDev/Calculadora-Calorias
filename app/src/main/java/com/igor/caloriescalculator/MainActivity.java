package com.igor.caloriescalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCaloriesLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUiComponents();
    }

    private void initializeUiComponents(){
        tvCaloriesLimit = findViewById(R.id.tv_calories_limit);
    }
}