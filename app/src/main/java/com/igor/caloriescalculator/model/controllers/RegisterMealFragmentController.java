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
import com.igor.caloriescalculator.model.repositories.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;


public class RegisterMealFragmentController {

    private Context context;
    private LayoutInflater inflater;
    private View currentView;
    private MealRepository repository;

    public RegisterMealFragmentController(Context context, View currentView){
        this.context = context;
        this.currentView = currentView;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(repository == null) repository = new MealRepository(this.context);

    }

    public boolean insertMeal(String foodName, String calories, Object mealClassification, String date, Object hour){
        ViewGroup toastContainer = this.currentView.findViewById(R.id.container_toast);
        View toastStyle = this.inflater.inflate(R.layout.custom_toast, toastContainer);

        if(RegisterMealValidations.validFields(this.context, foodName, calories, toastStyle)){
            LocalDateTime localDateTime = formatDate(date, hour.toString());
            MealClassification classification = (MealClassification) mealClassification;
            Meal meal = new Meal(foodName, localDateTime, classification, Double.valueOf(calories));
            boolean isInserted = repository.insertMeal(meal);
            return isInserted;

        }
        return false;

    }

    private LocalDateTime formatDate(String date, String hour){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String formatedHours = hour.substring(0, 2).trim();
        if(formatedHours.length() < 2) formatedHours = "0" + formatedHours;
        LocalTime localTime = LocalTime.parse(formatedHours+":00", DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(localDate, localTime);
    }

    public String getCaloriesFromThisDay(){
        Vector<Meal> meals = repository.selectAllTodayMeals(
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MIN), ZoneId.systemDefault()).toEpochSecond(),
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MAX), ZoneId.systemDefault()).toEpochSecond()
        );
        return String.valueOf(sumCalories(meals));
    }

    private Double sumCalories(Vector<Meal> meals){
        final double[] sum = {0.0};
        meals.forEach(
                (meal)->{
                    sum[0] += meal.getFoodCalories();
                }
        );

        return sum[0];
    }

}
