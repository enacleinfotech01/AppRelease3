package com.example.photovideohidelock.CV_activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import com.example.photovideohidelock.CV_interfaces.CV_CalculatorContract;
import com.example.photovideohidelock.CV_interfaces.CV_CalculatorPresenter;
import com.example.photovideohidelock.CV_services.CV_AppLockService;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;

public class CV_CalculatorActivity extends AppCompatActivity implements CV_CalculatorContract.View {
    public static boolean cv_mSqroot;
    private int count = 0;
    ImageView cv_button0;
    ImageView cv_button1;
    ImageView cv_button2;
    ImageView cv_button3;
    ImageView cv_button4;
    ImageView cv_button5;
    ImageView cv_button6;
    ImageView cv_button7;
    ImageView cv_button8;
    ImageView cv_button9;
    ImageView cv_buttonC;
    ImageView cv_buttonDevide;
    ImageView cv_buttonEqual;
    ImageView cv_buttonMinus;
    ImageView cv_buttonMod;
    ImageView cv_buttonPlus;
    ImageView cv_buttonPoint;
    ImageView cv_buttonRoot;
    ImageView cv_buttonSqroot;
    ImageView cv_buttonStar;
    ImageView cv_ic_recovery;
    /* access modifiers changed from: private */
    public CV_CalculatorPresenter cv_mCalculatorPresenter;
    Context cv_mContext;
    CV_TinyDB cv_tinyDB;
    TextView cv_txtScreen;
    TextView cv_txtanswer;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_calculator);
        this.cv_mContext = this;
