package com.internetlistener;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button service;
    private boolean started = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = (Button) findViewById(R.id.service);
        if(isMyServiceRunning(MyService.class)){
            started = true;
            service.setText("Stop Service");
        }else{
            started = false;
            service.setText("Start Service");
        }
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(started){
                    stopService();
                }else{
                    startService();
                }

            }
        });


    }

    public void startService() {
        startService(new Intent(getBaseContext(), MyService.class));
        started = true;
        service.setText("Stop Service");
    }

    // Method to stop the service
    public void stopService() {
        stopService(new Intent(getBaseContext(), MyService.class));
        started = false;
        service.setText("Start Service");
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
