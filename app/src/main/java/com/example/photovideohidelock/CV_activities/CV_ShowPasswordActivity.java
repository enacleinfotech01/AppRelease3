package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_services.CV_AppLockService;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CV_ShowPasswordActivity extends AppCompatActivity {
    public String cv_final_string = "";
    Context cv_mContext;
    private Animation cv_mShakeAnim;
    TextView cv_mUnlockFailTip;
    String cv_pkgName;
    private List<EditText> cv_pointList;
    CV_TinyDB cv_tinyDB;
    TextView cv_tv_lable;
    RoundedImageView cv_unlock_icon;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_show_password);
        this.cv_mContext = this;
//        new AdAdmob(this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_tinyDB = new CV_TinyDB(this.cv_mContext);
        this.cv_mUnlockFailTip = (TextView) findViewById(R.id.unlock_text);
        this.cv_unlock_icon = (RoundedImageView) findViewById(R.id.unlock_icon);
        this.cv_tv_lable = (TextView) findViewById(R.id.tv_lable);
        this.cv_mShakeAnim = AnimationUtils.loadAnimation(this.cv_mContext, R.anim.cv_shake_x);
        this.cv_pkgName = getIntent().getStringExtra("pckname");
        ArrayList arrayList = new ArrayList(4);
        this.cv_pointList = arrayList;
        arrayList.add((EditText) findViewById(R.id.edt_one));
        this.cv_pointList.add((EditText) findViewById(R.id.edt_two));
        this.cv_pointList.add((EditText) findViewById(R.id.edt_three));
        this.cv_pointList.add((EditText) findViewById(R.id.edt_four));
        for (EditText text : this.cv_pointList) {
            text.setText("");
        }
        try {
            this.cv_unlock_icon.setImageDrawable(this.cv_mContext.getPackageManager().getApplicationIcon(this.cv_pkgName));
            PackageManager packageManager = getApplicationContext().getPackageManager();
            this.cv_tv_lable.setText((String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.cv_pkgName, 128)));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_unlock_icon, 150, 150, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_del), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_0), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_none), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_9), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_8), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_7), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_6), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_5), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_4), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_3), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_2), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.number_1), 180, 180, true);
        CV_HelperResizer.setSize(findViewById(R.id.num_point_1), 100, 4, true);
        CV_HelperResizer.setSize(findViewById(R.id.num_point_2), 100, 4, true);
        CV_HelperResizer.setSize(findViewById(R.id.num_point_3), 100, 4, true);
        CV_HelperResizer.setSize(findViewById(R.id.num_point_4), 100, 4, true);
        CV_HelperResizer.setWidth(this.cv_mContext, findViewById(R.id.edt_one), 100);
        CV_HelperResizer.setWidth(this.cv_mContext, findViewById(R.id.edt_two), 100);
        CV_HelperResizer.setWidth(this.cv_mContext, findViewById(R.id.edt_three), 100);
        CV_HelperResizer.setWidth(this.cv_mContext, findViewById(R.id.edt_four), 100);
    }

    public void cv_onNumClick(View view) {
        int id = view.getId();/*2131296797*//*2131296798*//*2131296799*//*2131296800*//*2131296801*//*2131296802*//*2131296803*//*2131296804*//*2131296805*/
        if (id == R.id.number_0 || id == R.id.number_1 || id == R.id.number_2 || id == R.id.number_3 || id == R.id.number_4 || id == R.id.number_5 || id == R.id.number_6 || id == R.id.number_7 || id == R.id.number_8 || id == R.id.number_9) { /*2131296806*/
            cv_clickNumber((TextView) view);
            return;
        } else if (id == R.id.number_del) { /*2131296807*/
            cv_deleteNumber();
            return;
        }
        return;
    }

    private void cv_clickNumber(TextView textView) {
        if (this.cv_final_string.length() != 4) {
            String concat = this.cv_final_string.concat(textView.getText().toString());
            this.cv_final_string = concat;
            this.cv_pointList.get(concat.length() - 1).setText(textView.getText().toString());
            if (this.cv_final_string.length() == 4) {
                if (this.cv_tinyDB.getString(getResources().getString(R.string.apppassword)).equalsIgnoreCase(this.cv_final_string)) {
                    this.cv_tinyDB.putLong("lock_curr_milliseconds", System.currentTimeMillis());
                    this.cv_tinyDB.putString("last_load_package_name", this.cv_pkgName);
                    Intent intent = new Intent(CV_AppLockService.UNLOCK_ACTION);
                    intent.putExtra(CV_AppLockService.LOCK_SERVICE_LASTTIME, System.currentTimeMillis());
                    intent.putExtra(CV_AppLockService.LOCK_SERVICE_LASTAPP, this.cv_pkgName);
                    sendBroadcast(intent);
                    CV_AppLockService.from = 1;
                    finish();
                    return;
                }
                for (EditText text : this.cv_pointList) {
                    text.setText("");
                }
                this.cv_final_string = "";
                this.cv_mUnlockFailTip.setText(getResources().getString(R.string.password_error_count));
                this.cv_mUnlockFailTip.setTextColor(getResources().getColor(R.color.text_red));
                this.cv_mUnlockFailTip.startAnimation(this.cv_mShakeAnim);
            }
        }
    }

    private void cv_deleteNumber() {
        if (this.cv_final_string.length() != 0) {
            this.cv_pointList.get(this.cv_final_string.length() - 1).setText("");
            StringBuffer stringBuffer = new StringBuffer(this.cv_final_string);
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            this.cv_final_string = stringBuffer.toString();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        CV_AppLockService.from = 0;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
        finish();
    }
}
