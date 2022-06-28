package com.example.onlineshopping;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    Button getMyLoc, SubmitBtn;
    EditText CurrentLocation;
    LocationManager locManager;
    mLocationListener locListener;
    String address= "";

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        getMyLoc = findViewById(R.id.get_location);
        SubmitBtn = findViewById(R.id.submit);
        CurrentLocation = findViewById(R.id.current_location);
        locListener = new mLocationListener(getApplicationContext());
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, locListener);
        }catch (SecurityException x){
            Toast.makeText(this, "You are not allowed to access the current location", Toast.LENGTH_SHORT).show();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960,31.235711600),8));
        final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
        getMyLoc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                mMap.clear();
                Geocoder coder = new Geocoder(getApplicationContext());
                List<Address> addressList;
                Location loc = null ;
                try{
                    loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                catch (SecurityException x){
                    Toast.makeText(MapsActivity.this, "You did not allow to access the current location", Toast.LENGTH_SHORT).show();
                }
                if(loc!=null){
                    LatLng myPos =new LatLng(loc.getLatitude(),loc.getLongitude());
                    try{
                        addressList = coder.getFromLocation(myPos.latitude,myPos.longitude,1);
                        if(!addressList.isEmpty()){
                            String address = "";
                            for(int i =0;i<=addressList.get(0).getMaxAddressLineIndex();i++)
                                address+=addressList.get(0).getAddressLine(i)+", ";
                            mMap.addMarker(new MarkerOptions().position(myPos).title("My Location").snippet(address)).setDraggable(true);
                            CurrentLocation.setText(address);
                        }
                    } catch (IOException e) {
                        // mMap.addMarker(new MarkerOptions().position(myPos).title("my location"));
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos,15));
                }
                else{
                    Toast.makeText(MapsActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                }
            }

        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                Geocoder coder = new Geocoder(getApplicationContext());
                List<Address> addressList ;
                try{
                    addressList= coder.getFromLocation(marker.getPosition().latitude,marker.getPosition().longitude,1);
                    if(!addressList.isEmpty()){
                        String address = "";
                        for(int i=0;i<addressList.get(0).getMaxAddressLineIndex();i++)
                            address+=addressList.get(0).getAddressLine(i)+", ";
                        CurrentLocation.setText(address);
                    }
                    else{
                        Toast.makeText(MapsActivity.this, "No Address For This Location", Toast.LENGTH_SHORT).show();
                        CurrentLocation.getText().clear();
                    }
                }
                catch (IOException x){
                    Toast.makeText(MapsActivity.this, "Can't Get The Address, Check Your Network", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ps= new Intent(getApplicationContext(), ShowCart.class);
                ps.putExtra("location",CurrentLocation.getText().toString());
                setResult(1,ps);
                finish();
            }
        });
    }
}