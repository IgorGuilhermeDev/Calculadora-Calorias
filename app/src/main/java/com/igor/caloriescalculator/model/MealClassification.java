package com.igor.caloriescalculator.model;

import com.igor.caloriescalculator.R;

public enum MealClassification {
    BREAKFAST("Café da manhã", R.drawable.breakfast_icon),
    AFTER_BREAKFAST("Desjejum", R.drawable.biscoit_icon),
    LUNCH("Almoço", R.drawable.lunch_icon),
    AFTERNOON_BREAKFAST("Café da tarde", R.drawable.breakfast_icon),
    HAPPY_HOUR("Festa", R.drawable.happy_hour_icon),
    DINNER("Jantar", R.drawable.dinner_icon),
    DESSERT("Sobremesa", R.drawable.dessert_icon),
    OTHER("Outro", R.drawable.pizza_icon);

    private String name;
    private int imageId;

    private MealClassification(String name, int imageId){
        this.name = name;

    }

    public String getName(){
        return this.name;
    }

}
