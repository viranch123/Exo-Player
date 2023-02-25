package com.strong.exo.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.strong.exo.R;

public class getShreadUpdate extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, OnMapReadyCallback {
    FusedLocationProviderClient fusedLocationProviderClient;
    Button submit;
    EditText edt;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    int second=1;


    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_shread_update);
        fusedLocationProviderClient = new FusedLocationProviderClient(this);

        submit = findViewById(R.id.submit);
        edt = findViewById(R.id.edt);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                PreferenceManager.getDefaultSharedPreferences(getShreadUpdate.this)
                        .edit().putString("currentlat",""+locationResult.getLastLocation().getLatitude())
                        .putString("currentlong",""+locationResult.getLastLocation().getLongitude())
                        .apply();

                Toast.makeText(getShreadUpdate.this, "Current Update"+locationResult.getLastLocation().getLongitude(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationRequest = LocationRequest.create();
                locationRequest.setFastestInterval(5000);
                locationRequest.setInterval(1000);
                locationRequest.setMaxWaitTime(60*1000);




                if (ActivityCompat.checkSelfPermission(getShreadUpdate.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getShreadUpdate.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,null);



            }
        });




        SupportMapFragment supportMapFragment=SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.mapsfrg,supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);


    }

    @Override
    protected void onStart() {
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        super.onStart();
    }

    @Override
    protected void onPause() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


        String lat =sharedPreferences.getString("currentlat","na");
        String lng =sharedPreferences.getString("currentlong","na");
        edt.setText(lat);

        if (googleMap!=null)
        {
            LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,15);
            googleMap.moveCamera(cameraUpdate);

        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;





        String currentlat =PreferenceManager.getDefaultSharedPreferences(getShreadUpdate.this).getString("currentlat","na");
        String currentlng =PreferenceManager.getDefaultSharedPreferences(getShreadUpdate.this).getString("currentlong","na");
        edt.setText("Current Lat"+currentlat+"Current Long"+currentlng);

//        if (googleMap!=null)
//        {
//            LatLng latLng = new LatLng(Double.parseDouble(currentlat), Double.parseDouble(currentlng));
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,15);
//            googleMap.moveCamera(cameraUpdate);
//
//        }

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                PreferenceManager.getDefaultSharedPreferences(getShreadUpdate.this)
                        .edit().putString("currentlat",""+latLng.latitude)
                        .putString("currentlong",""+latLng.longitude)
                        .apply();

            }
        });

    }

    public void intent(View view) {

           Intent intent=new Intent(getShreadUpdate.this,MyIntentService.class);
           intent.putExtra("key","myservice");
           startService(intent);


    }
}