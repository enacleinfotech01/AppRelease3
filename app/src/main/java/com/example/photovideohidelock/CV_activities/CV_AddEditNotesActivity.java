package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_hidingUtils.CV_MyDatabaseHelper;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;
import com.google.firebase.messaging.ServiceStarter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CV_AddEditNotesActivity extends AppCompatActivity {
    Boolean cv_checkpauseoperation = false;
    EditText cv_edt_notes;
    EditText cv_edt_title;
    ImageView cv_ic_back;
    ImageView cv_ic_save;
    String cv_id;
    Context cv_mContext;
    CV_MyDatabaseHelper cv_myDatabaseHelper;
    String cv_notes;
    String cv_title;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_add_edit_notes);
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_edt_title = (EditText) findViewById(R.id.edt_title);
        this.cv_edt_notes = (EditText) findViewById(R.id.edt_notes);
        this.cv_ic_save = (ImageView) findViewById(R.id.ic_save);
        this.cv_myDatabaseHelper = new CV_MyDatabaseHelper(this.cv_mContext);
        if (CV_AddNotesToHideActivity.cv_add_edit_notes == 1) {
//            this.cv_id = getIntent().getStringExtra(OSOutcomeConstants.OUTCOME_ID);
//            this.cv_title = getIntent().getStringExtra(OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE);
            this.cv_notes = getIntent().getStringExtra("notes");
            this.cv_edt_title.setText(this.cv_title);
            this.cv_edt_notes.setText(this.cv_notes);
        }
        this.cv_ic_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddEditNotesActivity.this.cv_checkpauseoperation = true;
                if (CV_AddEditNotesActivity.this.cv_edt_title.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddEditNotesActivity.this.cv_mContext, "Please Enter Title", Toast.LENGTH_SHORT).show();
                } else if (CV_AddEditNotesActivity.this.cv_edt_notes.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddEditNotesActivity.this.cv_mContext, "Please Enter Note", Toast.LENGTH_SHORT).show();
                } else {
                    if (CV_AddNotesToHideActivity.cv_add_edit_notes == 1) {
                        CV_AddEditNotesActivity.this.cv_myDatabaseHelper.updateNoteData(CV_AddEditNotesActivity.this.cv_id, CV_AddEditNotesActivity.this.cv_edt_title.getText().toString().trim(), CV_AddEditNotesActivity.this.cv_edt_notes.getText().toString().trim());
                        Toast.makeText(CV_AddEditNotesActivity.this.cv_mContext, "Note Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        String format = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                        String format2 = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                        CV_MyHelper.hideKeyboardFrom(CV_AddEditNotesActivity.this.cv_mContext, CV_AddEditNotesActivity.this.cv_edt_notes);
                        CV_AddEditNotesActivity.this.cv_myDatabaseHelper.insertNotesData(String.valueOf(System.currentTimeMillis()), CV_AddEditNotesActivity.this.cv_edt_title.getText().toString().trim(), CV_AddEditNotesActivity.this.cv_edt_notes.getText().toString().trim(), format, format2);
                        Toast.makeText(CV_AddEditNotesActivity.this.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    }
                    CV_AddEditNotesActivity.this.cv_edt_title.setText("");
                    CV_AddEditNotesActivity.this.cv_edt_notes.setText("");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            CV_AddEditNotesActivity.this.finish();
                        }
                    }, 150);
                }
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddEditNotesActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_edt_title, 980, 100);
        CV_HelperResizer.setSize(this.cv_edt_notes, 980, ServiceStarter.ERROR_UNKNOWN);
        CV_HelperResizer.setSize(this.cv_ic_save, 880, 110);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setMargin(this.cv_edt_title, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(this.cv_edt_notes, 0, 15, 0, 0);
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
