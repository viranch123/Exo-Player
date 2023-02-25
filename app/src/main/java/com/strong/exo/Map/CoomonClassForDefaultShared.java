package com.strong.exo.Map;

import android.app.Activity;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.LatLng;

public class CoomonClassForDefaultShared {


    public static  void saveLocationResult(Activity activity, LatLng latLng)
    {
        PreferenceManager.getDefaultSharedPreferences(activity)
                .edit()
                .putString("location",""+latLng)
                .commit();
    }


    public static  String getLocationResult(Activity activity)
    {


        return PreferenceManager.getDefaultSharedPreferences(activity)
                .getString("location","na");
    }
}
