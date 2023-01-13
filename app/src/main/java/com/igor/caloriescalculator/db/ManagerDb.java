package com.igor.caloriescalculator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManagerDb extends SQLiteOpenHelper {

    public ManagerDb(Context context){
        super(context, DbScheme.DB_NAME, null, DbScheme.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbScheme.SQL_CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbScheme.SQL_DROP_TABLES);
        onCreate(db);

    }
}
