package com.internetlistener;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by knroy on 12/3/2017.
 * Don't modify without proper privileges
 */

public class MyService extends Service {
    BroadcastReceiver br;
    IntentFilter filter;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        br = new MyBroadcastReceiver();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(br, filter);
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
        br = null;
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }
}
