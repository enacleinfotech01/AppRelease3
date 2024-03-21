package com.example.photovideohidelock.CV_activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.photovideohidelock.CV_adapter.CV_Applist_adapter;
import com.example.photovideohidelock.CV_models.CV_AppList_model;
import com.example.photovideohidelock.CV_services.CV_AppLockService;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;
import java.util.ArrayList;
import java.util.List;

public class CV_AppLockActivity extends AppCompatActivity {
    public static ArrayList<String> cv_selected_app_list;
    CV_Applist_adapter cv_applist_adapter;
    Boolean cv_checkpauseoperation = false;
    ImageView cv_ic_back;
    ArrayList<CV_AppList_model> cv_installedApps;
    ImageView cv_iv_setting;
    Activity cv_mContext;
    RecyclerView cv_rcv_apps;
    CV_TinyDB cv_tinyDB;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_app_lock);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_iv_setting = (ImageView) findViewById(R.id.iv_setting);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_apps);
        this.cv_rcv_apps = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CV_TinyDB cV_TinyDB = new CV_TinyDB(this);
        this.cv_tinyDB = cV_TinyDB;
        cv_selected_app_list = cV_TinyDB.getSelectedAppList(getResources().getString(R.string.app_key));
        new cv_loadApps().execute(new Void[0]);
        this.cv_iv_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AppLockActivity.this.cv_checkpauseoperation = true;
                CV_AppLockActivity cV_AppLockActivity = CV_AppLockActivity.this;
                cV_AppLockActivity.startActivity(new Intent(cV_AppLockActivity, CV_AppLockSettingActivity.class));
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AppLockActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_iv_setting, 50, 50, true);
    }

    public class cv_loadApps extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        public cv_loadApps() {
        }

        public void onPreExecute() {
            super.onPreExecute();
            ProgressDialog progressDialog2 = new ProgressDialog(CV_AppLockActivity.this);
            this.progressDialog = progressDialog2;
            progressDialog2.setCancelable(false);
            this.progressDialog.setMessage("Loading Apps...");
            this.progressDialog.show();
        }

        public Void doInBackground(Void... voidArr) {
            CV_AppLockActivity cV_AppLockActivity = CV_AppLockActivity.this;
            cV_AppLockActivity.cv_installedApps = cV_AppLockActivity.getInstalledApps();
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            this.progressDialog.dismiss();
            if (CV_AppLockActivity.this.cv_installedApps != null && CV_AppLockActivity.this.cv_installedApps.size() > 0) {
                CV_AppLockActivity cV_AppLockActivity = CV_AppLockActivity.this;
                cV_AppLockActivity.cv_applist_adapter = new CV_Applist_adapter(cV_AppLockActivity, CV_AppLockActivity.this.cv_installedApps);
                CV_AppLockActivity.this.cv_rcv_apps.setAdapter(CV_AppLockActivity.this.cv_applist_adapter);
                CV_AppLockActivity.this.cv_applist_adapter.notifyDataSetChanged();
            }
            CV_AppLockActivity cV_AppLockActivity2 = CV_AppLockActivity.this;
            cV_AppLockActivity2.startService(new Intent(cV_AppLockActivity2, CV_AppLockService.class));
        }
    }

    /* access modifiers changed from: private */
    public ArrayList<CV_AppList_model> getInstalledApps() {
        ArrayList<CV_AppList_model> arrayList = new ArrayList<>();
        List<PackageInfo> installedPackages = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            if (getPackageManager().getLaunchIntentForPackage(packageInfo.applicationInfo.packageName) != null) {
                String charSequence = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable loadIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
                String str = packageInfo.applicationInfo.packageName;
                if (!str.equals(getPackageName())) {
                    arrayList.add(new CV_AppList_model(charSequence, loadIcon, str));
                }
            }
        }
        return arrayList;
    }

    public void onResume() {
        super.onResume();
        this.cv_checkpauseoperation = false;
    }

    public void onPause() {
        if (!this.cv_checkpauseoperation.booleanValue()) {
            startActivity(new Intent(this, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_checkpauseoperation = true;
        finish();
    }
}
