package com.daniel.matters.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dabraham on 1/13/16.
 */
public class MatterDbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "matters_db";

    public MatterDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: upgardes
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL(MattersTable.createTable());
        db.execSQL(ClientTable.createTable());
    }
}
