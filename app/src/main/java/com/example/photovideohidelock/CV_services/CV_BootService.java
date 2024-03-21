package com.example.photovideohidelock.CV_services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.photovideohidelock.R;

import kotlinx.coroutines.DebugKt;

public class CV_BootService extends Service {
    public static final String TAG = "MyService";

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startServiceOreoCondition() {
        if (Build.VERSION.SDK_INT >= 26) {
//            ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(new NotificationChannel("my_service", "Vault Service", 0));
            startForeground(101, new NotificationCompat.Builder((Context) this, "my_service").setCategory(NotificationCompat.CATEGORY_SERVICE).setSmallIcon((int) R.mipmap.ic_launcher).setPriority(-2).build());
            Log.e(DebugKt.DEBUG_PROPERTY_VALUE_ON, "onReceive: ");
            Intent intent = new Intent(getBaseContext(), CV_AppLockService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startService(intent);
        }
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    public void onStart(Intent intent, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            Log.e("OreoCheck", "onStart: ");
            startServiceOreoCondition();
            return;
        }
        Log.e(DebugKt.DEBUG_PROPERTY_VALUE_ON, "onReceive: ");
        Intent intent2 = new Intent(getBaseContext(), CV_AppLockService.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(intent2);
        Log.d(TAG, "onStart");
    }
}
