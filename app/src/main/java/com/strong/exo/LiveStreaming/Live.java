package com.strong.exo.LiveStreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.strong.exo.R;

public class Live extends AppCompatActivity {
PlayerView playerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        playerView=findViewById(R.id.playerView);

        SimpleExoPlayer simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();

        MediaItem mediaItem=new MediaItem.Builder()
                .setUri("https://www.youtube.com/watch?v=FGfQPjup0X8")
                .setLiveMaxPlaybackSpeed(1.02f)
                .build();
        playerView.setPlayer(simpleExoPlayer);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();
    }
}