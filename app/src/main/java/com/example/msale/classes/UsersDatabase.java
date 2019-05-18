package com.example.msale.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDatabase {

    myDbHelper myhelper;

    public UsersDatabase(Context context) {
        myhelper = new myDbHelper(context);
    }

    public int editBool(String username , String newBool)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.BOOL, newBool);
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.BOOL+" = ?", new String[] {username});
        return count;
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.USERNAME, myDbHelper.PhoneNUMBER, myDbHelper.MyPASSWORD, myDbHelper.CASH, myDbHelper.BOOL};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String userName = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(myDbHelper.PhoneNUMBER));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            String cash = cursor.getString(cursor.getColumnIndex(myDbHelper.CASH));
            String bool = cursor.getString(cursor.getColumnIndex(myDbHelper.BOOL));
            buffer.append(cid + " " + userName + " " + phoneNumber + " " + password + " " + cash + " " + bool + " " + "\n");
        }
        return buffer.toString();
    }

    public long insertData(String username, String phoneNumber, String password, String cash, String bool) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.PhoneNUMBER, phoneNumber);
        contentValues.put(myDbHelper.MyPASSWORD, password);
        contentValues.put(myDbHelper.CASH,cash);
        contentValues.put(myDbHelper.BOOL,bool);
        long id = db.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase.db";
        private static final String TABLE_NAME = "users";
        private static final int DATABASE_Version = 1;
        private static final String UID = "_id";
        private static final String USERNAME = "Username";
        private static final String PhoneNUMBER = "PhoneNumber";
        private static final String MyPASSWORD = "Password";
        private static final String CASH = "Cash";
        private static final String BOOL = "Bool";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " VARCHAR(255), " + PhoneNUMBER + " VARCHAR(255), " + MyPASSWORD + " VARCHAR(255), " + CASH + " VARCHAR(255), " + BOOL + " VARCHAR(225))";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS " + TABLE_NAME;
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
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,"" + e);
            }
        }
    }
}
