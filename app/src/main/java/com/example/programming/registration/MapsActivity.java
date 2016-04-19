package com.example.programming.registration;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UserRepo userRepo = new UserRepo(this);
        ArrayList<User> users = userRepo.getUserList();
        Log.i("Login Activity", users.toString());
        for (User u : users) {
            String address = u.address+" "+u.city+" "+u.state+" "+u.zip;

            GeocodingLocation locationAddress = new GeocodingLocation();
            GeocoderHandler temp = new GeocoderHandler();
            temp.setMap(mMap);
            temp.setUsername(u.username);
            locationAddress.getAddressFromLocation(address,
                    getApplicationContext(), temp);
            //while(location==null){}
            //Log.i("Map Activity", location);

        }
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private class GeocoderHandler extends Handler {
        GoogleMap mMap;
        String username;
        public void setMap(GoogleMap googleMap)
        {
            mMap = googleMap;
        }
        public void setUsername(String user)
        {
            username = user;
            Log.i("Geocoder Handler", username);
        }

        @Override
        public void handleMessage(Message message) {

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            try{
                Log.i("Geocoding", "Location"+locationAddress, new Exception());
                location =  message.getData().getString("latLong");
                //startActivity(intent);
                Scanner in = new Scanner(location);
                LatLng sydney = new LatLng(in.nextDouble(),in.nextDouble());
                mMap.addMarker(new MarkerOptions().position(sydney).title(username));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
            catch(Exception e){}

        }
        private String locationAddress;

        public String getLocationAddress() {
            return locationAddress;
        }
    }
}