//        new AdAdmob(this).FullscreenAd(this);
        CV_HelperResizer.putvalueinsharedprefrence(this.cv_mContext, CV_HelperResizer.CHECK_EXIT, false);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_mCalculatorPresenter = new CV_CalculatorPresenter(this);
        this.cv_ic_recovery = (ImageView) findViewById(R.id.ic_recovery);
        this.cv_txtScreen = (TextView) findViewById(R.id.txtScreen);
        this.cv_txtanswer = (TextView) findViewById(R.id.txtanswer);
        this.cv_buttonMod = (ImageView) findViewById(R.id.buttonMod);
        this.cv_buttonRoot = (ImageView) findViewById(R.id.buttonRoot);
        this.cv_buttonSqroot = (ImageView) findViewById(R.id.buttonSqroot);
        this.cv_buttonC = (ImageView) findViewById(R.id.buttonC);
        this.cv_button1 = (ImageView) findViewById(R.id.button1);
        this.cv_button2 = (ImageView) findViewById(R.id.button2);
        this.cv_button3 = (ImageView) findViewById(R.id.button3);
        this.cv_buttonPlus = (ImageView) findViewById(R.id.buttonPlus);
        this.cv_button4 = (ImageView) findViewById(R.id.button4);
        this.cv_button5 = (ImageView) findViewById(R.id.button5);
        this.cv_button6 = (ImageView) findViewById(R.id.button6);
        this.cv_buttonMinus = (ImageView) findViewById(R.id.buttonMinus);
        this.cv_button7 = (ImageView) findViewById(R.id.button7);
        this.cv_button8 = (ImageView) findViewById(R.id.button8);
        this.cv_button9 = (ImageView) findViewById(R.id.button9);
        this.cv_buttonStar = (ImageView) findViewById(R.id.buttonStar);
        this.cv_button0 = (ImageView) findViewById(R.id.button0);
        this.cv_buttonPoint = (ImageView) findViewById(R.id.buttonPoint);
        this.cv_buttonEqual = (ImageView) findViewById(R.id.buttonEqual);
        this.cv_buttonDevide = (ImageView) findViewById(R.id.buttonDevide);
        this.cv_tinyDB = new CV_TinyDB(this.cv_mContext);
        this.cv_button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("1");
            }
        });
        this.cv_button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd(ExifInterface.GPS_MEASUREMENT_2D);
            }
        });
        this.cv_button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd(ExifInterface.GPS_MEASUREMENT_3D);
            }
        });
        this.cv_button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("4");
            }
        });
        this.cv_button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("5");
            }
        });
        this.cv_button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("6");
            }
        });
        this.cv_button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("7");
            }
        });
        this.cv_button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("8");
            }
        });
        this.cv_button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("9");
            }
        });
        this.cv_button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("0");
            }
        });
        this.cv_buttonPoint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd(".");
            }
        });
        this.cv_buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_mCalculatorPresenter.onClearExpression();
            }
        });
        this.cv_buttonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("+");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_buttonMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("-");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_buttonStar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("*");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_buttonDevide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("/");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_buttonMod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd(CV_CalculatorPresenter.PERCENTAGE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_buttonRoot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("^");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_buttonSqroot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.cv_mSqroot = true;
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onOperatorAdd("√");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CV_CalculatorActivity.this.cv_txtScreen.getText().toString().trim().equalsIgnoreCase("")) {
                    try {
                        CV_CalculatorActivity.this.cv_mCalculatorPresenter.onCalculateResult();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.cv_txtScreen.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().equalsIgnoreCase(CV_CalculatorActivity.this.cv_tinyDB.getString(CV_CalculatorActivity.this.getResources().getString(R.string.password)))) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            CV_CalculatorActivity.this.cv_ShowNextDialog();
                            CV_CalculatorActivity.this.cv_mCalculatorPresenter.onClearExpression();
                        }
                    }, 200);
                }
            }
        });
        this.cv_ic_recovery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_CalculatorActivity.this.cv_ShowRecoveryDialog();
            }
        });
        if (isMyServiceRunning(CV_AppLockService.class)) {
            startService(new Intent(this.cv_mContext, CV_AppLockService.class));
        }
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_recovery, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_button0, 230, 180);
        CV_HelperResizer.setSize(this.cv_button1, 230, 180);
        CV_HelperResizer.setSize(this.cv_button2, 230, 180);
        CV_HelperResizer.setSize(this.cv_button3, 230, 180);
        CV_HelperResizer.setSize(this.cv_button4, 230, 180);
        CV_HelperResizer.setSize(this.cv_button5, 230, 180);
        CV_HelperResizer.setSize(this.cv_button6, 230, 180);
        CV_HelperResizer.setSize(this.cv_button7, 230, 180);
        CV_HelperResizer.setSize(this.cv_button8, 230, 180);
        CV_HelperResizer.setSize(this.cv_button9, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonPlus, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonMinus, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonStar, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonSqroot, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonDevide, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonMod, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonC, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonEqual, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonPoint, 230, 180);
        CV_HelperResizer.setSize(this.cv_buttonRoot, 230, 180);
    }

    /* access modifiers changed from: private */
    public void cv_ShowRecoveryDialog() {
        final Dialog dialog = new Dialog(this.cv_mContext, R.style.AppTheme_FullScreenDialog);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_recover_password_dialog_layout);
        dialog.show();
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ic_back);
        ImageView imageView2 = (ImageView) dialog.findViewById(R.id.ic_reset);
        final EditText editText = (EditText) dialog.findViewById(R.id.edt_answer);
        ((TextView) dialog.findViewById(R.id.txt_ques)).setText(this.cv_tinyDB.getString(getResources().getString(R.string.mainque)));
        CV_HelperResizer.setSize(imageView, 60, 60, true);
        CV_HelperResizer.setSize(editText, 980, 130);
        CV_HelperResizer.setSize(imageView2, 980, 110);
        CV_HelperResizer.setMargin(editText, 0, 90, 0, 0);
        CV_HelperResizer.setMargin(imageView2, 0, 100, 0, 0);
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (editText.getText().toString().trim().equalsIgnoreCase(CV_CalculatorActivity.this.cv_tinyDB.getString(CV_CalculatorActivity.this.getResources().getString(R.string.answer)))) {
                    CV_SettingActivity.cv_change_real = 1;
                    CV_CalculatorActivity cV_CalculatorActivity = CV_CalculatorActivity.this;
                    cV_CalculatorActivity.startActivity(new Intent(cV_CalculatorActivity.cv_mContext, CV_SetPasswordActivity.class));
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 700);
                    return;
                }
                Toast.makeText(CV_CalculatorActivity.this.cv_mContext, "Please Enter Correct Answer", Toast.LENGTH_LONG).show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void cv_ShowNextDialog() {
        final Dialog dialog = new Dialog(this.cv_mContext, R.style.AppTheme_FullScreenDialog);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_loading_dialog_layout);
        dialog.show();
        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
        CV_HelperResizer.setSize((ImageView) dialog.findViewById(R.id.splash_ic), 827, 589);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                final ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, progressBar.getMax()});
                ofInt.setDuration(2000);
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        progressBar.setProgress(((Integer) ofInt.getAnimatedValue()).intValue());
                    }
                });
                ofInt.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        CV_CalculatorActivity.this.startActivity(new Intent(CV_CalculatorActivity.this.cv_mContext, CV_VaultOptionMainActivity.class));
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                CV_CalculatorActivity.this.cv_txtScreen.setText("");
                                CV_CalculatorActivity.this.cv_txtanswer.setText("");
                                dialog.dismiss();
                            }
                        }, 500);
                    }
                });
                ofInt.start();
            }
        }, 2000);
    }

    private boolean isMyServiceRunning(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this.cv_mContext, CV_ExitActivity.class));
    }

    public void showResult(String str) {
        this.cv_txtanswer.setText(str);
    }

    public void updateCurrentExpression(String str) {
        this.cv_txtScreen.setText(str);
    }

    public void showInvalidExpressionMessage() {
        Toast.makeText(this.cv_mContext, getString(R.string.invalid_expression_message), Toast.LENGTH_LONG).show();
    }
}
