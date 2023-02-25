package com.strong.exo.customExo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.strong.exo.R;

public class customizedplayer extends AppCompatActivity {
    PlayerView surfaceView;
    boolean fullscreen = false;
    ImageView fullscreenview;
    SimpleExoPlayer simpleExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        fullscreenview=findViewById(R.id.exo_fullscreen);
        surfaceView=findViewById(R.id.surface_views);
         simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();
        //SimpleExoPlayer simpleExoPlayer=null;
        surfaceView.setPlayer(simpleExoPlayer);
        MediaItem mediaItem1=new MediaItem.Builder()
                .setUri("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
                .build();
        MediaItem mediaItem2=new MediaItem.Builder()
                .setUri("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
                .build();

        surfaceView.setShowFastForwardButton(true);


        simpleExoPlayer.addMediaItem(mediaItem1);
        simpleExoPlayer.addMediaItem(mediaItem2);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();


        // Creates a button that mimics a crash when clicked
//        Button crashButton = new Button(this);
//        crashButton.setText("Crash!");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
//
//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));






        fullscreenview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(fullscreen) {
                    fullscreenview.setImageDrawable(ContextCompat.getDrawable(customizedplayer.this, R.drawable.ic_baseline_fullscreen_24));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    if(getSupportActionBar() != null){
                        getSupportActionBar().show();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    fullscreen = false;
                }else{
                    fullscreenview.setImageDrawable(ContextCompat.getDrawable(customizedplayer.this, R.drawable.ic_baseline_fullscreen_24));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    if(getSupportActionBar() != null){
                        getSupportActionBar().hide();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    fullscreen = true;
                }





            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }


}