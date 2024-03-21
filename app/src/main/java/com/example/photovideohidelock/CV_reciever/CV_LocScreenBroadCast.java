package com.example.photovideohidelock.CV_reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.photovideohidelock.CV_services.CV_AppLockService;

public class CV_LocScreenBroadCast extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.e("PPP", "[BroadCastReciever] onRecieve()");
        if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
            Log.e("PPP", "[BroadCastReciever] Screen went OFF");
        } else if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
            Log.e("PPP", "[BroadCastReciever] Screen went ON");
        } else if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Log.e("PPP", "onReceive: boot");
        }
        context.startService(new Intent(context, CV_AppLockService.class));
    }
}
