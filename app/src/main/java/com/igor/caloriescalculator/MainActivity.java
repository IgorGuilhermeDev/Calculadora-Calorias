package com.igor.caloriescalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.igor.caloriescalculator.fragments.RegisterMealFragment;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RegisterMealFragment registerMealFragment = new RegisterMealFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, registerMealFragment).commit();
    }

}