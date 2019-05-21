package com.example.msale.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CommentsDatabaseAdapter {

    ProductsDatabase.myDbHelper helper;

    public CommentsDatabaseAdapter(Context context) {
        helper = new ProductsDatabase.myDbHelper(context);
    }

    public String getData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.USERNAME, myDbHelper.COMMENTS};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String comments = cursor.getString(cursor.getColumnIndex(myDbHelper.COMMENTS));
            buffer.append(cid + " " + username + " " + comments + " " + "\n");
        }
        return buffer.toString();
    }

    public long insertData(String username, String comments) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.COMMENTS, comments);
        long id = db.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "CommentsDatabase.db";
        private static final String TABLE_NAME = "comments";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String USERNAME = "userName";
        private static final String COMMENTS = "comments";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " VARCHAR(255), " + COMMENTS + " VARCHAR(255))";
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
