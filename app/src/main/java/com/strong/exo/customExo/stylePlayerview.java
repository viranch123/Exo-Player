package com.strong.exo.customExo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.strong.exo.R;

public class stylePlayerview extends AppCompatActivity {
    StyledPlayerView styledPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_playerview);
        styledPlayerView=findViewById(R.id.styleplayer);
        SimpleExoPlayer simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();
        styledPlayerView.setPlayer(simpleExoPlayer);
                MediaItem mediaItem=new MediaItem.Builder()
                .setUri("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
                .build();


        simpleExoPlayer.setMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();


    }
}