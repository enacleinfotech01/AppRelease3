package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_hidingUtils.CV_MyDatabaseHelper;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.util.regex.Pattern;

public class CV_AddEditContactActivity extends AppCompatActivity {
    LinearLayout cv_bg_lay;
    Boolean cv_checkpauseoperation = false;
    EditText cv_edt_name;
    EditText cv_edt_number;
    ImageView cv_ic_back;
    ImageView cv_ic_save;
    String cv_id;
    LinearLayout cv_lay_name;
    LinearLayout cv_lay_number;
    Context cv_mContext;
    CV_MyDatabaseHelper cv_myDatabaseHelper;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_add_edit_contact);
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_bg_lay = (LinearLayout) findViewById(R.id.bg_lay);
        this.cv_lay_name = (LinearLayout) findViewById(R.id.lay_name);
        this.cv_edt_name = (EditText) findViewById(R.id.edt_name);
        this.cv_lay_number = (LinearLayout) findViewById(R.id.lay_number);
        this.cv_edt_number = (EditText) findViewById(R.id.edt_number);
        this.cv_ic_save = (ImageView) findViewById(R.id.ic_save);
        this.cv_myDatabaseHelper = new CV_MyDatabaseHelper(this.cv_mContext);
        if (CV_AddContactToHideActivity.cv_add_edit == 1) {
//            this.cv_id = getIntent().getStringExtra(OSOutcomeConstants.OUTCOME_ID);
            String stringExtra = getIntent().getStringExtra("name");
            String stringExtra2 = getIntent().getStringExtra("number");
            this.cv_edt_name.setText(stringExtra);
            this.cv_edt_number.setText(stringExtra2);
            this.cv_ic_save.setImageResource(R.drawable.cv_edit_state_pressed);
        }
        this.cv_ic_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddEditContactActivity.this.cv_checkpauseoperation = true;
                if (CV_AddEditContactActivity.this.cv_edt_name.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddEditContactActivity.this.cv_mContext, "Please Enter Name First", Toast.LENGTH_LONG).show();
                    return;
                }
                CV_AddEditContactActivity cV_AddEditContactActivity = CV_AddEditContactActivity.this;
                if (!cV_AddEditContactActivity.isValidMobile(cV_AddEditContactActivity.cv_edt_number.getText().toString().trim())) {
                    Toast.makeText(CV_AddEditContactActivity.this.cv_mContext, "Please Enter Valid Number", Toast.LENGTH_LONG).show();
                    return;
                }
                if (CV_AddContactToHideActivity.cv_add_edit == 0) {
                    CV_AddEditContactActivity.this.cv_myDatabaseHelper.insertData(String.valueOf(System.currentTimeMillis()), CV_AddEditContactActivity.this.cv_edt_name.getText().toString().trim(), CV_AddEditContactActivity.this.cv_edt_number.getText().toString().trim());
                    Toast.makeText(CV_AddEditContactActivity.this.cv_mContext, "Contact Saved Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    CV_AddEditContactActivity.this.cv_myDatabaseHelper.updateData(CV_AddEditContactActivity.this.cv_id, CV_AddEditContactActivity.this.cv_edt_name.getText().toString().trim(), CV_AddEditContactActivity.this.cv_edt_number.getText().toString().trim());
                    Toast.makeText(CV_AddEditContactActivity.this.cv_mContext, "Contact Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                CV_AddEditContactActivity.this.cv_edt_name.setText("");
                CV_AddEditContactActivity.this.cv_edt_number.setText("");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        CV_AddEditContactActivity.this.finish();
                    }
                }, 150);
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddEditContactActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_bg_lay, 980, 720);
        CV_HelperResizer.setSize(this.cv_edt_name, 880, 110);
        CV_HelperResizer.setSize(this.cv_edt_number, 880, 110);
        CV_HelperResizer.setSize(this.cv_ic_save, 880, 110);
        CV_HelperResizer.setWidth(this.cv_mContext, this.cv_lay_name, 880);
        CV_HelperResizer.setWidth(this.cv_mContext, this.cv_lay_number, 880);
        CV_HelperResizer.setMargin(this.cv_bg_lay, 0, 60, 0, 0);
        CV_HelperResizer.setMargin(this.cv_lay_name, 0, 40, 0, 0);
        CV_HelperResizer.setMargin(this.cv_lay_number, 0, 40, 0, 0);
        CV_HelperResizer.setMargin(this.cv_edt_name, 0, 20, 0, 0);
        CV_HelperResizer.setMargin(this.cv_edt_number, 0, 20, 0, 0);
    }

    /* access modifiers changed from: private */
    public boolean isValidMobile(String str) {
        return !Pattern.matches("[a-zA-Z]+", str) && str.length() == 10;
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
