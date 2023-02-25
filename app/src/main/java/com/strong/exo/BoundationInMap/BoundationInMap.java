package com.strong.exo.BoundationInMap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.strong.exo.R;

public class BoundationInMap extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment supportMapFragment;
    //lat x1
    public static double bottomboundary = -35.0 -0.5;
    //lang y1
    public static double leftboundary = 138.58 - 0.1;
    //lat x2
    public static double topboundary = -34.9 + 0.1;
    //lang y2
    public static double rightboundary = 138.61 + 0.5;


    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundation_in_map);

        supportMapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.boundationmapfragment, supportMapFragment).commit();

        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;


    }

    public void setBounds(View view) {

        if (googleMap != null) {

            LatLngBounds latLngBounds = new LatLngBounds(new LatLng(bottomboundary, leftboundary), new LatLng(topboundary, rightboundary));
            MarkerOptions markerOptions = new MarkerOptions().title("AUSTRALIA").position(new LatLng(bottomboundary, leftboundary));
            googleMap.addMarker(markerOptions);


            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 1));

        }


    }

    public void setboudation(View view) {

        if (googleMap != null) {


            LatLngBounds latLngBounds = new LatLngBounds(new LatLng(bottomboundary, leftboundary), new LatLng(topboundary, rightboundary));
            MarkerOptions markerOptions = new MarkerOptions().title("AUSTRALIA").position(new LatLng(bottomboundary, leftboundary));
            googleMap.addMarker(markerOptions);

            //for restriction of boundation it is bound in particalur area
            googleMap.setLatLngBoundsForCameraTarget(latLngBounds);


            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 1));

        }

    }
}