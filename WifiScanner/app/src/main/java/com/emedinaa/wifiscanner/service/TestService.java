package com.emedinaa.wifiscanner.service;

import com.emedinaa.wifiscanner.MainActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TestService extends Service {
    private static final String TAG = "TestService";

    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
           throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
    }
   
   
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
   
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(), "Service Working",Toast.LENGTH_LONG).show();
        Log.v(TAG, "TestService");

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled",
                    Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        WifiReceiver wifiReceiver = new WifiReceiver();

        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
           
       Intent i = new Intent();
       i.setClass(this, MainActivity.class);
       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(i);

       return super.onStartCommand(intent, flags, startId);
           
    }
}
