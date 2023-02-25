package com.strong.exo.SimpleExoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.collect.Lists;
import com.strong.exo.R;

public class exoplayer extends AppCompatActivity {
    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer);
        playerView=findViewById(R.id.playerview);
        simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();

        playerView.setPlayer(simpleExoPlayer);
//        MediaItem mediaItem=new MediaItem.Builder()
//                .setUri("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
//                .build();

        MediaItem mediaItem = new MediaItem.Builder()
                .setUri("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")
                .setClipStartPositionMs(3000)
                .setClipEndPositionMs(14000)
                .build();




       // MediaItem mediaItem = MediaItem.fromUri("https://bitmovin-a.akamaihd.net/content/playhouse-vr/mpds/105560.mpd");
        simpleExoPlayer.setMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();

    }
}