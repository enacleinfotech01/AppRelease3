package com.example.photovideohidelock;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;

import com.example.photovideohidelock.CV_services.CV_LockScreenReceiver;
import com.example.photovideohidelock.CV_workers.CV_WorkerStarter;

public class CV_LockApplication extends Application {
    private static final String ONESIGNAL_APP_ID = "30f68423-05c6-4480-a1cc-6c8e5b950667";
    public static Context context;
    private static CV_LockApplication cv_lockApplication;
    public static CV_LockApplication singleton;

    public static CV_LockApplication getInstance() {
        return singleton;
    }

    public void onCreate() {
        super.onCreate();
        singleton = this;
        cv_lockApplication = this;
        context = this;
//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
//        OneSignal.initWithContext(this);
//        OneSignal.setAppId(ONESIGNAL_APP_ID);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        if (Build.VERSION.SDK_INT >= 26) {
            Context context2 = context;
            context2.startForegroundService(new Intent(context2, CV_LockScreenReceiver.class));
            return;
        }
        Context context3 = context;
        context3.startService(new Intent(context3, CV_LockScreenReceiver.class));
        CV_WorkerStarter.startServiceCheckerWorker();
        context = getApplicationContext();
    }

    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getContext() {
        return context;
    }

    public static CV_LockApplication getApp() {
        return cv_lockApplication;
    }
}
