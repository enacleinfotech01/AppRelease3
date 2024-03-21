package com.example.photovideohidelock.CV_workers;

import android.content.Context;
import android.content.Intent;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.photovideohidelock.CV_services.CV_AppLockService;

public class CV_ServiceCheckerWorker extends Worker {
    Context context;

    public CV_ServiceCheckerWorker(Context context2, WorkerParameters workerParameters) {
        super(context2, workerParameters);
        this.context = context2;
    }

    public ListenableWorker.Result doWork() {
        Context context2 = this.context;
        context2.startService(new Intent(context2, CV_AppLockService.class));
        return ListenableWorker.Result.success();
    }
}
