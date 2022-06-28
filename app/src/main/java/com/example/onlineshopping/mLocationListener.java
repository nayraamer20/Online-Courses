package com.example.onlineshopping;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class mLocationListener implements LocationListener {
    private Context activityContext;

    public mLocationListener(Context cont){
        activityContext= cont;
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(activityContext, location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Toast.makeText(activityContext, "GPS Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(activityContext, "GPS Disabled", Toast.LENGTH_SHORT).show();
    }
}

