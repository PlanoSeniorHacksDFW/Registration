package com.example.programming.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(User user) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(Student.KEY_age, student.age);
        //values.put(Student.KEY_email,student.email);
        //values.put(Student.KEY_name, student.name);

        values.put(User.KEY_username, user.username);
        values.put(User.KEY_password, user.password);
        values.put(User.KEY_confirm, user.confirm);
        values.put(User.KEY_phone, user.phone);
        values.put(User.KEY_address, user.address);
        values.put(User.KEY_age, user.age);
        values.put(User.KEY_city, user.city);
        values.put(User.KEY_zip, user.zip);
        values.put(User.KEY_state, user.state);
        values.put(User.KEY_gender, user.gender);
        values.put(User.KEY_bio, user.bio);
        values.put(User.KEY_img, user.img);

        // Inserting Row
        long user_Id = db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
        //return (int) student_Id;
        return 0;
    }
    public void clearTable()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(User.TABLE, null, null);
    }
    public ArrayList<User>  getUserList() {
        //Open connection to read only
        Log.i("User Repo", "Hi");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + User.TABLE;
        Log.i("User Repo", "Hi2");
        //Student student = new Student();
        ArrayList<User> userList = new ArrayList<User>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        Log.i("User Repo", "Hi3");
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.username = cursor.getString(cursor.getColumnIndex(User.KEY_username));
                user.password = cursor.getString(cursor.getColumnIndex(User.KEY_password));
                user.confirm = cursor.getString(cursor.getColumnIndex(User.KEY_confirm));
                user.phone = cursor.getString(cursor.getColumnIndex(User.KEY_phone));
                user.address = cursor.getString(cursor.getColumnIndex(User.KEY_address));
                user.city = cursor.getString(cursor.getColumnIndex(User.KEY_city));
                user.zip = cursor.getString(cursor.getColumnIndex(User.KEY_zip));
                user.state = cursor.getString(cursor.getColumnIndex(User.KEY_state));
                user.gender = cursor.getString(cursor.getColumnIndex(User.KEY_gender));
                user.bio = cursor.getString(cursor.getColumnIndex(User.KEY_bio));
                user.age = cursor.getString(cursor.getColumnIndex(User.KEY_age));
                user.img = null;
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;

    }

}