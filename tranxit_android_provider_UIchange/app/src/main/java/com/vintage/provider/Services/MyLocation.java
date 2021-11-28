package com.vintage.provider.Services;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.vintage.provider.R;

import java.util.Timer;
import java.util.TimerTask;

public class MyLocation {

    Timer timer1;
    LocationManager lm;
    LocationResult locationResult;
    boolean gps_enabled = true;
    boolean gpsFixed = true;
    Context myContext;



    public boolean getLocation(Context context, LocationResult result) {

        if (!gpsFixed){
            return false;
        }
        boolean permission = true;
        //I use LocationResult callback class to pass location value from MyLocation to user code.
        locationResult = result;
        myContext = context;
        if (lm == null)
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

//        Toast.makeText(context, gps_enabled+" "+network_enabled,     Toast.LENGTH_LONG).show();

        //don't start listeners if no provider is enabled
        if (!gps_enabled)
            return false;

        if (gps_enabled)

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                permission = false;
            }
        if (permission) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
            timer1 = new Timer();


            timer1.schedule(new GetLastLocation(), 10000);
        }
        //    Toast.makeText(context, " Yaha Tak AAya", Toast.LENGTH_LONG).show();
        return true;
    }

    LocationListener locationListenerGps = new LocationListener() {
        public void onLocationChanged(Location location) {
            if (!gpsFixed){
                locationResult.gotLocation(null);
                return;
            }
            timer1.cancel();
            locationResult.gotLocation(location);
            lm.removeUpdates(this);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };



    class GetLastLocation extends TimerTask {
        @Override

        public void run() {
            if (!gpsFixed){
                locationResult.gotLocation(null);
                return;
            }

            Location gps_loc = null;
            if (gps_enabled)
                if (ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
            gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            //if there are both values use the latest one
            if(gps_loc!=null){
                locationResult.gotLocation(gps_loc);
                return;
            }
            locationResult.gotLocation(null);
        }
    }


    public static abstract class LocationResult{
        public abstract void gotLocation(Location location);
    }
}