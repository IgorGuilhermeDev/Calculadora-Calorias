package com.igor.caloriescalculator.model.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * @author Igor Guilherme Almeida Rocha
 * Controler do fragment de Registro
 * @see com.igor.caloriescalculator.fragments.RegisterMealFragment
 */
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

    /**
     * Insere uma refeição no banco de dados
     * @param foodName Nome da refeição
     * @param calories Calorias da refeição
     * @param mealClassification tipo da refeição
     * @param date Data da injestão
     * @param hour Hora da ingestão
     * @return true se foi inserido com sucesso, false caso o contrário
     */
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

    /**
     * Formata a data e a hora de String para LocalDateTime
     * @param date data em String
     * @param hour hora em String
     * @return Uma instância de LocalDateTime
     */
    private LocalDateTime formatDate(String date, String hour){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String formatedHours = hour.substring(0, 2).trim();
        if(formatedHours.length() < 2) formatedHours = "0" + formatedHours;
        LocalTime localTime = LocalTime.parse(formatedHours+":00", DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * Pega as calorias consumidas no dia atual
     * @return As calorias consumidas no dia atual em String
     */
    public String getCaloriesFromThisDay(){
        Vector<Meal> meals = repository.selectAllTodayMeals(
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MIN), ZoneId.systemDefault()).toEpochSecond(),
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MAX), ZoneId.systemDefault()).toEpochSecond()
        );
        return String.valueOf(sumCalories(meals));
    }

    /**
     * Faz a soma das calorias
     * @param meals Uma instância de vector, contendo as refeições
     * @return a soma das calorias das refeições contidas em meals
     */
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
