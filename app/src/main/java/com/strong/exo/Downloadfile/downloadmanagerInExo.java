package com.strong.exo.Downloadfile;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.google.android.exoplayer2.offline.DownloadRequest;
import com.google.android.exoplayer2.offline.DownloadService;
import com.strong.exo.R;

import java.io.File;

public class downloadmanagerInExo extends AppCompatActivity {
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadmanager_in_exo);
        file=new File(""+Environment.getExternalStorageDirectory());

        if (file.exists())
        {
            Toast.makeText(this, "file exist", Toast.LENGTH_SHORT).show();
        }

    }

    public void dowloadvideo(View view) {
        Mydownloadmanager mydownloadmanager=new Mydownloadmanager(1,file,this);
        DownloadRequest downloadRequest=new DownloadRequest.Builder("1", Uri.parse("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8")).build();
        DownloadService.sendAddDownload(this,Mydownloadmanager.class,downloadRequest,false);
    }
}