package com.vintage.provider.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.vintage.provider.Services.MyLocation;

public class LocationFinder extends Activity {

    int increment = 4;
    MyLocation myLocation = new MyLocation();

    // private ProgressDialog dialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myLocation.getLocation(getApplicationContext(), locationResult);
        boolean r = myLocation.getLocation(getApplicationContext(), locationResult);
        finish();
    }

    public MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {

        @Override
        public void gotLocation(Location location) {
            // TODO Auto-generated method stub
            double Longitude = location.getLongitude();
            double Latitude = location.getLatitude();
            double Accuracy = location.getAccuracy();
            double Speed = location.getSpeed();

            Toast.makeText(getApplicationContext(), "Got Location", Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(), "Latitude: "+ Latitude, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Longitude: "+ Longitude, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Accuracy: "+ Accuracy, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Speed: "+ Speed, Toast.LENGTH_SHORT).show();
            System.out.println("SHARE PREFERENCE ME PUT KAR DIYA.");
        }
    };

    // handler for the background updating

}