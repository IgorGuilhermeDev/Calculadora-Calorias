package com.igor.caloriescalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.igor.caloriescalculator.adapters.HourSpinnerAdapter;
import com.igor.caloriescalculator.adapters.MealSpinnerAdapter;
import com.igor.caloriescalculator.data_mock.ListHours;
import com.igor.caloriescalculator.fragments.RegisterMealFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializeUiComponents();
        //setEvents();
        RegisterMealFragment registerMealFragment = new RegisterMealFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, registerMealFragment).commit();
    }

}