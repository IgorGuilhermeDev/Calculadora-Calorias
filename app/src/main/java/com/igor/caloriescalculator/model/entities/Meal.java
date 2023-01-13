package com.igor.caloriescalculator.model.entities;

import com.igor.caloriescalculator.model.enums.MealClassification;

import java.time.LocalDateTime;
import java.util.Objects;

public class Meal {

    private Integer id;
    private String name;
    private LocalDateTime injestionDate;
    private MealClassification mealClassificaion;
    private Double foodCalories;

    public Meal(){

    }

    public Meal(String name, LocalDateTime injestionDate, MealClassification mealClassificaion, Double foodCalories) {
        this.name = name;
        this.injestionDate = injestionDate;
        this.mealClassificaion = mealClassificaion;
        this.foodCalories = foodCalories;
    }

    public Meal(Integer id,String name, LocalDateTime injestionDate, MealClassification mealClassificaion, Double foodCalories) {
        this.id = id;
        this.name = name;
        this.injestionDate = injestionDate;
        this.mealClassificaion = mealClassificaion;
        this.foodCalories = foodCalories;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getInjestionDate() {
        return injestionDate;
    }

    public MealClassification getMealClassificaion() {
        return mealClassificaion;
    }

    public Double getFoodCalories() {
        return foodCalories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInjestionDate(LocalDateTime injestionDate) {
        this.injestionDate = injestionDate;
    }

    public void setMealClassificaion(MealClassification mealClassificaion) {
        this.mealClassificaion = mealClassificaion;
    }

    public void setFoodCalories(Double foodCalories) {
        this.foodCalories = foodCalories;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", injestionDate=" + injestionDate +
                ", mealClassificaion=" + mealClassificaion +
                ", foodCalories=" + foodCalories +
                '}';
    }
}
