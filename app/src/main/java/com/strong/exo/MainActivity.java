package com.strong.exo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerView=findViewById(R.id.player);
        simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(simpleExoPlayer);



        MediaItem item1 = MediaItem.fromUri("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4");
        MediaItem item2=MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");
        MediaItem item4=MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
        MediaItem item3 =MediaItem.fromUri("https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_1920_18MG.mp4");
        simpleExoPlayer.addMediaItem(item1);
        simpleExoPlayer.addMediaItem(item2);
        simpleExoPlayer.addMediaItem(item3);
        simpleExoPlayer.addMediaItem(item4);

        //for skipi some seconds
        simpleExoPlayer.seekTo(10000);









        simpleExoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);
//        simpleExoPlayer.setShuffleModeEnabled(true);

        simpleExoPlayer.prepare();
            simpleExoPlayer.play();

            simpleExoPlayer.addListener(new Player.EventListener() {
                @Override
                public void onPlayerError(ExoPlaybackException error) {
                    Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    public void suffle(View view) {

        Toast.makeText(this, ""+simpleExoPlayer.getShuffleModeEnabled(), Toast.LENGTH_SHORT).show();
    }
}