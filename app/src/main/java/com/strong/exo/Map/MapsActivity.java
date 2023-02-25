package com.strong.exo.Map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.strong.exo.R;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        getSupportFragmentManager().beginTransaction().add(R.id.container,new MapsFragment()).commit();
       String result= CoomonClassForDefaultShared.getLocationResult(this);
        Toast.makeText(this, "value"+result, Toast.LENGTH_SHORT).show();
    }
}