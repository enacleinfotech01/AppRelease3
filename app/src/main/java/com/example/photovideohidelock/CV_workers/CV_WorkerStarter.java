package com.example.photovideohidelock.CV_workers;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

public class CV_WorkerStarter {
    public static String UNIQUE_WORK_SERVICE_CHECKER = "UNIQUE_WORK_SERVICE_CHECKER";

    public static void startServiceCheckerWorker() {
        WorkManager.getInstance().enqueueUniquePeriodicWork(UNIQUE_WORK_SERVICE_CHECKER, ExistingPeriodicWorkPolicy.REPLACE, (PeriodicWorkRequest) ((PeriodicWorkRequest.Builder) new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) CV_ServiceCheckerWorker.class, 1, TimeUnit.MILLISECONDS).setInitialDelay(16, TimeUnit.MINUTES)).build());
    }
}
