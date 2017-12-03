package com.internetlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by knroy on 12/3/2017.
 * Don't modify without proper privileges
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    private boolean connected = false;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        switch (action) {
            case ConnectivityManager.CONNECTIVITY_ACTION:

                if(isNetworkConnected(context)){
                    //do action here
                    if(!connected) {
                        Toast.makeText(context, "Internet Available!", Toast.LENGTH_SHORT).show();
                    }
                    connected = true;
                }else{
                    if(connected){
                        Toast.makeText(context, "Internet Unavailable!", Toast.LENGTH_SHORT).show();
                    }
                    connected = false;
                }
                break;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }catch (Exception e){
            return true;
        }
    }
}