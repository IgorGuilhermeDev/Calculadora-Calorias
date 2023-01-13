package com.igor.caloriescalculator.db;

public class DbScheme {

    public static final String DB_NAME = "calories_calculator";
    public static final int VERSION = 1;
    public static final String SQL_CREATE_TABLES = Meal.SQL_CREATE;
    public static final String SQL_DROP_TABLES = Meal.SQL_DROP;

    public static class Meal {
        public static String TABLE_NAME = "meal";
        public static String TABLE_COLUMN_ID = "id";
        public static String TABLE_COLUMN_NAME = "name";
        public static String TABLE_COLUMN_DATE = "date";
        public static String TABLE_COLUMN_FOOD_CLASSIFICATION = "classification";
        public static String TABLE_COLUMN_FOOD_VALUE = "food_value";

        public static final String COLUMNS[] = {TABLE_COLUMN_ID,  TABLE_COLUMN_NAME,
                TABLE_COLUMN_DATE, TABLE_COLUMN_FOOD_CLASSIFICATION, TABLE_COLUMN_FOOD_VALUE};

        public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + "(" +
                TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TABLE_COLUMN_NAME + " TEXT," +
                TABLE_COLUMN_DATE + " INTEGER," +
                TABLE_COLUMN_FOOD_CLASSIFICATION + " TEXT," +
                TABLE_COLUMN_FOOD_VALUE + " REAL" +
                ");";

        public static final String SQL_DROP = "DROP IF EXISTS " + TABLE_NAME + ";";

    }
}
