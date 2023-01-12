package com.igor.caloriescalculator.model;

public enum MealClassification {
    BREAKFAST("Café da manhã"),
    AFTER_BREAKFAST("Desjejum"),
    LUNCH("Almoço"),
    AFTERNOON_BREAKFAST("Café da tarde"),
    HAPPY_HOUR("Festa"),
    DINNER("Jantar"),
    DESSERT("Sobremesa"),
    OTHER("Outro");

    private String name;

    private MealClassification(String name){
        this.name = name;

    }

    public String getName(){
        return this.name;
    }

}
