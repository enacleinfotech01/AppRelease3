package com.example.photovideohidelock.CV_services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.photovideohidelock.CV_reciever.CV_LocScreenBroadCast;
import com.example.photovideohidelock.R;

public class CV_LockScreenReceiver extends Service {
    public BroadcastReceiver mReceiver = null;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    private void startServiceOreoCondition() {
        if (Build.VERSION.SDK_INT >= 26) {
//            ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(new NotificationChannel("my_service", "Vault Service", 3));
            startForeground(101, new NotificationCompat.Builder((Context) this, "my_service").setCategory(NotificationCompat.CATEGORY_SERVICE).setSmallIcon((int) R.mipmap.ic_launcher).setPriority(-2).build());
            startService(new Intent(this, CV_AppLockService.class));
        }
    }

    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.setPriority(9999);
        CV_LocScreenBroadCast cV_LocScreenBroadCast = new CV_LocScreenBroadCast();
        this.mReceiver = cV_LocScreenBroadCast;
        registerReceiver(cV_LocScreenBroadCast, intentFilter);
        startServiceOreoCondition();
        super.onCreate();
    }

    public void onDestroy() {
        unregisterReceiver(this.mReceiver);
        super.onDestroy();
    }
}
