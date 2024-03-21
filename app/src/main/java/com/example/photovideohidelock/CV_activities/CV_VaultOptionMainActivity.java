package com.example.photovideohidelock.CV_activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyDatabaseHelper;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_reciever.CV_DemoDeviceAdminReceiver;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;

public class CV_VaultOptionMainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE = 1;
    public static int cv_image_video;
    private FrameLayout cv_adContainerView;
    ImageView cv_btn_apps;
    ImageView cv_btn_backup;
    ImageView cv_btn_contact;
    ImageView cv_btn_notes;
    ImageView cv_btn_other;
    ImageView cv_btn_photos;
    ImageView cv_btn_safepass;
    ImageView cv_btn_videos;
    Boolean cv_check_pause_op = false;
    ComponentName cv_demoDeviceAdmin;
    DevicePolicyManager cv_devicePolicyManager;
    boolean cv_dialog_running;
    int cv_for_pause = 0;
    ImageView cv_ic_menu;
    Intent cv_intent;
    Context cv_mContext;
    CV_MyDatabaseHelper cv_myDatabaseHelper;
    String[] cv_permissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.MANAGE_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    CV_TinyDB cv_tinyDB;
    TextView cv_tv_count_apps;
    TextView cv_tv_count_backup;
    TextView cv_tv_count_contact;
    TextView cv_tv_count_notes;
    TextView cv_tv_count_other;
    TextView cv_tv_count_pass;
    TextView cv_tv_count_pic;
    TextView cv_tv_count_video;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_vault_option_main);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName = new ComponentName(this, CV_DemoDeviceAdminReceiver.class);
        this.cv_demoDeviceAdmin = componentName;
        if (!this.cv_devicePolicyManager.isAdminActive(componentName)) {
            cv_ShowAdminDialog();
        }
        this.cv_btn_videos = (ImageView) findViewById(R.id.btn_videos);
        this.cv_btn_photos = (ImageView) findViewById(R.id.btn_photos);
        this.cv_btn_contact = (ImageView) findViewById(R.id.btn_contact);
        this.cv_btn_safepass = (ImageView) findViewById(R.id.btn_safepass);
        this.cv_btn_other = (ImageView) findViewById(R.id.btn_other);
        this.cv_btn_backup = (ImageView) findViewById(R.id.btn_backup);
        this.cv_btn_notes = (ImageView) findViewById(R.id.btn_notes);
        this.cv_ic_menu = (ImageView) findViewById(R.id.ic_menu);
        this.cv_btn_apps = (ImageView) findViewById(R.id.btn_apps);
        this.cv_tv_count_video = (TextView) findViewById(R.id.tv_count_video);
        this.cv_tv_count_pic = (TextView) findViewById(R.id.tv_count_pic);
        this.cv_tv_count_contact = (TextView) findViewById(R.id.tv_count_contact);
        this.cv_tv_count_apps = (TextView) findViewById(R.id.tv_count_apps);
        this.cv_tv_count_pass = (TextView) findViewById(R.id.tv_count_pass);
        this.cv_tv_count_notes = (TextView) findViewById(R.id.tv_count_notes);
        this.cv_tv_count_other = (TextView) findViewById(R.id.tv_count_other);
        this.cv_tv_count_backup = (TextView) findViewById(R.id.tv_count_backup);
        this.cv_myDatabaseHelper = new CV_MyDatabaseHelper(this.cv_mContext);
        this.cv_tinyDB = new CV_TinyDB(this.cv_mContext);
        this.cv_btn_videos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.cv_image_video = 0;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                Intent intent = new Intent(CV_VaultOptionMainActivity.this, CV_AddPhotoVideoToHideActivity.class);
                CV_VaultOptionMainActivity cV_VaultOptionMainActivity = CV_VaultOptionMainActivity.this;
                cV_VaultOptionMainActivity.fun_permission(cV_VaultOptionMainActivity, intent);
            }
        });
        this.cv_btn_photos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.cv_image_video = 1;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                Intent intent = new Intent(CV_VaultOptionMainActivity.this, CV_AddPhotoVideoToHideActivity.class);
                CV_VaultOptionMainActivity cV_VaultOptionMainActivity = CV_VaultOptionMainActivity.this;
                cV_VaultOptionMainActivity.fun_permission(cV_VaultOptionMainActivity, intent);
            }
        });
        this.cv_btn_contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                CV_Adshow.showinterstitialAd(CV_VaultOptionMainActivity.this, new Intent(CV_VaultOptionMainActivity.this.cv_mContext, CV_AddContactToHideActivity.class));
            }
        });
        this.cv_btn_apps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                CV_Adshow.showinterstitialAd(CV_VaultOptionMainActivity.this, new Intent(CV_VaultOptionMainActivity.this.cv_mContext, CV_AppLockActivity.class));
            }
        });
        this.cv_btn_safepass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                CV_Adshow.showinterstitialAd(CV_VaultOptionMainActivity.this, new Intent(CV_VaultOptionMainActivity.this.cv_mContext, CV_SafePasswordOptionActivity.class));
            }
        });
        this.cv_btn_notes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                CV_Adshow.showinterstitialAd(CV_VaultOptionMainActivity.this, new Intent(CV_VaultOptionMainActivity.this.cv_mContext, CV_AddNotesToHideActivity.class));
            }
        });
        this.cv_btn_other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                CV_Adshow.showinterstitialAd(CV_VaultOptionMainActivity.this, new Intent(CV_VaultOptionMainActivity.this.cv_mContext, CV_AddOtherFilesToHideActivity.class));
            }
        });
        this.cv_btn_backup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                CV_Adshow.showinterstitialAd(CV_VaultOptionMainActivity.this, new Intent(CV_VaultOptionMainActivity.this.cv_mContext, CV_BackupRestoreActivity.class));
            }
        });
        this.cv_ic_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                CV_VaultOptionMainActivity.this.cv_for_pause = 2;
                CV_Adshow.showinterstitialAd(CV_VaultOptionMainActivity.this, new Intent(CV_VaultOptionMainActivity.this.cv_mContext, CV_SettingActivity.class));
            }
        });
    }

    private void cv_ShowAdminDialog() {
        final Dialog dialog = new Dialog(this.cv_mContext);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_device_admin_dialog_layout);
        dialog.show();
        this.cv_dialog_running = true;
        ImageView imageView = (ImageView) dialog.findViewById(R.id.btn_ok);
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(dialog.findViewById(R.id.popup), 800, 750, true);
        CV_HelperResizer.setSize(imageView, 200, 100, true);
        CV_HelperResizer.setMargin(imageView, 0, 0, 45, 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_VaultOptionMainActivity.this.cv_check_pause_op = true;
                dialog.dismiss();
                Intent intent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
                intent.putExtra("android.app.extra.DEVICE_ADMIN", CV_VaultOptionMainActivity.this.cv_demoDeviceAdmin);
                intent.putExtra("android.app.extra.ADD_EXPLANATION", "Log Explanation");
                CV_VaultOptionMainActivity.this.startActivityForResult(intent, 1);
                CV_VaultOptionMainActivity cV_VaultOptionMainActivity = CV_VaultOptionMainActivity.this;
                cV_VaultOptionMainActivity.cv_dialog_running = false;
                cV_VaultOptionMainActivity.cv_for_pause = 0;
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                CV_VaultOptionMainActivity cV_VaultOptionMainActivity = CV_VaultOptionMainActivity.this;
                cV_VaultOptionMainActivity.cv_for_pause = 0;
                cV_VaultOptionMainActivity.cv_dialog_running = false;
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            super.onActivityResult(i, i2, intent);
        } else {
            this.cv_for_pause = 2;
        }
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_menu, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_btn_videos, 465, 300);
        CV_HelperResizer.setSize(this.cv_btn_photos, 465, 300);
        CV_HelperResizer.setSize(this.cv_btn_contact, 465, 300);
        CV_HelperResizer.setSize(this.cv_btn_safepass, 465, 300);
        CV_HelperResizer.setSize(this.cv_btn_other, 465, 300);
        CV_HelperResizer.setSize(this.cv_btn_backup, 465, 300);
        CV_HelperResizer.setSize(this.cv_btn_notes, 465, 300);
        CV_HelperResizer.setSize(this.cv_btn_apps, 465, 300);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_check_pause_op = true;
        startActivity(new Intent(this, CV_ExitActivity.class));
    }

    /* access modifiers changed from: package-private */
    public void fun_permission(Activity activity, Intent intent) {
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                @SuppressLint("ResourceType") View findViewById = findViewById(16908290);
                Snackbar make = Snackbar.make(findViewById, (CharSequence) getString(R.string.app_name) + " required permissions to access all files.Please go to Setting and enable the 'all files access' then go back ", -2);
                ((Snackbar.SnackbarLayout) make.getView()).setMinimumHeight(150);
                make.setAction((CharSequence) "Settings", (View.OnClickListener) new CV_VaultOptionMainActivity$$ExternalSyntheticLambda0(this));
                make.show();
                return;
            }
            CV_Adshow.showinterstitialAd(this, intent);
        } else if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            CV_Adshow.showinterstitialAd(this, intent);
        } else {
            ActivityCompat.requestPermissions(this, this.cv_permissions, 101);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$fun_permission$0$com-example-photovideohidelock-CV_activities-CV_VaultOptionMainActivity  reason: not valid java name */
    public /* synthetic */ void m35lambda$fun_permission$0$comexamplephotovideohidelockCV_activitiesCV_VaultOptionMainActivity(View view) {
        try {
            startActivity(new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.parse("package:com.example.photovideohidelock")));
        } catch (Exception unused) {
            Intent intent = new Intent();
            intent.setAction("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION");
            startActivity(intent);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101 && iArr.length > 0 && iArr[0] == 0) {
            startActivity(this.cv_intent);
        }
    }

    public void onResume() {
        super.onResume();
        this.cv_check_pause_op = false;
        if (this.cv_for_pause == -1) {
            finish();
            return;
        }
        try {
            this.cv_myDatabaseHelper.CreateAllTables();
            long profilesCount = this.cv_myDatabaseHelper.getProfilesCount("contact");
            long profilesCount2 = this.cv_myDatabaseHelper.getProfilesCount("atm") + this.cv_myDatabaseHelper.getProfilesCount("bank") + this.cv_myDatabaseHelper.getProfilesCount("creditcard");
            long profilesCount3 = this.cv_myDatabaseHelper.getProfilesCount("notes");
            this.cv_tv_count_contact.setText("" + profilesCount);
            this.cv_tv_count_pass.setText("" + ((int) (profilesCount2 + this.cv_myDatabaseHelper.getProfilesCount("email") + this.cv_myDatabaseHelper.getProfilesCount("idcard") + this.cv_myDatabaseHelper.getProfilesCount("website") + this.cv_myDatabaseHelper.getProfilesCount("ecommerce") + this.cv_myDatabaseHelper.getProfilesCount(NotificationCompat.CATEGORY_SOCIAL) + this.cv_myDatabaseHelper.getProfilesCount("business") + this.cv_myDatabaseHelper.getProfilesCount("general"))));
            this.cv_tv_count_notes.setText("" + profilesCount3);
        } catch (Exception e) {
            e.printStackTrace();
            this.cv_tv_count_contact.setText("0");
            this.cv_tv_count_pass.setText("0");
            this.cv_tv_count_notes.setText("0");
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        CV_MyHelper.getVideoFolder(this.cv_mContext);
        CV_MyHelper.getImagesFolder(this.cv_mContext);
        CV_MyHelper.getOthersFolder(this.cv_mContext);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault/image");
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (file.exists()) {
            for (File file2 : listFiles) {
                if (new CV_HelperResizer.ImageFileFilter().accept(file2)) {
                    arrayList2.add(file2.getAbsolutePath());
                }
            }
        }
        if (file.exists()) {
            for (File file3 : listFiles) {
                if (new CV_HelperResizer.VideoFILTER().accept(file3)) {
                    arrayList.add(file3.getAbsolutePath());
                }
            }
        }
        File file4 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault/Others");
        if (!file4.exists()) {
            file4.mkdirs();
        }
        File[] listFiles2 = file4.listFiles();
        if (file.exists()) {
            for (File absolutePath : listFiles2) {
                arrayList3.add(absolutePath.getAbsolutePath());
            }
        }
        if (!this.cv_tinyDB.getString(getResources().getString(R.string.folder_name)).equalsIgnoreCase("")) {
            String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + this.cv_tinyDB.getString(getResources().getString(R.string.folder_name));
            File file5 = new File(str);
            if (file5.exists()) {
                File[] listFiles3 = file5.listFiles();
                if (listFiles3 != null) {
                    for (int i = 0; i < listFiles3.length; i++) {
                        if (!listFiles3[i].getAbsolutePath().equals(str + "/" + getPackageName())) {
                            arrayList4.add(listFiles3[i].getAbsolutePath());
                        }
                    }
                    this.cv_tv_count_backup.setText("" + arrayList4.size());
                } else {
                    this.cv_tv_count_backup.setText("0");
                }
            } else {
                this.cv_tv_count_backup.setText("0");
            }
        } else {
            this.cv_tv_count_backup.setText("0");
        }
        ArrayList<String> selectedAppList = this.cv_tinyDB.getSelectedAppList(getResources().getString(R.string.app_key));
        this.cv_tv_count_video.setText("" + arrayList.size());
        this.cv_tv_count_pic.setText("" + arrayList2.size());
        this.cv_tv_count_other.setText("" + arrayList3.size());
        this.cv_tv_count_apps.setText("" + selectedAppList.size());
    }

    public void onPause() {
        super.onPause();
        if (!this.cv_dialog_running && this.cv_for_pause == 0) {
            this.cv_for_pause = -1;
        }
        if (!this.cv_check_pause_op.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
    }
}
