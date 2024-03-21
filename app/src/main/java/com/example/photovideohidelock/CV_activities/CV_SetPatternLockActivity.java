package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_services.CV_AppLockService;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;
import com.itsxtt.patternlock.PatternLockView;

import java.util.ArrayList;
import java.util.Iterator;

public class CV_SetPatternLockActivity extends AppCompatActivity {
    Context cv_mContext;
    String cv_myfrom;
    PatternLockView cv_patternLockView;
    String cv_pkgName;
    CV_TinyDB cv_tinyDB;
    TextView cv_tv_title;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_set_pattern_lock);
        this.cv_mContext = this;
//        new AdAdmob(this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_tv_title = (TextView) findViewById(R.id.tv_title);
        this.cv_patternLockView = (PatternLockView) findViewById(R.id.patternLockView);
        this.cv_tinyDB = new CV_TinyDB(this.cv_mContext);
        String stringExtra = getIntent().getStringExtra("myfrom");
        this.cv_myfrom = stringExtra;
        if (stringExtra.equalsIgnoreCase("set")) {
            this.cv_tv_title.setText("Add Pattern");
        } else if (this.cv_myfrom.equalsIgnoreCase("change")) {
            this.cv_tv_title.setText("Enter Old Pattern");
        } else if (this.cv_myfrom.equalsIgnoreCase("oldpasswordmatch")) {
            this.cv_tv_title.setText("Enter new Pattern");
        } else {
            this.cv_pkgName = getIntent().getStringExtra("pckname");
            this.cv_tv_title.setText("Enter Pattern");
        }
        this.cv_patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
            public void onProgress(ArrayList<Integer> arrayList) {
            }

            public void onStarted() {
            }

            public boolean onComplete(ArrayList<Integer> arrayList) {
                boolean equals = TextUtils.equals(CV_SetPatternLockActivity.this.cv_tinyDB.getString(CV_SetPatternLockActivity.this.getResources().getString(R.string.pattern_password)), CV_SetPatternLockActivity.this.getPatternString(arrayList));
                if ((CV_SetPatternLockActivity.this.cv_tinyDB.getString(CV_SetPatternLockActivity.this.getResources().getString(R.string.pattern_password)).equalsIgnoreCase("") && CV_SetPatternLockActivity.this.cv_myfrom.equalsIgnoreCase("set")) || CV_SetPatternLockActivity.this.cv_myfrom.equalsIgnoreCase("change")) {
                    if (equals) {
                        if (CV_SetPatternLockActivity.this.cv_myfrom != null && !CV_SetPatternLockActivity.this.cv_myfrom.isEmpty() && CV_SetPatternLockActivity.this.cv_myfrom.equals("change")) {
                            Intent intent = new Intent(CV_SetPatternLockActivity.this, CV_SetPatternLockActivityChangePassword.class);
                            intent.putExtra("myfrom", "oldpasswordmatch");
                            CV_SetPatternLockActivity.this.startActivity(intent);
                            CV_SetPatternLockActivity.this.finish();
                        }
                    } else if (CV_SetPatternLockActivity.this.cv_myfrom != null && !CV_SetPatternLockActivity.this.cv_myfrom.isEmpty() && CV_SetPatternLockActivity.this.cv_myfrom.equals("change")) {
                        Toast.makeText(CV_SetPatternLockActivity.this.cv_mContext, "Please Enter Valid Pattern", Toast.LENGTH_SHORT).show();
                    }
                    if (CV_SetPatternLockActivity.this.cv_myfrom != null && !CV_SetPatternLockActivity.this.cv_myfrom.isEmpty() && CV_SetPatternLockActivity.this.cv_myfrom.equals("set")) {
                        CV_SetPatternLockActivity.this.cv_tinyDB.putString(CV_SetPatternLockActivity.this.getResources().getString(R.string.pattern_password), CV_SetPatternLockActivity.this.getPatternString(arrayList));
                        CV_SetPatternLockActivity.this.finish();
                    }
                } else if (equals) {
                    CV_SetPatternLockActivity.this.cv_tinyDB.putLong("lock_curr_milliseconds", System.currentTimeMillis());
                    CV_SetPatternLockActivity.this.cv_tinyDB.putString("last_load_package_name", CV_SetPatternLockActivity.this.cv_pkgName);
                    Intent intent2 = new Intent(CV_AppLockService.UNLOCK_ACTION);
                    intent2.putExtra(CV_AppLockService.LOCK_SERVICE_LASTTIME, System.currentTimeMillis());
                    intent2.putExtra(CV_AppLockService.LOCK_SERVICE_LASTAPP, CV_SetPatternLockActivity.this.cv_pkgName);
                    CV_SetPatternLockActivity.this.sendBroadcast(intent2);
                    CV_AppLockService.from = 1;
                    CV_SetPatternLockActivity.this.finish();
                } else {
                    Toast.makeText(CV_SetPatternLockActivity.this.cv_mContext, "Please Enter Correct Pattern", 0).show();
                }
                return equals;
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
    }

    /* access modifiers changed from: private */
    public String getPatternString(ArrayList<Integer> arrayList) {
        Iterator<Integer> it = arrayList.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next().toString();
        }
        return str;
    }
}
