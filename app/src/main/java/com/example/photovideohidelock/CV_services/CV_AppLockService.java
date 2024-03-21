package com.example.photovideohidelock.CV_services;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.work.WorkRequest;

import com.example.photovideohidelock.CV_activities.CV_SetPatternLockActivity;
import com.example.photovideohidelock.CV_activities.CV_ShowPasswordActivity;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CV_AppLockService extends Service {
    static final boolean $assertionsDisabled = false;
    public static final String LOCK_SERVICE_LASTAPP = "LOCK_SERVICE_LASTAPP";
    public static final String LOCK_SERVICE_LASTTIME = "LOCK_SERVICE_LASTTIME";
    public static final String UNLOCK_ACTION = "UNLOCK_ACTION";
    public static boolean cv_isActionLock = false;
    public static int from;
    public static CV_AppLockService instance;
    String CURRENT_PACKAGE_NAME = "app.calculator.vault.lock.photo.video";
    ArrayList<String> cv_apps_list;
    /* access modifiers changed from: private */
    public String cv_lastUnlockPackageName = "";
    /* access modifiers changed from: private */
    public long cv_lastUnlockTimeSeconds = 0;
    ActivityManager cv_mActivityManager;
    private ServiceReceiver cv_mServiceReceiver;
    public boolean cv_threadIsTerminate = false;
    CV_TinyDB cv_tinyDB;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return Service.START_STICKY;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        this.cv_mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        this.cv_mServiceReceiver = new ServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction(UNLOCK_ACTION);
        registerReceiver(this.cv_mServiceReceiver, intentFilter);
        this.cv_threadIsTerminate = true;
        this.CURRENT_PACKAGE_NAME = getApplicationContext().getPackageName();
        Log.e("Current PN", "" + this.CURRENT_PACKAGE_NAME);
        this.cv_tinyDB = new CV_TinyDB(instance);
        this.cv_apps_list = new ArrayList<>();
        scheduleMethod();
    }

    private void scheduleMethod() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            public void run() {
                CV_AppLockService.this.cv_apps_list.clear();
                CV_AppLockService cV_AppLockService = CV_AppLockService.this;
                cV_AppLockService.cv_apps_list = cV_AppLockService.cv_tinyDB.getSelectedAppList(CV_AppLockService.instance.getResources().getString(R.string.app_key));
                CV_AppLockService.this.cv_tinyDB.getBoolean("lock_auto_screen");
                CV_AppLockService.this.cv_tinyDB.getBoolean("lock_auto_screen_time");
                String launcherTopApp = CV_AppLockService.this.getLauncherTopApp(CV_AppLockService.instance, CV_AppLockService.this.cv_mActivityManager);
                Log.e("PPP", "" + launcherTopApp);
                CV_AppLockService.this.cv_tinyDB.getString("last_load_package_name");
                if (launcherTopApp.contains("launcher")) {
                    CV_AppLockService.from = 0;
                }
                if (CV_AppLockService.from == 0 && !TextUtils.isEmpty(launcherTopApp) && !CV_AppLockService.this.inWhiteList(launcherTopApp)) {
                    if (CV_AppLockService.this.getHomes().contains(launcherTopApp) || launcherTopApp.contains("launcher")) {
                        CV_AppLockService.from = 0;
                    } else if (CV_AppLockService.this.cv_apps_list.contains(launcherTopApp) && !launcherTopApp.equalsIgnoreCase(CV_AppLockService.this.getPackageName())) {
                        try {
                            if (CV_AppLockService.this.cv_tinyDB.getInt(CV_AppLockService.this.getResources().getString(R.string.pattern_lock_key)) == 0) {
                                String string = CV_AppLockService.this.cv_tinyDB.getString(CV_AppLockService.this.getResources().getString(R.string.apppassword));
                                if (string != null) {
                                    if (!string.isEmpty()) {
                                        Intent intent = new Intent(CV_AppLockService.instance, CV_ShowPasswordActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("pckname", launcherTopApp);
                                        CV_AppLockService.from = 1;
                                        CV_AppLockService.this.startActivity(intent);
                                        return;
                                    }
                                }
                                CV_AppLockService.this.stopSelf();
                                return;
                            }
                            Intent intent2 = new Intent(CV_AppLockService.instance, CV_SetPatternLockActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent2.putExtra("pckname", launcherTopApp);
                            intent2.putExtra("myfrom", NotificationCompat.CATEGORY_SERVICE);
                            CV_AppLockService.from = 1;
                            CV_AppLockService.this.startActivity(intent2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public boolean inWhiteList(String str) {
        return str.equals(instance.getPackageName()) || str.equals("com.android.settings");
    }

    public class ServiceReceiver extends BroadcastReceiver {
        public ServiceReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            CV_AppLockService.this.cv_tinyDB.getBoolean("lock_auto_screen");
            CV_AppLockService.this.cv_tinyDB.getBoolean("lock_auto_screen_time");
            int hashCode = action.hashCode();
            if (hashCode == -2128145023) {
                action.equals("android.intent.action.SCREEN_OFF");
            } else if (hashCode == 1900291985 && action.equals(CV_AppLockService.UNLOCK_ACTION)) {
                String unused = CV_AppLockService.this.cv_lastUnlockPackageName = intent.getStringExtra(CV_AppLockService.LOCK_SERVICE_LASTAPP);
                CV_AppLockService cV_AppLockService = CV_AppLockService.this;
                long unused2 = cV_AppLockService.cv_lastUnlockTimeSeconds = intent.getLongExtra(CV_AppLockService.LOCK_SERVICE_LASTTIME, cV_AppLockService.cv_lastUnlockTimeSeconds);
            }
        }
    }

    public String getLauncherTopApp(Context context, ActivityManager activityManager) {
        long currentTimeMillis = System.currentTimeMillis();
        UsageEvents.Event event = new UsageEvents.Event();
        UsageEvents queryEvents = ((UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE)).queryEvents(currentTimeMillis - WorkRequest.MIN_BACKOFF_MILLIS, currentTimeMillis);
        String str = "";
        while (queryEvents.hasNextEvent()) {
            queryEvents.getNextEvent(event);
            if (event.getEventType() == 1) {
                str = event.getPackageName();
            }
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return "";
    }

    /* access modifiers changed from: private */
    public List<String> getHomes() {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 65536)) {
            arrayList.add(resolveInfo.activityInfo.packageName);
        }
        return arrayList;
    }

    public void onDestroy() {
        super.onDestroy();
        this.cv_threadIsTerminate = false;
        unregisterReceiver(this.cv_mServiceReceiver);
        stopForegroundService();
    }

    private void startForegroundService() {
        Log.e("AAA", "Start foreground service.");
        if (Build.VERSION.SDK_INT >= 26) {
//            createNotificationChannel("my_service", "Vault Service");
            return;
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Vault service");
        bigTextStyle.bigText("This helps to keep lock screen active");
        builder.setStyle(bigTextStyle);
        builder.setContentTitle("Vault service");
        builder.setContentText("This service helps us to keep lock screen active");
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon((int) R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setOngoing(true);
        builder.setPriority(2);
        builder.setFullScreenIntent(activity, true);
        startForeground(1, builder.build());
    }

//    private void createNotificationChannel(String str, String str2) {
//        Intent intent = new Intent(this, CV_SplashActivity.class);
//        TaskStackBuilder create = TaskStackBuilder.create(this);
//        create.addNextIntentWithParentStack(intent);
//        PendingIntent pendingIntent = create.getPendingIntent(0, 134217728);
//        NotificationChannel notificationChannel = new NotificationChannel(str, str2, 3);
//        notificationChannel.setLightColor(-16776961);
//        notificationChannel.setLockscreenVisibility(0);
//        ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(notificationChannel);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder((Context) this, str);
//        Notification build = builder.setOngoing(true).setSmallIcon((int) R.mipmap.ic_launcher).setContentTitle("Vault service is running").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).setContentIntent(pendingIntent).build();
//        NotificationManagerCompat.from(this).notify(1, builder.build());
//        startForeground(1, build);
//    }

    private void stopForegroundService() {
        Log.e("AAA", "Stop foreground service.");
        stopForeground(true);
        stopSelf();
    }
}
