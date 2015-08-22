package com.emedinaa.wifiscanner.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootStartUpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
           Intent service = new Intent(context, TestService.class);
           context.startService(service);
    }
}
