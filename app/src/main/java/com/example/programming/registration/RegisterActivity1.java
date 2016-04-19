package com.example.programming.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.media.*;
import android.graphics.*;
import android.graphics.Bitmap.CompressFormat;
import java.io.*;
import java.util.ArrayList;
import android.util.Log;

public class RegisterActivity1 extends AppCompatActivity {
    EditText usernameET;
    EditText passwordET;
    EditText confirmET;
    EditText phoneET;
    EditText addressET;
    EditText ageET;
    EditText cityET;
    EditText zipET;
    EditText stateET;
    EditText genderET;

    Button registerButton;

    RegisterActivity1 ra1 = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        usernameET = (EditText) findViewById(R.id.editUsernameText);
        passwordET = (EditText) findViewById(R.id.editPasswordText);
        confirmET = (EditText) findViewById(R.id.editConfirmText);
        phoneET = (EditText) findViewById(R.id.editPhoneText);
        addressET = (EditText) findViewById(R.id.editAddressText);
        ageET = (EditText) findViewById(R.id.editAgeText);
        cityET = (EditText) findViewById(R.id.editCityText);
        zipET = (EditText) findViewById(R.id.editZipText);
        stateET = (EditText) findViewById(R.id.editStateText);
        genderET = (EditText) findViewById(R.id.editGenderText);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Register Activity", "Hi");
                UserRepo userRepo = new UserRepo(ra1);
                User user = new User();
                //Log.i("Username", usernameET.getText().toString());
                user.username = usernameET.getText().toString();
                user.password = passwordET.getText().toString();
                user.confirm = confirmET.getText().toString();
                user.phone = phoneET.getText().toString();
                user.address = addressET.getText().toString();
                user.city = cityET.getText().toString();
                user.age = ageET.getText().toString();
                user.zip = zipET.getText().toString();
                user.state = stateET.getText().toString();
                user.gender = genderET.getText().toString();
                user.img = new byte[0];//DbBitmapUtility.getBytes(BitmapFactory.decodeFile("mipmap/ic_launcherbitmap.bmp"));
                //userRepo.clearTable();
                userRepo.insert(user);
                Log.i("Register Activity", "Hi2");
                ArrayList<User> users = userRepo.getUserList();
                Log.i("Register Activity", "Hi3");
                Log.i("Register Activity", users.toString());
                for(User u: users)
                {
                    Log.i("Register Activity", "User username"+user.username);
                    String s =  User.KEY_username + ", " + user.username
                            + User.KEY_password + ", " + user.password
                            + User.KEY_confirm + ", " + user.confirm
                            + User.KEY_phone + ", " + user.phone
                            + User.KEY_address + ", " + user.address
                            + User.KEY_age + ", " + user.age
                            + User.KEY_city + ", " + user.city
                            + User.KEY_zip + ", " + user.zip
                            + User.KEY_state + ", " + user.state
                            + User.KEY_gender + ", " + user.gender
                            + User.KEY_bio + ", " + user.bio;
                    Log.i("Register Activity", s);

                }
                Log.i("Register Activity", "Hi4");

                Intent intent = new Intent(RegisterActivity1.this, LoginActivity.class);
                startActivity(intent);

                //EditText editText = (EditText) findViewById(R.id.addressET);
                //String address = editText.getText().toString();

                //GeocodingLocation locationAddress = new GeocodingLocation();
                //locationAddress.getAddressFromLocation(address,
                //        getApplicationContext(), new GeocoderHandler());
            }
        });
    }
}
