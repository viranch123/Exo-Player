package com.strong.exo.Map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.strong.exo.R;

public class getupdateonClickmap extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    GoogleMap googleMap;
    SupportMapFragment supportMapFragment;

    Button testbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getupdateon_clickmap);


        testbtn = findViewById(R.id.testbtn);
        supportMapFragment=SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.mapUpdate,supportMapFragment).commit();
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {


                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
                        googleMap.addMarker(new MarkerOptions().position(latLng));

                       PreferenceManager.getDefaultSharedPreferences(getupdateonClickmap.this).edit()
                               .putString("location",""+latLng.toString()).apply();
                    }
                });
            }
        });



        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getupdateonClickmap.this, "this is caled", Toast.LENGTH_SHORT).show();
                PreferenceManager.getDefaultSharedPreferences(getupdateonClickmap.this)
                        .edit().putString("name","test").apply();

            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Toast.makeText(this, "this is called", Toast.LENGTH_SHORT).show();
    }
}