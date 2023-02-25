package com.strong.exo.MapusingFirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.strong.exo.R;

import java.util.ArrayList;
import java.util.List;

public class MapusingFirestore extends AppCompatActivity implements OnMapReadyCallback {
    FirebaseFirestore firebaseFirestore;
    SupportMapFragment supportMapFragment;
    RecyclerView recyclerView;
    GoogleMap googleMap;
    List<modelclass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapusing_firestore);
        recyclerView = findViewById(R.id.recyclerformap);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        list = new ArrayList<>();


        firebaseFirestore = FirebaseFirestore.getInstance();
        getData();
        supportMapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentformap, supportMapFragment).commit();


        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    return;
                }
                if (recyclerView == null)
                    return;

                int firstVisible = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                if (firstVisible == RecyclerView.NO_POSITION) {
                    return;
                }
                if (googleMap != null)
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(3.0f));

                LatLng latLng = new LatLng(list.get(firstVisible).getLatitude(), list.get(firstVisible).getLongtitude());

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);

                MarkerOptions markerOptions = new MarkerOptions().title(list.get(firstVisible).getTitle()).position(latLng);
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

    public void getData() {


        firebaseFirestore.collection("Location")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    modelclass modelclass = documentSnapshot.toObject(com.strong.exo.MapusingFirebase.modelclass.class);
                    list.add(modelclass);
                    LatLng latLng = new LatLng(documentSnapshot.getDouble("latitude"), documentSnapshot.getDouble("longtitude"));
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);


                    MarkerOptions markerOptions = new MarkerOptions().title(documentSnapshot.get("title").toString()).position(latLng);
                    googleMap.addMarker(markerOptions);

                    googleMap.animateCamera(cameraUpdate);

                }


                Adapter_map adapter_map = new Adapter_map(MapusingFirestore.this, list, googleMap);

                recyclerView.setAdapter(adapter_map);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show();
    }

}