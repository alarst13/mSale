package com.example.msale.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductsDatabase {
    myDbHelper helper;

    public ProductsDatabase(Context context) {
        helper = new myDbHelper(context);
    }

    public String getData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.ProductType, myDbHelper.NAME , myDbHelper.FACTORY, myDbHelper.PRICE, myDbHelper.OFF, myDbHelper.DESCRIPTION, myDbHelper.NUMBER, myDbHelper.PIC_ID};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String productType = cursor.getString(cursor.getColumnIndex(myDbHelper.ProductType));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String factory = cursor.getString(cursor.getColumnIndex(myDbHelper.FACTORY));
            String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
            String off = cursor.getString(cursor.getColumnIndex(myDbHelper.OFF));
            String description = cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            String number = cursor.getString(cursor.getColumnIndex(myDbHelper.NUMBER));
            String image = cursor.getString(cursor.getColumnIndex(myDbHelper.PIC_ID));
            buffer.append(cid + " " + productType + " " + name + " " + factory + " " + price + " " + off + " " + description + " " + number + " " + image + " " + "\n");
        }
        return buffer.toString();
    }

    public long insertData(String productType, String name, String factory, String price, String off, String description, String number, String picID) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.ProductType, productType);
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.FACTORY, factory);
        contentValues.put(myDbHelper.PRICE, price);
        contentValues.put(myDbHelper.OFF,off);
        contentValues.put(myDbHelper.DESCRIPTION,description);
        contentValues.put(myDbHelper.NUMBER,number);
        contentValues.put(myDbHelper.PIC_ID, picID);
        long id = db.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "productsDatabase.db";
        private static final String TABLE_NAME = "products";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String ProductType = "ProductType";
        private static final String NAME = "name";
        private static final String FACTORY = "factory";
        private static final String PRICE = "price";
        private static final String OFF = "off";
        private static final String DESCRIPTION = "description";
        private static final String NUMBER = "number";
        public static final String PIC_ID = "picID";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ProductType + " VARCHAR(255), " + NAME + " VARCHAR(255), " + FACTORY + " VARCHAR(255), " + PRICE + " VARCHAR(255), " + OFF + " VARCHAR(255), " + DESCRIPTION + " VARCHAR(255), " + NUMBER + " VARCHAR(255), " + PIC_ID + " VARCHAR(255))";
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
