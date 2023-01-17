package com.igor.caloriescalculator.model.controllers;

import android.content.Context;
import android.widget.Toast;

import com.igor.caloriescalculator.model.entities.Meal;
import com.igor.caloriescalculator.model.enums.MealClassification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VisualizationMealsFragmentController {

    private Context context;

    public VisualizationMealsFragmentController(Context context){
        this.context = context;
    }

    public double getSum(List<Meal> meals){
        final double[] sum = {0.0};
        meals.forEach(
                (meal)->{
                    sum[0] += meal.getFoodCalories();
                }
        );

        return sum[0];
    }

    public double mediaCaloriesForDay(double sum, long dateDiff){
        return sum / dateDiff;
    }

    public List<Meal> mediaCaloriesForDayAtMealType(List<Meal> meals, double mediaForDay){

        HashMap<MealClassification, Integer> mealClassificationAndQuantity = new HashMap<>();

        for(MealClassification classification : MealClassification.values()){
            int quantity = 0;
            for(Meal meal : meals){
                if(meal.getMealClassificaion().equals(classification)){
                    quantity ++;
                }
            }
            mealClassificationAndQuantity.put(classification, quantity);
        }
        return calcMediaForMealType(mealClassificationAndQuantity, mediaForDay / meals.size());


    }

    private List<Meal> calcMediaForMealType(HashMap<MealClassification, Integer> mealClassificationAndQuantity,  double mediaForMeal){
        List<Meal> result = new ArrayList<>();

        for(MealClassification classification : mealClassificationAndQuantity.keySet()){
            Meal meal = new Meal();
            meal.setName(classification.getName());
            meal.setMealClassificaion(classification);
            meal.setFoodCalories(mediaForMeal *  mealClassificationAndQuantity.get(classification));
            result.add(meal);
        }
        return  result;
    }
}
