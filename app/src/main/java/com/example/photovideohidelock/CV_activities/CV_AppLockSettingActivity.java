package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;

public class CV_AppLockSettingActivity extends AppCompatActivity {
    Boolean cv_checkpauseopration = false;
    ImageView cv_ic_back;
    ImageView cv_ic_changepattern;
    ImageView cv_ic_pass;
    ImageView cv_iv_pattern_onoff;
    Context cv_mContext;
    int cv_pattern_flag;
    LinearLayout cv_pattern_lay;
    CV_TinyDB cv_tinyDB;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_app_lock_setting);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_pattern_lay = (LinearLayout) findViewById(R.id.pattern_lay);
        this.cv_iv_pattern_onoff = (ImageView) findViewById(R.id.iv_pattern_onoff);
        this.cv_ic_pass = (ImageView) findViewById(R.id.ic_pass);
        this.cv_ic_changepattern = (ImageView) findViewById(R.id.ic_changepattern);
        CV_TinyDB cV_TinyDB = new CV_TinyDB(this.cv_mContext);
        this.cv_tinyDB = cV_TinyDB;
        int i = cV_TinyDB.getInt(getResources().getString(R.string.pattern_lock_key));
        this.cv_pattern_flag = i;
        if (i == 0 && this.cv_tinyDB.getString(getResources().getString(R.string.pattern_password)).equalsIgnoreCase("")) {
            this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_off);
        } else if (this.cv_pattern_flag != 1 || this.cv_tinyDB.getString(getResources().getString(R.string.pattern_password)).equalsIgnoreCase("")) {
            this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_off);
        } else {
            this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_on);
        }
        this.cv_ic_pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AppLockSettingActivity.this.cv_checkpauseopration = true;
                CV_SettingActivity.cv_change_real = 1;
                CV_AppLockSettingActivity cV_AppLockSettingActivity = CV_AppLockSettingActivity.this;
                CV_Adshow.showinterstitialAd(cV_AppLockSettingActivity, new Intent(cV_AppLockSettingActivity.cv_mContext, CV_ChangeAppPasswordActivity.class));
            }
        });
        this.cv_iv_pattern_onoff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AppLockSettingActivity cV_AppLockSettingActivity = CV_AppLockSettingActivity.this;
                cV_AppLockSettingActivity.cv_pattern_flag = cV_AppLockSettingActivity.cv_tinyDB.getInt(CV_AppLockSettingActivity.this.getResources().getString(R.string.pattern_lock_key));
                if (CV_AppLockSettingActivity.this.cv_pattern_flag == 0) {
                    CV_AppLockSettingActivity.this.cv_tinyDB.putInt(CV_AppLockSettingActivity.this.getResources().getString(R.string.pattern_lock_key), 1);
                    CV_AppLockSettingActivity.this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_on);
                    if (CV_AppLockSettingActivity.this.cv_tinyDB.getString(CV_AppLockSettingActivity.this.getResources().getString(R.string.pattern_password)).equalsIgnoreCase("")) {
                        CV_AppLockSettingActivity.this.cv_checkpauseopration = true;
                        CV_AppLockSettingActivity cV_AppLockSettingActivity2 = CV_AppLockSettingActivity.this;
                        cV_AppLockSettingActivity2.startActivity(new Intent(cV_AppLockSettingActivity2.cv_mContext, CV_SetPatternLockActivity.class).putExtra("myfrom", "set"));
                        return;
                    }
                    return;
                }
                CV_AppLockSettingActivity.this.cv_tinyDB.putInt(CV_AppLockSettingActivity.this.getResources().getString(R.string.pattern_lock_key), 0);
                CV_AppLockSettingActivity.this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_off);
            }
        });
        this.cv_ic_changepattern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AppLockSettingActivity.this.cv_checkpauseopration = true;
                CV_AppLockSettingActivity cV_AppLockSettingActivity = CV_AppLockSettingActivity.this;
                cV_AppLockSettingActivity.cv_pattern_flag = cV_AppLockSettingActivity.cv_tinyDB.getInt(CV_AppLockSettingActivity.this.getResources().getString(R.string.pattern_lock_key));
                if (CV_AppLockSettingActivity.this.cv_pattern_flag != 1 || CV_AppLockSettingActivity.this.cv_tinyDB.getString(CV_AppLockSettingActivity.this.getResources().getString(R.string.pattern_password)).equalsIgnoreCase("")) {
                    Toast.makeText(CV_AppLockSettingActivity.this.cv_mContext, "Please Create And Enable Pattern First", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(CV_AppLockSettingActivity.this.cv_mContext, CV_SetPatternLockActivity.class);
                intent.putExtra("myfrom", "change");
                CV_Adshow.showinterstitialAd(CV_AppLockSettingActivity.this, intent);
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AppLockSettingActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_pattern_lay, 980, 120, true);
        CV_HelperResizer.setSize(this.cv_ic_pass, 980, 120, true);
        CV_HelperResizer.setSize(this.cv_ic_changepattern, 980, 120, true);
        CV_HelperResizer.setSize(this.cv_iv_pattern_onoff, 90, 48);
        CV_HelperResizer.setMargin(this.cv_ic_pass, 0, 60, 0, 0);
        CV_HelperResizer.setMargin(this.cv_pattern_lay, 0, 10, 0, 0);
        CV_HelperResizer.setMargin(this.cv_ic_changepattern, 0, 10, 0, 0);
    }

    public void onResume() {
        super.onResume();
        this.cv_checkpauseopration = false;
        this.cv_pattern_flag = this.cv_tinyDB.getInt(getResources().getString(R.string.pattern_lock_key));
        if (this.cv_tinyDB.getString(getResources().getString(R.string.pattern_password)).equalsIgnoreCase("")) {
            if (this.cv_pattern_flag == 0) {
                this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_off);
            }
        } else if (this.cv_pattern_flag == 1) {
            this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_on);
        } else {
            this.cv_iv_pattern_onoff.setImageResource(R.drawable.cv_off);
        }
    }

    public void onPause() {
        super.onPause();
        if (!this.cv_checkpauseopration.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_checkpauseopration = true;
        finish();
    }
}
