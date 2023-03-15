package com.pe.car.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CarSQLite.db";
    public static final String TABLE = "Cars";
    public static final String[] COLUMNS = {"id", "model", "price"};
    private static final int DATABASE_VERSION = 1;
    private static Database instance;

    public static Database initInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
        return instance;
    }

    public static Database getInstance() {
        return instance;
    }

    private static final String CREATE_TABLE = String.format(
            "CREATE TABLE IF NOT EXISTS %s (%s)",
            TABLE,
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, model TEXT NOT NULL, price INTEGER NOT NULL"
    );


    private static final String DROP_TABLE = String.format("DROP TABLE IF EXISTS %s", TABLE);

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
