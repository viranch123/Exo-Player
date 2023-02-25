package com.strong.exo.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.strong.exo.R;

public class AnimateCamera extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment supportMapFragment;
    Button button, second,ongps;
    GoogleMap googleMap;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate_camera);
        button = findViewById(R.id.animatecamerabtn);
        second = findViewById(R.id.secondcamerabtn);
        ongps = findViewById(R.id.ongps);


        ongps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnimateCamera.this, ""+isGPSEnabled(), Toast.LENGTH_SHORT).show();
            }
        });



        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        } else {
        }


        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentmapforanimate, supportMapFragment).commit();


        supportMapFragment.getMapAsync(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleMap != null)
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(3.0f));

                LatLng latLng = new LatLng(23.8417, 73.7147);

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);

                MarkerOptions markerOptions = new MarkerOptions().title("Dungarpur").position(latLng);
                googleMap.addMarker(markerOptions);
                googleMap.animateCamera(cameraUpdate, 5000, new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleMap != null)
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(3.0f));

                LatLng latLng = new LatLng(23.5461, 74.4350);

                MarkerOptions markerOptions = new MarkerOptions().title("Dungarpur").position(latLng);


                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                googleMap.addMarker(markerOptions);
                googleMap.animateCamera(cameraUpdate, 5000, new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        LatLng latLng = new LatLng(23.8417, 73.7147);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);


    }


    public void getcurrentLocation(View view) {
        isGPSEnabled();
    }


    public boolean isGPSEnabled() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean providerEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (providerEnabled) {
            return true;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("GPS is require for this app");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(intent, 101);
                }
            });
            builder.show();


        }

        return false;

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (requestCode==101)
        {
            LocationManager locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
            boolean provider=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


            if (provider)
            {
                Toast.makeText(this, "Gps is on ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "GPS is off", Toast.LENGTH_SHORT).show();
            }

        }


        super.startActivityForResult(intent, requestCode);
    }
}