package com.example.programming.registration;

import android.content.Context;
import android.database.sqlite.*;
import android.util.Log;
public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "Accompany.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db = SQLiteDatabase.openOrCreateDatabase(DATABASE_NAME,null);
        String DROP_TABLE = "DROP TABLE IF EXISTS "+User.TABLE;
        String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + User.TABLE + "("
                            + User.KEY_username + ", "
                            + User.KEY_password + ", "
                            + User.KEY_confirm + ", "
                            + User.KEY_phone + ", "
                            + User.KEY_address + ", "
                            + User.KEY_age + ", "
                            + User.KEY_city + ", "
                            + User.KEY_zip + ", "
                            + User.KEY_state + ", "
                            + User.KEY_gender + ", "
                            + User.KEY_bio + ", "
                            + User.KEY_img + ") ";
        System.out.println("Yay");
        Log.i("DBHelper", "yay");
        db.execSQL(CREATE_TABLE_USERS);
        db.setTransactionSuccessful();
        System.out.println("Plz");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);

        // Create tables again
        onCreate(db);

    }
}