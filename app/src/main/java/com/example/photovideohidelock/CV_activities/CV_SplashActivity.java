package com.example.photovideohidelock.CV_activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;

public class CV_SplashActivity extends AppCompatActivity {
    int check_for_first_time;
    int for_question_check;
    Context mContext;
    int p = 0;
    ProgressBar progressBar;
    ImageView splash_ic;
    CV_TinyDB tinyDB;
    int x = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_splash);
        this.mContext = this;
        if (!CV_HelperResizer.getPREFERENCES(this, CV_HelperResizer.CHECK_EXIT, true).booleanValue()) {
            checkPermissions();
            setPref();
            startActivity(new Intent(this.mContext, CV_CalculatorActivity.class));
            finish();
        }
        init();
        resize();
    }

    private void init() {
        this.splash_ic = (ImageView) findViewById(R.id.splash_ic);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.p = 0;
        CV_TinyDB cV_TinyDB = new CV_TinyDB(this.mContext);
        this.tinyDB = cV_TinyDB;
        this.check_for_first_time = cV_TinyDB.getInt(getResources().getString(R.string.first_time));
        this.for_question_check = this.tinyDB.getInt(getResources().getString(R.string.for_question));
    }

    private void resize() {
        CV_HelperResizer.getheightandwidth(this.mContext);
        CV_HelperResizer.setSize(this.splash_ic, 827, 589);
    }

    private void checkPermissions() {
        requestPermissions(Build.VERSION.SDK_INT >= 33 ? new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.READ_MEDIA_AUDIO", "android.permission.READ_CONTACTS", "android.permission.CAMERA"} : new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1101);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1101) {
            if (iArr.length > 0) {
                for (int i2 : iArr) {
                    if (i2 != 0) {
                        finish();
                        return;
                    }
                }
                this.p = 0;
                onSuccess();
                return;
            }
            finish();
        }
    }

    private void onSuccess() {
        if (!isAccessGranted()) {
            this.p = 1;
            Toast.makeText(this.mContext, "Please Grant Permission First", Toast.LENGTH_SHORT).show();
            startActivity(new Intent("android.settings.USAGE_ACCESS_SETTINGS"));
        } else if (!ifCanDrawOverlay()) {
            checkDrawOverlayPermission();
        } else if (this.x == 0) {
            this.x = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    final ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, CV_SplashActivity.this.progressBar.getMax()});
                    ofInt.setDuration(4000);
                    ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            CV_SplashActivity.this.progressBar.setProgress(((Integer) ofInt.getAnimatedValue()).intValue());
                        }
                    });
                    ofInt.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            if (CV_SplashActivity.this.check_for_first_time == 0) {
                                CV_SettingActivity.cv_change_real = 0;
                                CV_SplashActivity.this.startActivity(new Intent(CV_SplashActivity.this.mContext, CV_SetPasswordActivity.class));
                                CV_SplashActivity.this.finish();
                            } else if (CV_SplashActivity.this.for_question_check == 0) {
                                CV_SettingActivity.cv_change_ques = 0;
                                CV_SplashActivity.this.startActivity(new Intent(CV_SplashActivity.this.mContext, CV_SetQuestionActivity.class));
                                CV_SplashActivity.this.finish();
                            } else {
                                CV_SplashActivity.this.startActivity(new Intent(CV_SplashActivity.this.mContext, CV_CalculatorActivity.class));
                                CV_SplashActivity.this.finish();
                            }
                        }
                    });
                    ofInt.start();
                }
            }, 4000);
        }
    }

    private boolean isAccessGranted() {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 0);
            if (((AppOpsManager) getSystemService(Context.APP_OPS_SERVICE)).checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) == 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void checkDrawOverlayPermission() {
        if (!Settings.canDrawOverlays(this.mContext)) {
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getApplicationContext().getPackageName())), 1995);
        }
    }

    public boolean ifCanDrawOverlay() {
        return Settings.canDrawOverlays(this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1995) {
            return;
        }
        if (Settings.canDrawOverlays(this)) {
            onSuccess();
            return;
        }
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getApplicationContext().getPackageName())), 1995);
        Toast.makeText(this.mContext, "Please Grant Overlay Permission", Toast.LENGTH_SHORT).show();
    }

    public void onResume() {
        super.onResume();
        checkPermissions();
    }

    public void setPref() {
        SharedPreferences.Editor edit = getSharedPreferences("consentpreff", 0).edit();
        edit.putBoolean("isDone", true);
        edit.apply();
    }

    public boolean isConsentDone() {
        return getSharedPreferences("consentpreff", 0).getBoolean("isDone", false);
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void ads_adsdk_showUpdateDialog(final String str) {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        View inflate = getLayoutInflater().inflate(R.layout.cv_ads_adsdk_installnewappdialog, (ViewGroup) null);
        dialog.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.update);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_decription);
        textView.setText("Update Now");
        ((TextView) inflate.findViewById(R.id.txt_title)).setText("Update our new app now and enjoy");
        textView2.setText("");
        textView2.setVisibility(8);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    CV_SplashActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (ActivityNotFoundException unused) {
                }
            }
        });
        dialog.create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    public int ads_adsdk_getCurrentVersionCode() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void ads_adsdk_showRedirectDialog(final String str) {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        View inflate = getLayoutInflater().inflate(R.layout.cv_ads_adsdk_installnewappdialog, (ViewGroup) null);
        dialog.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.update);
        textView.setText("Install Now");
        ((TextView) inflate.findViewById(R.id.txt_title)).setText("Install our new app now and enjoy");
        ((TextView) inflate.findViewById(R.id.txt_decription)).setText("We have transferred our server, so install our new app by clicking the button below to enjoy the new features of app.");
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    CV_SplashActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (ActivityNotFoundException unused) {
                }
            }
        });
        dialog.create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }
}
