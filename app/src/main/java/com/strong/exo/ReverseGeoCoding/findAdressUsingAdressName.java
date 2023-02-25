package com.strong.exo.ReverseGeoCoding;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.strong.exo.R;

import java.io.IOException;
import java.util.List;

public class findAdressUsingAdressName extends AppCompatActivity implements OnMapReadyCallback {
SupportMapFragment supportMapFragment;
TextView textView;
EditText editText;
GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_adress_using_adress_name);
        editText=findViewById(R.id.edt_address);
        supportMapFragment=SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frgmap,supportMapFragment).commit();

        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    this.googleMap=googleMap;
    }

    public void getAddressfromname(View view) {
        Geocoder geocoder=new Geocoder(this);

        try {

            double bottomlat = 23.839291;
            double bottomleft = 73.85013887388492;
            double toplat = 23.839291;
            double topright = 73.72331008536622-0.05;

            editText.setText("Dungarpur");
            List<Address> dungarpur = geocoder.getFromLocationName(editText.getText().toString(), 5,bottomlat,bottomleft ,toplat ,topright );
          //  MarkerOptions markerOptions=new MarkerOptions().position(new LatLng(dungarpur.get(0).getLatitude(),dungarpur.get(0).getLongitude()));

            MarkerOptions markerOptions=new MarkerOptions().position(new LatLng(bottomlat, bottomleft));

            MarkerOptions markerOptions1=new MarkerOptions().position(new LatLng(toplat, topright));

            googleMap.addMarker(markerOptions);
            googleMap.addMarker(markerOptions1);
            LatLng latLng=new LatLng(dungarpur.get(0).getLatitude(),dungarpur.get(0).getLongitude());

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}