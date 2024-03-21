package com.example.photovideohidelock.CV_activities;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_reciever.CV_DemoDeviceAdminReceiver;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

public class CV_SettingActivity extends AppCompatActivity {
    public static int cv_change_ques;
    public static int cv_change_real;
    private final int REQUEST_ENABLE = 1;
    Boolean cv_checkpauseoperation = false;
    ComponentName cv_demoDeviceAdmin;
    ImageView cv_ic_back;
    ImageView cv_ic_onoff;
    ImageView cv_ic_pp;
    ImageView cv_ic_rate;
    ImageView cv_ic_share;
    ImageView cv_ic_uninstall;
    ImageView cv_ic_update_calpass;
    ImageView cv_ic_update_ques;
    Context cv_mContext;
    private FrameLayout flNativeAds;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_setting);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_ic_update_ques = (ImageView) findViewById(R.id.ic_update_ques);
        this.cv_ic_update_calpass = (ImageView) findViewById(R.id.ic_update_calpass);
        this.cv_ic_uninstall = (ImageView) findViewById(R.id.ic_uninstall);
        this.cv_ic_share = (ImageView) findViewById(R.id.ic_share);
        this.cv_ic_rate = (ImageView) findViewById(R.id.ic_rate);
        this.cv_ic_pp = (ImageView) findViewById(R.id.ic_pp);
        this.cv_ic_update_ques.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SettingActivity.this.cv_checkpauseoperation = true;
                CV_SettingActivity.cv_change_ques = 1;
                CV_SettingActivity cV_SettingActivity = CV_SettingActivity.this;
                cV_SettingActivity.startActivity(new Intent(cV_SettingActivity.cv_mContext, CV_SetQuestionActivity.class));
            }
        });
        this.cv_ic_update_calpass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SettingActivity.this.cv_checkpauseoperation = true;
                CV_SettingActivity.cv_change_real = 1;
                CV_SettingActivity cV_SettingActivity = CV_SettingActivity.this;
                cV_SettingActivity.startActivity(new Intent(cV_SettingActivity.cv_mContext, CV_SetPasswordActivity.class).putExtra("chengepassword", "AppLockSettingActivity"));
            }
        });
        this.cv_ic_uninstall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SettingActivity.this.cv_checkpauseoperation = true;
                CV_SettingActivity.this.cv_ShowUninstallDialog();
            }
        });
        this.cv_ic_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SettingActivity.this.cv_checkpauseoperation = true;
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", R.string.app_name);
                intent.putExtra("android.intent.extra.TEXT", "See " + CV_SettingActivity.this.getString(R.string.app_name) + " from -  https://play.google.com/store/apps/details?id=" + CV_SettingActivity.this.getPackageName());
                CV_SettingActivity.this.startActivity(Intent.createChooser(intent, "Share Application"));
            }
        });
        this.cv_ic_rate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SettingActivity.this.cv_checkpauseoperation = true;
                try {
                    CV_SettingActivity cV_SettingActivity = CV_SettingActivity.this;
                    cV_SettingActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + CV_SettingActivity.this.getPackageName())));
                } catch (Exception unused) {
                    CV_SettingActivity cV_SettingActivity2 = CV_SettingActivity.this;
                    cV_SettingActivity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + CV_SettingActivity.this.getPackageName())));
                }
            }
        });
        this.cv_ic_pp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SettingActivity.this.cv_checkpauseoperation = true;
                CV_SettingActivity cV_SettingActivity = CV_SettingActivity.this;
                cV_SettingActivity.startActivity(new Intent(cV_SettingActivity.cv_mContext, CV_PrivacyPolicy.class));
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SettingActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_update_ques, 980, 120);
        CV_HelperResizer.setSize(this.cv_ic_update_calpass, 980, 120);
        CV_HelperResizer.setSize(this.cv_ic_uninstall, 980, 120);
        CV_HelperResizer.setSize(this.cv_ic_share, 980, 120);
        CV_HelperResizer.setSize(this.cv_ic_rate, 980, 120);
        CV_HelperResizer.setSize(this.cv_ic_pp, 980, 120);
    }

    /* access modifiers changed from: private */
    public void cv_ShowUninstallDialog() {
        final Dialog dialog = new Dialog(this.cv_mContext, R.style.AppTheme_FullScreenDialog);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_uninstall_setting_dialog_layout);
        dialog.show();
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.start_lay);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ic_back);
        this.cv_ic_onoff = (ImageView) dialog.findViewById(R.id.ic_onoff);
        final DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName = new ComponentName(this, CV_DemoDeviceAdminReceiver.class);
        this.cv_demoDeviceAdmin = componentName;
        if (!devicePolicyManager.isAdminActive(componentName)) {
            this.cv_ic_onoff.setImageResource(R.drawable.cv_off);
        } else {
            this.cv_ic_onoff.setImageResource(R.drawable.cv_on);
        }
        CV_HelperResizer.setSize(linearLayout, 980, 120, true);
        CV_HelperResizer.setSize(this.cv_ic_onoff, 90, 48, true);
        CV_HelperResizer.setSize(imageView, 60, 60, true);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        this.cv_ic_onoff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!devicePolicyManager.isAdminActive(CV_SettingActivity.this.cv_demoDeviceAdmin)) {
                    CV_SettingActivity.this.cv_ShowAdminDialog();
                    return;
                }
                CV_SettingActivity.this.cv_ic_onoff.setImageResource(R.drawable.cv_off);
                ((DevicePolicyManager) CV_SettingActivity.this.getSystemService(Context.DEVICE_POLICY_SERVICE)).removeActiveAdmin(CV_SettingActivity.this.cv_demoDeviceAdmin);
            }
        });
    }

    /* access modifiers changed from: private */
    public void cv_ShowAdminDialog() {
        final Dialog dialog = new Dialog(this.cv_mContext);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_device_admin_dialog_layout);
        dialog.show();
        ImageView imageView = (ImageView) dialog.findViewById(R.id.btn_ok);
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(dialog.findViewById(R.id.popup), 800, 750, true);
        CV_HelperResizer.setSize(imageView, 200, 100, true);
        CV_HelperResizer.setMargin(imageView, 0, 0, 45, 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
                intent.putExtra("android.app.extra.DEVICE_ADMIN", CV_SettingActivity.this.cv_demoDeviceAdmin);
                intent.putExtra("android.app.extra.ADD_EXPLANATION", "Log Explanation");
                CV_SettingActivity.this.startActivityForResult(intent, 1);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            this.cv_ic_onoff.setImageResource(R.drawable.cv_on);
        } else {
            this.cv_ic_onoff.setImageResource(R.drawable.cv_off);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_checkpauseoperation = true;
        finish();
    }

    public void onResume() {
        super.onResume();
        this.cv_checkpauseoperation = false;
    }

    public void onPause() {
        if (!this.cv_checkpauseoperation.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
