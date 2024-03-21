package com.example.photovideohidelock.CV_activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.util.TypedValue;


import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.photovideohidelock.CV_adapter.CV_RestoreBackupAdapter;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;
import com.hzy.libp7zip.P7ZipApi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CV_BackupRestoreActivity extends AppCompatActivity {
    public static Dialog cv_dialog;
    LinearLayout cv_backup_lay;
    Boolean cv_checkpauseoperation = false;
    String cv_file_name;
    String cv_folder_name;
    ImageView cv_ic_back;
    ImageView cv_ic_restore;
    ImageView cv_ic_take;
    ImageView cv_ic_watch;
    Context cv_mContext;
    ArrayList<String> cv_restore_list;
    CV_TinyDB cv_tinyDB;
    LinearLayout cv_watch_lay;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_backup_restore);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_watch_lay = (LinearLayout) findViewById(R.id.watch_lay);
        this.cv_ic_watch = (ImageView) findViewById(R.id.ic_watch);
        this.cv_backup_lay = (LinearLayout) findViewById(R.id.backup_lay);
        this.cv_ic_take = (ImageView) findViewById(R.id.ic_take);
        this.cv_ic_restore = (ImageView) findViewById(R.id.ic_restore);
        this.cv_watch_lay.setVisibility(View.GONE);
        this.cv_backup_lay.setVisibility(View.VISIBLE);
        this.cv_tinyDB = new CV_TinyDB(this.cv_mContext);
        this.cv_ic_watch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_BackupRestoreActivity.this.cv_checkpauseoperation = true;
                CV_BackupRestoreActivity.this.cv_watch_lay.setVisibility(View.GONE);
                CV_BackupRestoreActivity.this.cv_backup_lay.setVisibility(View.VISIBLE);
            }
        });
        this.cv_ic_take.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_BackupRestoreActivity cV_BackupRestoreActivity = CV_BackupRestoreActivity.this;
                cV_BackupRestoreActivity.cv_folder_name = cV_BackupRestoreActivity.cv_tinyDB.getString(CV_BackupRestoreActivity.this.getResources().getString(R.string.folder_name));
                boolean exists = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + CV_BackupRestoreActivity.this.cv_folder_name).exists();
                if (CV_BackupRestoreActivity.this.cv_folder_name.equalsIgnoreCase("")) {
                    CV_BackupRestoreActivity.this.cv_ShowFolderNameDialog();
                } else if (CV_BackupRestoreActivity.this.cv_folder_name.equalsIgnoreCase("") || exists) {
                    CV_BackupRestoreActivity.this.cv_ShowBackupNameDialog();
                } else {
                    CV_BackupRestoreActivity.this.cv_ShowFolderNameDialog();
                }
            }
        });
        this.cv_ic_restore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_BackupRestoreActivity.this.cv_folder_name.equalsIgnoreCase("")) {
                    Toast.makeText(CV_BackupRestoreActivity.this.cv_mContext, "Please Create Folder First And Take Backup", Toast.LENGTH_SHORT).show();
                } else {
                    CV_BackupRestoreActivity.this.cv_ShowRestoreListDialog();
                }
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_BackupRestoreActivity.this.cv_checkpauseoperation = true;
                CV_BackupRestoreActivity.this.onBackPressed();
            }
        });
    }

    public static void cv_copyDirectory(File file, File file2) throws IOException {
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("sourceDir does not exist");
        } else if (file.isFile() || file2.isFile()) {
            throw new IllegalArgumentException("Either sourceDir or destDir is not a directory");
        } else {
            try {
                cv_copyDirectoryImpl(file, file2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void cv_copyDirectoryImpl(File file, File file2) throws Throwable {
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file3 : listFiles) {
                if (file3.isDirectory()) {
                    File file4 = new File(file2, file3.getName());
                    System.out.println("CREATED DIR: " + file4.getAbsolutePath());
                    file4.mkdir();
                    cv_copyDirectory(file3, file4);
                } else {
                    cv_copySingleFile(file3, new File(file2, file3.getName()));
                }
            }
        }


    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005a, code lost:
        if (r0 != null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        throw r8;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */





    private static void cv_copySingleFile(File file, File file2) throws Throwable {
        PrintStream printStream = System.out;
        printStream.println("COPY FILE: " + file.getAbsolutePath() + " TO: " + file2.getAbsolutePath());
        if (!file2.exists()) {
            file2.createNewFile();
        }
        FileChannel fileChannel = null;
        try {
            FileChannel channel = new FileInputStream(file).getChannel();
            FileChannel channel2 = new FileOutputStream(file2).getChannel();
            channel.transferTo(0L, channel.size(), channel2);
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                fileChannel.close();
            }
            throw th;
        }
    }

//    private static void cv_copySingleFile(File r8, File r9) throws Throwable {
//        /*
//            java.io.PrintStream r0 = java.lang.System.out
//            java.lang.StringBuilder r1 = new java.lang.StringBuilder
//            java.lang.String r2 = "COPY FILE: "
//            r1.<init>(r2)
//            java.lang.String r2 = r8.getAbsolutePath()
//            r1.append(r2)
//            java.lang.String r2 = " TO: "
//            r1.append(r2)
//            java.lang.String r2 = r9.getAbsolutePath()
//            r1.append(r2)
//            java.lang.String r1 = r1.toString()
//            r0.println(r1)
//            boolean r0 = r9.exists()
//            if (r0 != 0) goto L_0x002c
//            r9.createNewFile()
//        L_0x002c:
//            r0 = 0
//            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0059 }
//            r1.<init>(r8)     // Catch:{ all -> 0x0059 }
//            java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch:{ all -> 0x0059 }
//            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x0057 }
//            r8.<init>(r9)     // Catch:{ all -> 0x0057 }
//            java.nio.channels.FileChannel r8 = r8.getChannel()     // Catch:{ all -> 0x0057 }
//            r3 = 0
//            long r5 = r0.size()     // Catch:{ all -> 0x0055 }
//            r2 = r0
//            r7 = r8
//            r2.transferTo(r3, r5, r7)     // Catch:{ all -> 0x0055 }
//            if (r0 == 0) goto L_0x004f
//            r0.close()     // Catch:{ all -> 0x0055 }
//        L_0x004f:
//            if (r8 == 0) goto L_0x0054
//            r8.close()     // Catch:{ all -> 0x0055 }
//        L_0x0054:
//            return
//        L_0x0055:
//            r8 = move-exception
//            throw r8     // Catch:{ all -> 0x0059 }
//        L_0x0057:
//            r8 = move-exception
//            throw r8     // Catch:{ all -> 0x0059 }
//        L_0x0059:
//            r8 = move-exception
//            if (r0 == 0) goto L_0x005f
//            r0.close()
//        L_0x005f:
//            throw r8
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.example.photovideohidelock.CV_activities.CV_BackupRestoreActivity.cv_copySingleFile(java.io.File, java.io.File):void");
//    }

    public String getExtractCmd(String str, String str2) {
        return String.format("7z x '%s' '-o%s' -aoa", new Object[]{str, str2});
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_watch, 630, 110);
        CV_HelperResizer.setSize(this.cv_ic_take, 980, 120);
        CV_HelperResizer.setSize(this.cv_ic_restore, 980, 120);
    }

    private class cv_MakeZipBackup extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        private cv_MakeZipBackup() {
            this.progressDialog = new ProgressDialog(CV_BackupRestoreActivity.this.cv_mContext);
        }

        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setCancelable(false);
            this.progressDialog.setMessage("Taking Backup...");
            this.progressDialog.show();
        }

        public Void doInBackground(Void... voidArr) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + CV_BackupRestoreActivity.this.cv_folder_name);
            if (!file.exists()) {
                file.mkdirs();
                file.mkdir();
            }
            CV_BackupRestoreActivity.this.zipFileAtPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault", file.getAbsolutePath() + "/" + CV_BackupRestoreActivity.this.cv_file_name + ".zip");
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    cv_MakeZipBackup.this.progressDialog.dismiss();
                    Toast.makeText(CV_BackupRestoreActivity.this.cv_mContext, "Backup Exported Successfully", Toast.LENGTH_SHORT).show();
                }
            }, 1500);
        }
    }

    public boolean zipFileAtPath(String str, String str2) {
        File file = new File(str);
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
            if (!file.isDirectory()) {
                byte[] bArr = new byte[2048];
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str), 2048);
                ZipEntry zipEntry = new ZipEntry(getLastPathComponent(str));
                zipEntry.setTime(file.lastModified());
                zipOutputStream.putNextEntry(zipEntry);
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 2048);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
            } else {
                cv_zipSubFolder(zipOutputStream, file, file.getParent().length());
            }
            zipOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void cv_zipSubFolder(ZipOutputStream zipOutputStream, File file, int i) throws IOException {
        for (File file2 : file.listFiles()) {
            if (!file2.isDirectory()) {
                byte[] bArr = new byte[2048];
                String path = file2.getPath();
                String substring = path.substring(i + 1);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path), 2048);
                ZipEntry zipEntry = new ZipEntry(substring);
                zipEntry.setTime(file2.lastModified());
                zipOutputStream.putNextEntry(zipEntry);
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 2048);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
                bufferedInputStream.close();
            } else if (!file2.getName().contains("cache") && !file2.getName().contains("shared_prefs")) {
                cv_zipSubFolder(zipOutputStream, file2, i);
            }
        }
    }

    public String getLastPathComponent(String str) {
        String[] split = str.split("/");
        if (split.length == 0) {
            return "";
        }
        return split[split.length - 1];
    }

    /* access modifiers changed from: private */
    public void cv_ShowFolderNameDialog() {
        final Dialog dialog = new Dialog(this.cv_mContext);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_folder_name_dialog_layout);
        dialog.show();
        final EditText editText = (EditText) dialog.findViewById(R.id.edt_name);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ic_ok);
        CV_HelperResizer.setSize((LinearLayout) dialog.findViewById(R.id.popup), 980, 552);
        CV_HelperResizer.setSize(imageView, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 100);
