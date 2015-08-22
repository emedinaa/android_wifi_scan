package com.emedinaa.wifiscanner.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.emedinaa.wifiscanner.model.WifiEntity;
import com.emedinaa.wifiscanner.storage.DatabaseHelper;
import com.emedinaa.wifiscanner.storage.WifiRepository;

import java.util.List;

/**
 * Created by emedinaa on 21/08/15.
 */
public class WifiReceiver extends BroadcastReceiver {

    private static final String TAG = "WifiReceiver";

    private List<ScanResult> wifiList;
    private WifiManager wifiManager;
    private StringBuilder sb;
    private int wifiConnections;
    private WifiRepository wifiRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if(wifiManager==null)return;

        wifiRepository = new WifiRepository(new DatabaseHelper(context));

        sb = new StringBuilder();
        wifiList = wifiManager.getScanResults();
        wifiConnections=wifiList.size();
        sb.append("\n Wifi connections :"+wifiConnections+"\n\n");

        WifiEntity wifiEntity;
        for(int i = 0; i < wifiList.size(); i++){

            sb.append(new Integer(i+1).toString() + ". ");
            sb.append((wifiList.get(i)).toString());
            sb.append("\n\n");

            wifiEntity= new WifiEntity();
            wifiEntity.setSSID(wifiList.get(i).SSID);
            wifiEntity.setBSSID(wifiList.get(i).BSSID);
            wifiEntity.setFrequency(wifiList.get(i).frequency);
            wifiEntity.setLevel(wifiList.get(i).level);
            wifiRepository.saveOrUpdateWifi(wifiEntity);
        }
        Log.v(TAG, "sb " + sb);
        Toast.makeText(context,"Wifi connections :"+wifiConnections,Toast.LENGTH_SHORT).show();
    }

    /*
        Number Of Wifi connections :9
        1. SSID: RED_EMEDINAA, BSSID: e8:94:f6:d2:54:a4, capabilities: [WPA2-PSK-CCMP+TKIP][ESS], level: -55, frequency: 2432, timestamp: 1323318747, distance: ?(cm), distanceSd: ?(cm)
        2. SSID: DIRECT-w3[TV]UN40EH5300, BSSID: 16:49:e0:5b:70:e6, capabilities: [WPA2-PSK-CCMP][WPS][ESS], level: -54, frequency: 2462, timestamp: 1323318730, distance: ?(cm), distanceSd: ?(cm)
        3. SSID: Szpilman3, BSSID: 14:cc:20:d3:0a:1a, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS], level: -59, frequency: 2462, timestamp: 1323318755, distance: ?(cm), distanceSd: ?(cm)
        4. SSID: VIDALITO, BSSID: 08:18:1a:fc:95:6c, capabilities: [WPA-PSK-TKIP][ESS], level: -75, frequency: 2437, timestamp: 1323318762, distance: ?(cm), distanceSd: ?(cm)
        5. SSID: PCECSAC, BSSID: 0c:54:a5:fc:3d:48, capabilities: [WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS], level: -77, frequency: 2447, timestamp: 1323318780, distance: ?(cm), distanceSd: ?(cm)
        6. SSID: WLAN_1B52, BSSID: f8:63:94:98:1b:5b, capabilities: [WPA-PSK-CCMP+TKIP][ESS], level: -81, frequency: 2417, timestamp: 1323318792, distance: ?(cm), distanceSd: ?(cm)
        7. SSID: CHIKI..., BSSID: 7c:b7:33:f3:86:d3, capabilities: [WPA2-PSK-CCMP+TKIP][ESS], level: -80, frequency: 2462, timestamp: 1323318786, distance: ?(cm), distanceSd: ?(cm)
        8. SSID: Wifi_Net1, BSSID: c4:10:8a:e1:43:58, capabilities: [WPA2-EAP-CCMP][ESS], level: -77, frequency: 2412, timestamp: 1323318768, distance: ?(cm), distanceSd: ?(cm)
        9. SSID: wifi_net3, BSSID: c4:10:8a:61:43:58, capabilities: [ESS], level: -76, frequency: 2412, timestamp: 1323318775, distance: ?(cm), distanceSd: ?(cm)
     */
}
