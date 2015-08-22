package com.emedinaa.wifiscanner;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.emedinaa.wifiscanner.model.WifiEntity;
import com.emedinaa.wifiscanner.service.WifiReceiver;
import com.emedinaa.wifiscanner.storage.DatabaseHelper;
import com.emedinaa.wifiscanner.storage.WifiRepository;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        WifiRepository wifiRepository = new WifiRepository(this);
        List<WifiEntity> wifiEntities= wifiRepository.getAll();
        int connections=wifiEntities.size();
        String msg= "Conexiones DB "+connections+"\n";
        if(connections>0)
        {
            WifiEntity wifiEntity= wifiEntities.get(0);
            msg+="0:  SSID"+wifiEntity.getSSID()+ " BSSID "+wifiEntity.getBSSID()+ " level "+wifiEntity.getLevel()+
                    " Frequency "+wifiEntity.getFrequency();

            ((TextView)findViewById(R.id.tviConexionesDB)).setText(msg);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
