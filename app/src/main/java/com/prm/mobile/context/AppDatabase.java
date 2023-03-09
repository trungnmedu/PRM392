package com.prm.mobile.context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "app.db";
    public static final String TABLE = "student";
    private static final int DATABASE_VERSION = 1;
    private static AppDatabase instance;

    public static AppDatabase initInstance(Context context){
        if(instance == null){
            instance = new AppDatabase(context);
        }
        return instance;
    }

    public  static AppDatabase getInstance(){
        return instance;
    }

    //"id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score REAL"
    private static final String CREATE_TABLE = String.format(
            "CREATE TABLE IF NOT EXISTS %s (%s)",
            TABLE,
            "id TEXT PRIMARY KEY, name TEXT, score REAL"
    );


    private static final String DROP_TABLE = String.format("DROP TABLE IF EXISTS %s", TABLE);

    public AppDatabase(Context context) {
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