//        CV_HelperResizer.setSize(editText, TypedValues.Custom.TYPE_INT, 100);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                if (trim.equalsIgnoreCase("")) {
                    Toast.makeText(CV_BackupRestoreActivity.this.cv_mContext, "Please Enter Folder Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + trim);
                if (!file.exists()) {
                    file.mkdirs();
                    file.mkdir();
                    Context context = CV_BackupRestoreActivity.this.cv_mContext;
                    Toast.makeText(context, "Folder Created Successfully Here :  " + file.getPath(), Toast.LENGTH_LONG).show();
                }
                CV_BackupRestoreActivity.this.cv_tinyDB.putString(CV_BackupRestoreActivity.this.getResources().getString(R.string.folder_name), trim);
                dialog.dismiss();
                CV_BackupRestoreActivity.this.cv_ShowBackupNameDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void cv_ShowBackupNameDialog() {
        final Dialog dialog = new Dialog(this.cv_mContext);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.cv_file_name_dialog_layout);
        dialog.show();
        final EditText editText = (EditText) dialog.findViewById(R.id.edt_name);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ic_ok);
        CV_HelperResizer.setSize((LinearLayout) dialog.findViewById(R.id.popup), 980, 482);
//        CV_HelperResizer.setSize(editText, TypedValues.Custom.TYPE_INT, 100);
        CV_HelperResizer.setSize(imageView, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 100);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_BackupRestoreActivity.this.cv_file_name = editText.getText().toString().trim();
                if (CV_BackupRestoreActivity.this.cv_file_name.equalsIgnoreCase("")) {
                    Toast.makeText(CV_BackupRestoreActivity.this.cv_mContext, "Please Enter File Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                CV_BackupRestoreActivity cV_BackupRestoreActivity = CV_BackupRestoreActivity.this;
                cV_BackupRestoreActivity.cv_folder_name = cV_BackupRestoreActivity.cv_tinyDB.getString(CV_BackupRestoreActivity.this.getResources().getString(R.string.folder_name));
                if (CV_BackupRestoreActivity.this.cv_folder_name != null || !CV_BackupRestoreActivity.this.cv_folder_name.isEmpty()) {
                    String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + CV_BackupRestoreActivity.this.cv_folder_name + "/" + CV_BackupRestoreActivity.this.cv_file_name + ".zip";
                    if (CV_BackupRestoreActivity.this.cv_restore_list == null || !CV_BackupRestoreActivity.this.cv_restore_list.contains(str)) {
                        dialog.dismiss();
                        new cv_MakeZipBackup().execute(new Void[0]);
                        return;
                    }
                    Toast.makeText(CV_BackupRestoreActivity.this.cv_mContext, "Same File Name Already Exists!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void cv_ShowRestoreListDialog() {
        Dialog dialog = new Dialog(this.cv_mContext);
        cv_dialog = dialog;
        dialog.requestWindowFeature(1);
        cv_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        cv_dialog.setContentView(R.layout.cv_restore_backup_dialog_layout);
        cv_dialog.show();
        ListView listView = (ListView) cv_dialog.findViewById(R.id.list_restore);
        TextView textView = (TextView) cv_dialog.findViewById(R.id.tv_nobackup);
        CV_HelperResizer.setSize((LinearLayout) cv_dialog.findViewById(R.id.restore_popup), 850, 850, true);
        if (!this.cv_folder_name.equalsIgnoreCase("")) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + this.cv_folder_name);
            if (file.exists()) {
                File[] listFiles = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault").listFiles();
                Arrays.sort(listFiles, new Comparator<File>() {
                    public int compare(File file, File file2) {
                        if (file.lastModified() > file2.lastModified()) {
                            return 1;
                        }
                        return file.lastModified() == file2.lastModified() ? 0 : -1;
                    }
                });
                ArrayList<String> arrayList = new ArrayList<>();
                this.cv_restore_list = arrayList;
                arrayList.clear();
                for (File absolutePath : listFiles) {
                    if (!absolutePath.getAbsolutePath().equals(file + "/" + getPackageName())) {
                        this.cv_restore_list.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + this.cv_folder_name);
                    }
                }
                Collections.reverse(this.cv_restore_list);
            }
            if (this.cv_restore_list.size() > 0) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
            }
            listView.setAdapter(new CV_RestoreBackupAdapter(this.cv_mContext, this.cv_restore_list));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    CV_BackupRestoreActivity.cv_dialog.dismiss();
                    final ProgressDialog progressDialog = new ProgressDialog(CV_BackupRestoreActivity.this.cv_mContext);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Restoring Backup...");
                    progressDialog.show();
                    final StringBuilder sb = new StringBuilder();
                    sb.append(CV_BackupRestoreActivity.this.cv_mContext.getApplicationInfo().dataDir);
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + CV_BackupRestoreActivity.this.cv_folder_name);
                    if (!file.exists()) {
                        file.mkdirs();
                        file.mkdir();
                    }
                    if (file.canWrite()) {
                        new File(file.getPath());
                        try {
                            CV_BackupRestoreActivity cV_BackupRestoreActivity = CV_BackupRestoreActivity.this;
                            P7ZipApi.executeCommand(cV_BackupRestoreActivity.getExtractCmd(cV_BackupRestoreActivity.cv_restore_list.get(i), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault"));
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    try {
                                        CV_BackupRestoreActivity.cv_copyDirectory(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault"), new File(sb.toString()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, 1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(CV_BackupRestoreActivity.this.cv_mContext, "Backup Restored Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }, 1500);
                }
            });
            return;
        }
        Toast.makeText(this.cv_mContext, "Please Create Folder First And Take Backup", Toast.LENGTH_SHORT).show();
    }

    public void onResume() {
        super.onResume();
        this.cv_checkpauseoperation = false;
        String string = this.cv_tinyDB.getString(getResources().getString(R.string.folder_name));
        this.cv_folder_name = string;
        if (!string.equalsIgnoreCase("")) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + this.cv_folder_name);
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                Arrays.sort(listFiles, new Comparator<File>() {
                    public int compare(File file, File file2) {
                        if (file.lastModified() > file2.lastModified()) {
                            return 1;
                        }
                        return file.lastModified() == file2.lastModified() ? 0 : -1;
                    }
                });
                this.cv_restore_list = new ArrayList<>();
                for (File absolutePath : listFiles) {
                    this.cv_restore_list.add(absolutePath.getAbsolutePath());
                }
                Collections.reverse(this.cv_restore_list);
            }
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_checkpauseoperation = true;
        finish();
    }

    public void onPause() {
        if (!this.cv_checkpauseoperation.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
