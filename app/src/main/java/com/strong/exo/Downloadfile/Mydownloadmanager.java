package com.strong.exo.Downloadfile;

import android.app.Notification;
import android.content.Context;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.offline.Download;
import com.google.android.exoplayer2.offline.DownloadManager;
import com.google.android.exoplayer2.offline.DownloadService;
import com.google.android.exoplayer2.scheduler.Scheduler;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executor;

public class Mydownloadmanager extends DownloadService {
    ExoDatabaseProvider exoDatabaseProvider;
    File file;
    SimpleCache simpleCache;
    DefaultHttpDataSourceFactory sourceFactory;
    Executor executor;
    DownloadManager downloadManager;
    Context context;

    protected Mydownloadmanager(int foregroundNotificationId,File file,Context context) {
        super(foregroundNotificationId);
        exoDatabaseProvider=new ExoDatabaseProvider(context);
        simpleCache=new SimpleCache(file,new NoOpCacheEvictor(),exoDatabaseProvider);
        sourceFactory=new DefaultHttpDataSourceFactory();
        executor=Runnable::run;
        this.context=context;
    }

    @Override
    protected DownloadManager getDownloadManager() {
        downloadManager=new DownloadManager(context,exoDatabaseProvider,simpleCache,sourceFactory,executor);
        return downloadManager;
    }

    @Nullable
    @Override
    protected Scheduler getScheduler() {
        return null;
    }

    @Override
    protected Notification getForegroundNotification(List<Download> downloads) {
        return null;
    }
}
