package com.pe.clock.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pe.clock.database.Database;

public class CarContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.pe.clock.Clocks";
    private static final String BASE_PATH = "car";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private Database appDatabase;

    public CarContentProvider() {
        uriMatcher.addURI(AUTHORITY, String.format("%s%s", BASE_PATH, "/*"), 1);
    }

    @Override
    public boolean onCreate() {
        appDatabase = Database.initInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] columns,
            @Nullable String whereColumns,
            @Nullable String[] whereClauses,
            @Nullable String sortOrder
    ) {
        SQLiteDatabase database = appDatabase.getWritableDatabase();
        Cursor cursor = database.query(Database.TABLE, columns, whereColumns, whereClauses, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase database = appDatabase.getWritableDatabase();
        long id = database.insert(Database.TABLE, null, contentValues);
        //long id = database.insertWithOnConflict(AppDatabase.TABLE, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);

        Uri itemUri = ContentUris.withAppendedId(CONTENT_URI, id);
        getContext().getContentResolver().notifyChange(itemUri, null);
        return itemUri;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String whereColumns, @Nullable String[] whereClauses) {
        SQLiteDatabase database = appDatabase.getWritableDatabase();
        return database.update(Database.TABLE, contentValues, whereColumns, whereClauses);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClauses, @Nullable String[] whereArgs) {
        SQLiteDatabase database = appDatabase.getWritableDatabase();
        return database.delete(Database.TABLE, whereClauses, whereArgs);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
