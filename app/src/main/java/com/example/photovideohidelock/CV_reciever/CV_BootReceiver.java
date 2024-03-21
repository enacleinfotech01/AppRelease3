package com.example.photovideohidelock.CV_reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.example.photovideohidelock.CV_services.CV_BootService;

public class CV_BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.e("AfterBoot", "onReceive: Call In Receiver");
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Log.e("Boot", "onReceive: BootReciver");
            Intent intent2 = new Intent(context, CV_BootService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent2);
            } else {
                context.startService(intent2);
            }
            Log.i("Autostart", "started");
        }
    }
}
