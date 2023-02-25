package com.strong.exo.Map;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class MyIntentService extends IntentService {

    public MyIntentService() {

        super("MyIntentService");
        setIntentRedelivery(true);
    }





    @Override
    protected void onHandleIntent(Intent intent) {


        downloadsong();

    }

    public void downloadsong()
    {
        try {
            Thread.sleep(4000);
            Log.d("tag","Dwonloading song");
        } catch (InterruptedException e) {
            e.printStackTrace();


        }
        Log.d("tag","Dwonloaded song");


    }



}