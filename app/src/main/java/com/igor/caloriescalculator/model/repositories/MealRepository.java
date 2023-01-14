package com.igor.caloriescalculator.model.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.igor.caloriescalculator.db.DbScheme;
import com.igor.caloriescalculator.db.ManagerDb;
import com.igor.caloriescalculator.model.entities.Meal;
import com.igor.caloriescalculator.model.enums.MealClassification;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Vector;

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

    public Vector<Meal> selectAllTodayMeals(long initialDate, long finalDate){
        Vector<Meal> result = new Vector<>();
        try(ManagerDb db = new ManagerDb(this.context)){
            String sql = "date >= ? and date <= ?";

            SQLiteDatabase tran = db.getReadableDatabase();

            Cursor tuplas = tran.query(DbScheme.MealScheme.TABLE_NAME,
                    DbScheme.MealScheme.COLUMNS,
                    sql,
                    new String[]{initialDate+"", finalDate+""}, null,
                    null, null);

            while(tuplas.moveToNext()){
                Meal meal = new Meal(
                        tuplas.getInt(0),
                        tuplas.getString(1),
                        Instant.ofEpochMilli(tuplas.getLong(2))
                                .atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        MealClassification.BREAKFAST,
                        25.0
                );

               result.add(meal);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

}

