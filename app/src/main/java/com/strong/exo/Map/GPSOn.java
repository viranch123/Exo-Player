package com.strong.exo.Map;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.strong.exo.R;

import java.util.List;

public class GPSOn extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,SharedPreferences.OnSharedPreferenceChangeListener {
    GoogleApiClient googleApiClient;
    FusedLocationProviderClient mClient;
    GoogleMap googleMap;
    LocationCallback locationCallback;
    LocationRequest locationRequest;
    LatLng latLng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_s_on);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            } else {
            }
        }

        SupportMapFragment supportMapFragment=SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.mapsfrg,supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);


        // for connecting google service
        googleApiClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this).addApi(LocationServices.API)
                .addOnConnectionFailedListener(this).build();

        googleApiClient.connect();

        mClient = new FusedLocationProviderClient(this);




        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Toast.makeText(GPSOn.this, "CUrrent location"+locationResult.getLastLocation(), Toast.LENGTH_SHORT).show();
                MarkerOptions markerOptions= new MarkerOptions()
                        .position(new LatLng(locationResult.getLastLocation().getLatitude(),locationResult.getLastLocation().getLongitude()));
                googleMap.addMarker(markerOptions);
                Log.d("mylocationupdate",""+locationResult.getLastLocation());


                List<Location> locations = locationResult.getLocations();
                Toast.makeText(GPSOn.this, "location size"+locations.size(), Toast.LENGTH_SHORT).show();

                Log.d("mylogservicedata",""+locations.size());



                String datalocation = "";

                for (Location location:locations)
                {
                    datalocation = ""+location.getLatitude()+"\t"+location.getLongitude();

                }



            }

            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);


                Toast.makeText(GPSOn.this, "location availability"+locationAvailability.isLocationAvailable(), Toast.LENGTH_SHORT).show();
            }
        };





    }

    public void GPSon(View view) {

        locationRequest=LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000);

        //batch location
        locationRequest.setMaxWaitTime(60*1000);




        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder=new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient settingsClient= LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> task=settingsClient.checkLocationSettings(builder.build());



        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if (e instanceof ResolvableApiException)
                {
                    try {
                        ResolvableApiException resolvableApiException= (ResolvableApiException) e;
                        resolvableApiException.startResolutionForResult(GPSOn.this,101);
                        Toast.makeText(GPSOn.this, "GPS is on ", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e1)
                    {
                        Toast.makeText(GPSOn.this, "exception"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;

        //radius show



        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

            }
        });

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this, "connected to api client", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(this, "connection result"+connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("MissingPermission")
    public void GPScheck(View view) {

        mClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

               latLng=new LatLng(location.getLatitude(),location.getLongitude());
                CameraUpdate cameraUpdateFactory=CameraUpdateFactory.newLatLngZoom(latLng,15);

                googleMap.moveCamera(cameraUpdateFactory );
                CircleOptions circleOptions=new CircleOptions().center(latLng).radius(500)
                        .strokeColor(Color.parseColor("#3071cce7"))
                        .fillColor(Color.parseColor("#7571cce7"));


                googleMap.addCircle(circleOptions);


                MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Clicked");
                Marker marker = googleMap.addMarker(markerOptions);

                //marker.setAlpha(2.0f);
                //marker.setAnchor(5,5);
                marker.setDraggable(true);
                marker.setRotation(45);
                marker.setTag("123");
                marker.setSnippet("chirag");
                Toast.makeText(GPSOn.this, ""+location.getLatitude(), Toast.LENGTH_SHORT).show();

                HandlerThread thread = new HandlerThread("chirag");
                mClient.requestLocationUpdates(locationRequest,locationCallback,thread.getLooper());





                try {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            marker.setIcon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(GPSOn.this,R.drawable.exo_controls_fastforward,"name")));

                        }
                    });

                }
                catch (Exception e)
                {
                    Toast.makeText(GPSOn.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {

                        Toast.makeText(GPSOn.this, ""+marker.getPosition(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }

    public static Bitmap createCustomMarker(Context context, @DrawableRes int resource, String _name) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custommarker, null);

       ImageView markerImage = marker.findViewById(R.id.user_dp);
        markerImage.setImageResource(resource);
        TextView txt_name = (TextView) marker.findViewById(R.id.name);
        txt_name.setText(_name);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mClient.removeLocationUpdates(locationCallback);
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);


    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Toast.makeText(this, ""+sharedPreferences.getString("location","na"), Toast.LENGTH_SHORT).show();
        sharedPreferences.getString("location","na");
        Log.d("mytag",""+     sharedPreferences.getString("location","na"));

    } 

    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

    }
}