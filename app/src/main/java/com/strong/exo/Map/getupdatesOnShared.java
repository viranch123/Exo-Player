package com.strong.exo.Map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.strong.exo.R;

public class getupdatesOnShared extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
   
    EditText editText;

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getupdates_on_shared);
        editText=findViewById(R.id.msg);



        
        
        


}

    public void getUpdate(View view) {
        
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit().putString("name",editText.getText().toString()).apply();
        
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Toast.makeText(this, "SharedPrefrences Call "+sharedPreferences.getString("name","na"), Toast.LENGTH_SHORT).show();
    }
}