package com.example.photovideohidelock.CV_activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;

public class CV_SetQuestionActivity extends AppCompatActivity {
    ImageView cv_btn_save;
    EditText cv_edt_answer;
    ImageView cv_ic_down;
    LinearLayout cv_lay_ques;
    Context cv_mContext;
    ImageView cv_splash_ic;
    CV_TinyDB cv_tinyDB;
    TextView cv_tv_ques;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_set_question);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_lay_ques = (LinearLayout) findViewById(R.id.lay_ques);
        this.cv_tv_ques = (TextView) findViewById(R.id.tv_ques);
        this.cv_ic_down = (ImageView) findViewById(R.id.ic_down);
        this.cv_btn_save = (ImageView) findViewById(R.id.btn_submit);
        this.cv_edt_answer = (EditText) findViewById(R.id.edt_answer);
        this.cv_splash_ic = (ImageView) findViewById(R.id.splash_ic);
        this.cv_tinyDB = new CV_TinyDB(this.cv_mContext);
        if (CV_SettingActivity.cv_change_ques == 1) {
            this.cv_edt_answer.setText(this.cv_tinyDB.getString(getResources().getString(R.string.answer)));
            this.cv_tv_ques.setText(this.cv_tinyDB.getString(getResources().getString(R.string.mainque)));
        }
        this.cv_lay_ques.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SetQuestionActivity.this.cv_SelectQuestionDialog();
            }
        });
        this.cv_btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_SetQuestionActivity.this.cv_edt_answer.getText().toString().trim().equalsIgnoreCase("")) {
                    CV_SetQuestionActivity.this.cv_edt_answer.setError("Please Enter Answer First");
                    return;
                }
                CV_SetQuestionActivity.this.cv_tinyDB.putString(CV_SetQuestionActivity.this.getResources().getString(R.string.answer), CV_SetQuestionActivity.this.cv_edt_answer.getText().toString().trim());
                CV_SetQuestionActivity.this.cv_tinyDB.putString(CV_SetQuestionActivity.this.getResources().getString(R.string.mainque), CV_SetQuestionActivity.this.cv_tv_ques.getText().toString().trim());
                if (CV_SettingActivity.cv_change_ques != 1) {
                    CV_SetQuestionActivity.this.cv_tinyDB.putInt(CV_SetQuestionActivity.this.getResources().getString(R.string.for_question), 1);
                    CV_SetQuestionActivity cV_SetQuestionActivity = CV_SetQuestionActivity.this;
                    cV_SetQuestionActivity.startActivity(new Intent(cV_SetQuestionActivity.cv_mContext, CV_CalculatorActivity.class));
                }
                CV_SetQuestionActivity.this.finish();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_down, 80, 80, true);
        CV_HelperResizer.setSize(this.cv_splash_ic, 827, 589);
        CV_HelperResizer.setSize(this.cv_lay_ques, 980, 120);
        CV_HelperResizer.setSize(this.cv_edt_answer, 980, 120);
        CV_HelperResizer.setSize(this.cv_btn_save, 980, 120);
        CV_HelperResizer.setMargin(this.cv_lay_ques, 0, 30, 0, 0);
        CV_HelperResizer.setMargin(this.cv_edt_answer, 0, 30, 0, 0);
    }

    /* access modifiers changed from: private */
    public void cv_SelectQuestionDialog() {
        Dialog dialog = new Dialog(this.cv_mContext);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_question_dialog_layout);
        dialog.show();
        CV_HelperResizer.setSize(dialog.findViewById(R.id.popup), 800, 834);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.line_first);
        LinearLayout linearLayout2 = (LinearLayout) dialog.findViewById(R.id.line_second);
        LinearLayout linearLayout3 = (LinearLayout) dialog.findViewById(R.id.line_third);
        LinearLayout linearLayout4 = (LinearLayout) dialog.findViewById(R.id.line_fourth);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.iv_first);
        ImageView imageView2 = (ImageView) dialog.findViewById(R.id.iv_second);
        ImageView imageView3 = (ImageView) dialog.findViewById(R.id.iv_third);
        ImageView imageView4 = (ImageView) dialog.findViewById(R.id.iv_fourth);
        ImageView imageView5 = (ImageView) dialog.findViewById(R.id.iv_fifth);
        ImageView imageView6 = (ImageView) dialog.findViewById(R.id.iv_six);
        ImageView imageView7 = (ImageView) dialog.findViewById(R.id.iv_seven);
        LinearLayout linearLayout5 = (LinearLayout) dialog.findViewById(R.id.line_seven);
        LinearLayout linearLayout6 = (LinearLayout) dialog.findViewById(R.id.line_six);
        CV_HelperResizer.setSize(imageView, 60, 60, true);
        CV_HelperResizer.setSize(imageView2, 60, 60, true);
        CV_HelperResizer.setSize(imageView3, 60, 60, true);
        CV_HelperResizer.setSize(imageView4, 60, 60, true);
        CV_HelperResizer.setSize(imageView5, 60, 60, true);
        CV_HelperResizer.setSize(imageView6, 60, 60, true);
        CV_HelperResizer.setSize(imageView7, 60, 60, true);
        LinearLayout linearLayout7 = (LinearLayout) dialog.findViewById(R.id.line_fifth);
        if (this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("Who is your best friend?") || this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("")) {
            imageView.setImageResource(R.drawable.cv_select);
            imageView2.setImageResource(R.drawable.cv_deselect);
            imageView3.setImageResource(R.drawable.cv_deselect);
            imageView4.setImageResource(R.drawable.cv_deselect);
            imageView5.setImageResource(R.drawable.cv_deselect);
            imageView6.setImageResource(R.drawable.cv_deselect);
            imageView7.setImageResource(R.drawable.cv_deselect);
        } else if (this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("What is your birth month?")) {
            imageView2.setImageResource(R.drawable.cv_select);
            imageView.setImageResource(R.drawable.cv_deselect);
            imageView3.setImageResource(R.drawable.cv_deselect);
            imageView4.setImageResource(R.drawable.cv_deselect);
            imageView5.setImageResource(R.drawable.cv_deselect);
            imageView6.setImageResource(R.drawable.cv_deselect);
            imageView7.setImageResource(R.drawable.cv_deselect);
        } else if (this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("Who is your favourite cricketer?")) {
            imageView3.setImageResource(R.drawable.cv_select);
            imageView2.setImageResource(R.drawable.cv_deselect);
            imageView.setImageResource(R.drawable.cv_deselect);
            imageView4.setImageResource(R.drawable.cv_deselect);
            imageView5.setImageResource(R.drawable.cv_deselect);
            imageView6.setImageResource(R.drawable.cv_deselect);
            imageView7.setImageResource(R.drawable.cv_deselect);
        } else if (this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("What was your first car?")) {
            imageView4.setImageResource(R.drawable.cv_select);
            imageView3.setImageResource(R.drawable.cv_deselect);
            imageView2.setImageResource(R.drawable.cv_deselect);
            imageView.setImageResource(R.drawable.cv_deselect);
            imageView5.setImageResource(R.drawable.cv_deselect);
            imageView6.setImageResource(R.drawable.cv_deselect);
            imageView7.setImageResource(R.drawable.cv_deselect);
        } else if (this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("Which is your favourite song?")) {
            imageView5.setImageResource(R.drawable.cv_select);
            imageView4.setImageResource(R.drawable.cv_deselect);
            imageView3.setImageResource(R.drawable.cv_deselect);
            imageView2.setImageResource(R.drawable.cv_deselect);
            imageView.setImageResource(R.drawable.cv_deselect);
            imageView6.setImageResource(R.drawable.cv_deselect);
            imageView7.setImageResource(R.drawable.cv_deselect);
        } else if (this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("In what town was your first job?")) {
            imageView6.setImageResource(R.drawable.cv_select);
            imageView5.setImageResource(R.drawable.cv_deselect);
            imageView4.setImageResource(R.drawable.cv_deselect);
            imageView3.setImageResource(R.drawable.cv_deselect);
            imageView2.setImageResource(R.drawable.cv_deselect);
            imageView.setImageResource(R.drawable.cv_deselect);
            imageView7.setImageResource(R.drawable.cv_deselect);
        } else if (this.cv_tinyDB.getString(getResources().getString(R.string.mainque)).equalsIgnoreCase("Which is your favourite movie?")) {
            imageView7.setImageResource(R.drawable.cv_select);
            imageView6.setImageResource(R.drawable.cv_deselect);
            imageView5.setImageResource(R.drawable.cv_deselect);
            imageView4.setImageResource(R.drawable.cv_deselect);
            imageView3.setImageResource(R.drawable.cv_deselect);
            imageView2.setImageResource(R.drawable.cv_deselect);
            imageView.setImageResource(R.drawable.cv_deselect);
        }
        ImageView imageView8 = imageView7;
        ImageView imageView9 = imageView6;
        ImageView imageView10 = imageView5;
        final Dialog dialog2 = dialog;
        ImageView imageView11 = imageView4;
        final ImageView imageView12 = imageView;
        ImageView imageView13 = imageView3;
        final ImageView imageView14 = imageView2;
        ImageView imageView15 = imageView2;
        final ImageView imageView16 = imageView13;
        ImageView imageView17 = imageView;
        final ImageView imageView18 = imageView11;
        final ImageView imageView19 = imageView10;
        LinearLayout linearLayout8 = linearLayout6;
        final ImageView imageView20 = imageView9;
        View.OnClickListener r0 = null;
        View.OnClickListener r10 = r0;
        final ImageView imageView21 = imageView8;
         r0 = new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
                CV_SetQuestionActivity.this.cv_tv_ques.setText(CV_SetQuestionActivity.this.getResources().getString(R.string.txt_bst_friend));
                imageView12.setImageResource(R.drawable.cv_select);
                imageView14.setImageResource(R.drawable.cv_deselect);
                imageView16.setImageResource(R.drawable.cv_deselect);
                imageView18.setImageResource(R.drawable.cv_deselect);
                imageView19.setImageResource(R.drawable.cv_deselect);
                imageView20.setImageResource(R.drawable.cv_deselect);
                imageView21.setImageResource(R.drawable.cv_deselect);
            }
        };
        linearLayout.setOnClickListener(r10);
        final ImageView imageView22 = imageView15;
        final ImageView imageView23 = imageView17;
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
                CV_SetQuestionActivity.this.cv_tv_ques.setText(CV_SetQuestionActivity.this.getResources().getString(R.string.txt_birth_month));
                imageView22.setImageResource(R.drawable.cv_select);
                imageView23.setImageResource(R.drawable.cv_deselect);
                imageView16.setImageResource(R.drawable.cv_deselect);
                imageView18.setImageResource(R.drawable.cv_deselect);
                imageView19.setImageResource(R.drawable.cv_deselect);
                imageView20.setImageResource(R.drawable.cv_deselect);
                imageView21.setImageResource(R.drawable.cv_deselect);
            }
        });
        final ImageView imageView24 = imageView13;
        final ImageView imageView25 = imageView15;
        final ImageView imageView26 = imageView17;
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
                CV_SetQuestionActivity.this.cv_tv_ques.setText(CV_SetQuestionActivity.this.getResources().getString(R.string.txt_fav_cricketer));
                imageView24.setImageResource(R.drawable.cv_select);
                imageView25.setImageResource(R.drawable.cv_deselect);
                imageView26.setImageResource(R.drawable.cv_deselect);
                imageView18.setImageResource(R.drawable.cv_deselect);
                imageView19.setImageResource(R.drawable.cv_deselect);
                imageView20.setImageResource(R.drawable.cv_deselect);
                imageView21.setImageResource(R.drawable.cv_deselect);
            }
        });
        final ImageView imageView27 = imageView11;
        final ImageView imageView28 = imageView13;
        final ImageView imageView29 = imageView15;
        final ImageView imageView30 = imageView17;
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
                CV_SetQuestionActivity.this.cv_tv_ques.setText(CV_SetQuestionActivity.this.getResources().getString(R.string.txt_first_car));
                imageView27.setImageResource(R.drawable.cv_select);
                imageView28.setImageResource(R.drawable.cv_deselect);
                imageView29.setImageResource(R.drawable.cv_deselect);
                imageView30.setImageResource(R.drawable.cv_deselect);
                imageView19.setImageResource(R.drawable.cv_deselect);
                imageView20.setImageResource(R.drawable.cv_deselect);
                imageView21.setImageResource(R.drawable.cv_deselect);
            }
        });
        final ImageView imageView31 = imageView10;
        final ImageView imageView32 = imageView11;
        final ImageView imageView33 = imageView13;
        final ImageView imageView34 = imageView15;
        final ImageView imageView35 = imageView17;
        linearLayout7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
                CV_SetQuestionActivity.this.cv_tv_ques.setText(CV_SetQuestionActivity.this.getResources().getString(R.string.txt_fav_song));
                imageView31.setImageResource(R.drawable.cv_select);
                imageView32.setImageResource(R.drawable.cv_deselect);
                imageView33.setImageResource(R.drawable.cv_deselect);
                imageView34.setImageResource(R.drawable.cv_deselect);
                imageView35.setImageResource(R.drawable.cv_deselect);
                imageView20.setImageResource(R.drawable.cv_deselect);
                imageView21.setImageResource(R.drawable.cv_deselect);
            }
        });
        final ImageView imageView36 = imageView9;
        final ImageView imageView37 = imageView10;
        final ImageView imageView38 = imageView11;
        final ImageView imageView39 = imageView13;
        final ImageView imageView40 = imageView15;
        final ImageView imageView41 = imageView17;
        linearLayout6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
                CV_SetQuestionActivity.this.cv_tv_ques.setText(CV_SetQuestionActivity.this.getResources().getString(R.string.txt_first_job));
                imageView36.setImageResource(R.drawable.cv_select);
                imageView37.setImageResource(R.drawable.cv_deselect);
                imageView38.setImageResource(R.drawable.cv_deselect);
                imageView39.setImageResource(R.drawable.cv_deselect);
                imageView40.setImageResource(R.drawable.cv_deselect);
                imageView41.setImageResource(R.drawable.cv_deselect);
                imageView21.setImageResource(R.drawable.cv_deselect);
            }
        });
        final ImageView imageView42 = imageView8;
        final ImageView imageView43 = imageView9;
        final ImageView imageView44 = imageView10;
        final ImageView imageView45 = imageView11;
        final ImageView imageView46 = imageView13;
        final ImageView imageView47 = imageView15;
        final ImageView imageView48 = imageView17;
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.dismiss();
                CV_SetQuestionActivity.this.cv_tv_ques.setText(CV_SetQuestionActivity.this.getResources().getString(R.string.txt_fav_movie));
                imageView42.setImageResource(R.drawable.cv_select);
                imageView43.setImageResource(R.drawable.cv_deselect);
                imageView44.setImageResource(R.drawable.cv_deselect);
                imageView45.setImageResource(R.drawable.cv_deselect);
                imageView46.setImageResource(R.drawable.cv_deselect);
                imageView47.setImageResource(R.drawable.cv_deselect);
                imageView48.setImageResource(R.drawable.cv_deselect);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                CV_SetQuestionActivity.this.cv_tinyDB.putString(CV_SetQuestionActivity.this.getResources().getString(R.string.mainque), CV_SetQuestionActivity.this.cv_tv_ques.getText().toString().trim());
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (CV_SettingActivity.cv_change_ques != 1) {
            this.cv_tinyDB.putInt(getResources().getString(R.string.for_question), 0);
        }
        finish();
    }
}
