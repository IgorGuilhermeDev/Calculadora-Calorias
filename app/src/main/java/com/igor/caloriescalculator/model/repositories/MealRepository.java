package com.igor.caloriescalculator.model.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.igor.caloriescalculator.db.DbScheme;
import com.igor.caloriescalculator.db.ManagerDb;
import com.igor.caloriescalculator.model.entities.Meal;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MealRepository {

    private Context context;
    public MealRepository(Context context){
        this.context = context;
    }


    public Boolean insertMeal(Meal meal){
        try(ManagerDb connection = new ManagerDb(context)){
            SQLiteDatabase database = connection.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DbScheme.MealScheme.TABLE_COLUMN_NAME, meal.getName());
            values.put(DbScheme.MealScheme.TABLE_COLUMN_DATE, ZonedDateTime.of(meal.getInjestionDate(), ZoneId.systemDefault()).toEpochSecond());
            values.put(DbScheme.MealScheme.TABLE_COLUMN_FOOD_CLASSIFICATION, meal.getMealClassificaion().getName());
            values.put(DbScheme.MealScheme.TABLE_COLUMN_FOOD_VALUE, meal.getFoodCalories());

            long id = database.insert(DbScheme.MealScheme.TABLE_NAME, null, values);

            if(id > 0) return true;
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
