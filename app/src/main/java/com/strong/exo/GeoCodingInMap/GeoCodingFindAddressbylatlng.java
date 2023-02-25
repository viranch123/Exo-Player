package com.strong.exo.GeoCodingInMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.strong.exo.R;

import java.io.IOException;
import java.util.List;

public class GeoCodingFindAddressbylatlng extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    GoogleMap googleMap;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_coding_find_address);
        recyclerView=findViewById(R.id.address);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        supportMapFragment=SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentformapgeo,supportMapFragment).commit();
        
        supportMapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public void getAddress(View view) {

        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> fromLocation = geocoder.getFromLocation(23.8417,73.7147,10);
            Adapter_forShowAdress adapter_forShowAdress=new Adapter_forShowAdress(GeoCodingFindAddressbylatlng.this,fromLocation);
            recyclerView.setAdapter(adapter_forShowAdress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}