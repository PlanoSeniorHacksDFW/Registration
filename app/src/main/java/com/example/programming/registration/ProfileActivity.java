package com.example.programming.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    EditText usernameET;
    EditText ageET;
    EditText genderET;
    EditText addressET;
    EditText phoneET;
    Button findPeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameET = (EditText) findViewById(R.id.editUsernameProfileText);
        ageET = (EditText) findViewById(R.id.editAgeProfileText);
        genderET = (EditText) findViewById(R.id.editGenderProfileText);
        addressET = (EditText) findViewById(R.id.editAddressProfileText);
        phoneET = (EditText) findViewById(R.id.editPhoneProfileText);

        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if(extrasBundle != null) {
            String username = extrasBundle.getString("user");
            UserRepo userRepo = new UserRepo(this);
            ArrayList<User> users = userRepo.getUserList();
            Log.i("Login Activity", users.toString());
            for (User u : users) {
                if(u.username.equals(username))
                {

                    usernameET.setText(u.username, TextView.BufferType.EDITABLE);
                    ageET.setText(u.age, TextView.BufferType.EDITABLE);
                    genderET.setText(u.gender, TextView.BufferType.EDITABLE);
                    addressET.setText(u.address+" "+u.city+" "+u.state+" "+u.zip, TextView.BufferType.EDITABLE);
                    phoneET.setText(u.phone, TextView.BufferType.EDITABLE);

                }

            }
        }

        findPeople = (Button) findViewById(R.id.button1);
        findPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
