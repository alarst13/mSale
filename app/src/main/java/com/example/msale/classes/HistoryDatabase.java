package com.example.msale.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabase {
    ProductsDatabase.myDbHelper helper;

    public HistoryDatabase (Context context) {
        helper = new ProductsDatabase.myDbHelper(context);
    }

    public String getData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.USERNAME , myDbHelper.UnitPRICE, myDbHelper.NumberOfPRODUCTS, myDbHelper.WholePRICE, myDbHelper.TIME, myDbHelper.DATE};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String unitPrice = cursor.getString(cursor.getColumnIndex(myDbHelper.UnitPRICE));
            String numberOfProducts = cursor.getString(cursor.getColumnIndex(myDbHelper.NumberOfPRODUCTS));
            String wholePrice   = cursor.getString(cursor.getColumnIndex(myDbHelper.WholePRICE));
            String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
            String date = cursor.getString(cursor.getColumnIndex(myDbHelper.DATE));
            buffer.append(cid + " " + username + " " + unitPrice + " " + numberOfProducts + " " + wholePrice + " " + time + " " + date + " " + "\n");
        }
        return buffer.toString();
    }

    public long insertData(String username, String unitPrice, String numberOfProducts, String wholePrice, String time, String date) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.UnitPRICE, unitPrice);
        contentValues.put(myDbHelper.NumberOfPRODUCTS, numberOfProducts);
        contentValues.put(myDbHelper.WholePRICE, wholePrice);
        contentValues.put(myDbHelper.TIME, time);
        contentValues.put(myDbHelper.DATE, date);
        long id = db.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase.db";
        private static final String TABLE_NAME = "history";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String USERNAME = "userName";
        private static final String UnitPRICE = "unitPrice";
        private static final String NumberOfPRODUCTS = "numberOfProducts";
        private static final String WholePRICE = "wholePrice";
        private static final String TIME = "time";
        private static final String DATE = "date";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " VARCHAR(255), " + UnitPRICE + " VARCHAR(255), " + NumberOfPRODUCTS + " VARCHAR(255), " + WholePRICE + " VARCHAR(255), " + TIME + " VARCHAR(255), " + DATE + " VARCHAR(255))";
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
