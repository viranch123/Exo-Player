package com.strong.exo.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.strong.exo.R;

public class MyMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbar;
    MapView mapView;
    GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapView = findViewById(R.id.mapview);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("mymap","Maps is called");

        this.googleMap = googleMap;
        LatLng latLng=new LatLng(23.8417,73.7147);
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,15);

        //addMarker
        MarkerOptions markerOptions=new MarkerOptions().title("clicked value").position(latLng);
        googleMap.addMarker(markerOptions);

        LatLng latLng1=new LatLng(23.8418,73.7147);
        MarkerOptions markerOptions1=new MarkerOptions().title("second marker").position(latLng1);
        googleMap.addMarker(markerOptions1);

        googleMap.moveCamera(cameraUpdate);
        Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();


        //zoom
        googleMap.getUiSettings().setZoomControlsEnabled(true);


        //location button
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);


    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mapstype,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        switch (id)
        {
            case R.id.none:
            {
                Toast.makeText(this, "selected", Toast.LENGTH_SHORT).show();
                googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            }
            case R.id.normal:
            {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            }

            case R.id.satellite:
            {
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            }

            case R.id.terrain:
            {
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            }
            case R.id.hybrid:
            {
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            }

        }



        return super.onOptionsItemSelected(item);
        
        
    }
}