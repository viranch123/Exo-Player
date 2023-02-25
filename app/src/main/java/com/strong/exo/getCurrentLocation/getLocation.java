package com.strong.exo.getCurrentLocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.strong.exo.MainActivity;
import com.strong.exo.Map.GPSOn;
import com.strong.exo.R;

import org.jetbrains.annotations.NotNull;

public class getLocation extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        supportMapFragment=SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.newmap,supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);
        fusedLocationProviderClient=new FusedLocationProviderClient(this);
        locationRequest=LocationRequest.create();
        locationRequest=LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000);

        //batch location
        locationRequest.setMaxWaitTime(60*1000);




        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder=new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        locationCallback=new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull @NotNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Toast.makeText(getLocation.this, "CUrrent location"+locationResult.getLastLocation(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLocationAvailability(@NonNull @NotNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };
    }

    @SuppressLint("MissingPermission")
    public void getLocation(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            }else {

                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {

                    @Override
                    public void onSuccess(Location location) {

                        Toast.makeText(getLocation.this, "current location"+location.getLatitude(), Toast.LENGTH_SHORT).show();


                       LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

                        CameraUpdate cameraUpdateFactory=CameraUpdateFactory.newLatLngZoom(latLng,15);
                        googleMap.moveCamera(cameraUpdateFactory);

                        HandlerThread thread = new HandlerThread("chirag");
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,thread.getLooper());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                    }
                });
            }
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;

    }
}