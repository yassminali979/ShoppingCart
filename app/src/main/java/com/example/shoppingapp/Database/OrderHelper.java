package com.example.shoppingapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderHelper  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "d";

    public OrderHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = "CREATE TABLE " + OrderContract.OrderEntry.TABLE_NAME + " ("
                + OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  OrderContract.OrderEntry.NAME + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.QUANTITY + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.PRICE + " TEXT NOT NULL );";

        db.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
