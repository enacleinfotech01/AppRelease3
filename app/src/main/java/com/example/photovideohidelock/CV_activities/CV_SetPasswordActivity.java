package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;

public class CV_SetPasswordActivity extends AppCompatActivity {
    ImageView cv_btn_submit;
    EditText cv_edt_confpass;
    EditText cv_edt_pass;
    EditText cv_edt_pass_old;
    LinearLayout cv_liner_conform_passoword;
    LinearLayout cv_liner_enter_passoword;
    LinearLayout cv_liner_old_pass;
    Context cv_mContext;
    ImageView cv_splash_ic;
    CV_TinyDB cv_tinyDB;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_set_password);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_splash_ic = (ImageView) findViewById(R.id.splash_ic);
        this.cv_edt_pass = (EditText) findViewById(R.id.edt_pass);
        this.cv_edt_confpass = (EditText) findViewById(R.id.edt_confpass);
        this.cv_btn_submit = (ImageView) findViewById(R.id.btn_submit);
        this.cv_edt_pass_old = (EditText) findViewById(R.id.edt_pass_old);
        this.cv_liner_old_pass = (LinearLayout) findViewById(R.id.liner_old_pass);
        this.cv_liner_conform_passoword = (LinearLayout) findViewById(R.id.liner_conform_passoword);
        this.cv_liner_enter_passoword = (LinearLayout) findViewById(R.id.liner_enter_passoword);
        this.cv_tinyDB = new CV_TinyDB(this.cv_mContext);
        String stringExtra = getIntent().getStringExtra("chengepassword");
        if (stringExtra != null && !stringExtra.isEmpty()) {
            this.cv_liner_old_pass.setVisibility(View.VISIBLE);
            this.cv_liner_conform_passoword.setVisibility(View.GONE);
            this.cv_liner_enter_passoword.setVisibility(View.GONE);
        }
        this.cv_btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_SetPasswordActivity.this.cv_liner_conform_passoword.getVisibility() == View.VISIBLE) {
                    String trim = CV_SetPasswordActivity.this.cv_edt_pass.getText().toString().trim();
                    String trim2 = CV_SetPasswordActivity.this.cv_edt_confpass.getText().toString().trim();
                    if (!trim.equals(trim2)) {
                        Toast.makeText(CV_SetPasswordActivity.this.cv_mContext, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    } else if (CV_SettingActivity.cv_change_real == 1) {
                        CV_SetPasswordActivity.this.cv_tinyDB.putString(CV_SetPasswordActivity.this.getResources().getString(R.string.password), trim2);
                        CV_SetPasswordActivity.this.finish();
                    } else if (trim.equalsIgnoreCase(trim2)) {
                        CV_SetPasswordActivity.this.cv_tinyDB.putString(CV_SetPasswordActivity.this.getResources().getString(R.string.password), trim2);
                        CV_SetPasswordActivity.this.cv_tinyDB.putInt(CV_SetPasswordActivity.this.getResources().getString(R.string.first_time), 1);
                        CV_SettingActivity.cv_change_ques = 0;
                        CV_SetPasswordActivity cV_SetPasswordActivity = CV_SetPasswordActivity.this;
                        cV_SetPasswordActivity.startActivity(new Intent(cV_SetPasswordActivity.cv_mContext, CV_SetQuestionActivity.class));
                        CV_SetPasswordActivity.this.finish();
                    }
                } else if (CV_SetPasswordActivity.this.cv_liner_old_pass.getVisibility() == View.VISIBLE) {
                    if (CV_SetPasswordActivity.this.cv_edt_pass_old.getText().toString().trim().equals(CV_SetPasswordActivity.this.cv_tinyDB.getString(CV_SetPasswordActivity.this.getResources().getString(R.string.password)))) {
                        CV_SetPasswordActivity.this.cv_liner_conform_passoword.setVisibility(View.VISIBLE);
                        CV_SetPasswordActivity.this.cv_liner_enter_passoword.setVisibility(View.VISIBLE);
                        CV_SetPasswordActivity.this.cv_liner_old_pass.setVisibility(View.GONE);
                        CV_SetPasswordActivity.this.cv_edt_pass.setText("");
                        CV_SetPasswordActivity.this.cv_edt_confpass.setText("");
                        CV_SetPasswordActivity.this.cv_edt_pass_old.setText("");
                        return;
                    }
                    Toast.makeText(CV_SetPasswordActivity.this.cv_mContext, "Password enter valid password match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_splash_ic, 827, 589);
        CV_HelperResizer.setSize(this.cv_edt_pass, 980, 120);
        CV_HelperResizer.setSize(this.cv_edt_pass_old, 980, 120);
        CV_HelperResizer.setSize(this.cv_edt_confpass, 980, 120);
        CV_HelperResizer.setSize(this.cv_btn_submit, 980, 120);
        CV_HelperResizer.setMargin(this.cv_edt_pass, 0, 30, 0, 0);
        CV_HelperResizer.setMargin(this.cv_edt_confpass, 0, 30, 0, 0);
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (CV_SettingActivity.cv_change_real != 1) {
            this.cv_tinyDB.putInt(getResources().getString(R.string.first_time), 0);
        }
        finish();
    }
}
