package com.igor.caloriescalculator.model.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.model.entities.Meal;
import com.igor.caloriescalculator.model.enums.MealClassification;
import com.igor.caloriescalculator.model.errors.RegisterMealValidations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.Inflater;

public class RegisterMealFragmentController {

    private Context context;
    private LayoutInflater inflater;
    private View currentView;

    public RegisterMealFragmentController(Context context, View currentView){
        this.context = context;
        this.currentView = currentView;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void insertMeal(String foodName, String calories, Object mealClassification, String date, Object hour){
        ViewGroup toastContainer = this.currentView.findViewById(R.id.container_toast);
        View toastStyle = this.inflater.inflate(R.layout.custom_toast, toastContainer);

        if(RegisterMealValidations.validFields(this.context, foodName, calories, toastStyle)){
            LocalDateTime localDateTime = formatDate(date, hour.toString());
            MealClassification classification = (MealClassification) mealClassification;
            Meal meal = new Meal(foodName, localDateTime, classification, Double.valueOf(calories));
            Toast.makeText(this.context, meal.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private LocalDateTime formatDate(String date, String hour){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String formatedHours = hour.substring(0, 2).trim();
        if(formatedHours.length() < 2) formatedHours = "0" + formatedHours;
        LocalTime localTime = LocalTime.parse(formatedHours+":00", DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(localDate, localTime);
    }

}
