package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photovideohidelock.CV_adapter.CV_HiddenOtherFilesAdapter;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_interfaces.CV_OtherFilesDeleteInterface;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_RelativePopupWindow;
import com.example.photovideohidelock.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class CV_AddOtherFilesToHideActivity extends AppCompatActivity implements CV_OtherFilesDeleteInterface {
    public static ArrayList<String> cv_duplicatefileshidden = new ArrayList<>();
    private FrameLayout cv_adContainerView;
    Boolean cv_checkpauseoperation = false;
    EditText cv_edt_search;
    public ArrayList<String> cv_fileshidden = new ArrayList<>();
    CV_HiddenOtherFilesAdapter cv_hiddenOtherFilesAdapter;
    ImageView cv_ic_add;
    ImageView cv_ic_back;
    ImageView cv_ic_close;
    ImageView cv_ic_option;
    ImageView cv_ic_search;
    ImageView cv_ic_search1;
    Context cv_mContext;
    RecyclerView cv_rcv_otherfiles;
    LinearLayout cv_search_lay;
    TextView cv_txt_nofiles;
    View cv_vieww;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_add_other_files_to_hide);
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_ic_search = (ImageView) findViewById(R.id.ic_search);
        this.cv_ic_search1 = (ImageView) findViewById(R.id.ic_search1);
        this.cv_search_lay = (LinearLayout) findViewById(R.id.search_lay);
        this.cv_edt_search = (EditText) findViewById(R.id.edt_search);
        this.cv_ic_close = (ImageView) findViewById(R.id.ic_close);
        this.cv_ic_option = (ImageView) findViewById(R.id.ic_option);
        this.cv_vieww = findViewById(R.id.vieww);
        this.cv_rcv_otherfiles = (RecyclerView) findViewById(R.id.rcv_otherfiles);
        this.cv_txt_nofiles = (TextView) findViewById(R.id.txt_nofiles);
        this.cv_ic_add = (ImageView) findViewById(R.id.ic_add);
        this.cv_rcv_otherfiles.setLayoutManager(new LinearLayoutManager(this.cv_mContext));
        this.cv_ic_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddOtherFilesToHideActivity.this.cv_checkpauseoperation = true;
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("*/*");
                CV_AddOtherFilesToHideActivity.this.startActivityForResult(intent, 21);
            }
        });
        this.cv_ic_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddOtherFilesToHideActivity.this.cv_edt_search.requestFocus();
                CV_MyHelper.showKeyboardFrom(CV_AddOtherFilesToHideActivity.this.cv_mContext, CV_AddOtherFilesToHideActivity.this.cv_edt_search);
                CV_AddOtherFilesToHideActivity.this.cv_ic_search.setVisibility(View.GONE);
                CV_AddOtherFilesToHideActivity.this.cv_search_lay.setVisibility(View.VISIBLE);
            }
        });
        this.cv_edt_search.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                CV_AddOtherFilesToHideActivity.this.cv_hiddenOtherFilesAdapter.cv_filter(CV_AddOtherFilesToHideActivity.this.cv_edt_search.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
        this.cv_ic_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddOtherFilesToHideActivity.this.cv_edt_search.setText("");
                CV_AddOtherFilesToHideActivity.this.cv_ic_search.setVisibility(View.VISIBLE);
                CV_AddOtherFilesToHideActivity.this.cv_search_lay.setVisibility(View.GONE);
                CV_MyHelper.hideKeyboardFrom(CV_AddOtherFilesToHideActivity.this.cv_mContext, CV_AddOtherFilesToHideActivity.this.cv_edt_search);
            }
        });
        this.cv_ic_option.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddOtherFilesToHideActivity.this.cv_ShowMoreDialog();
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddOtherFilesToHideActivity.this.onBackPressed();
            }
        });
        new Cl_LoadFiles().execute(new Void[0]);
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_search, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_ic_search1, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_ic_option, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_ic_close, 40, 40, true);
        CV_HelperResizer.setSize(this.cv_ic_add, 150, 150, true);
        CV_HelperResizer.setSize(this.cv_search_lay, 770, 90);
    }

    /* access modifiers changed from: private */
    public void cv_ShowMoreDialog() {
        final CV_RelativePopupWindow cV_RelativePopupWindow = new CV_RelativePopupWindow();
        ViewGroup viewGroup = null;
        View inflate = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cv_more_option_layout, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ic_selectall);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.ic_deselectall);
        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.ic_delete);
        CV_HelperResizer.setSize((LinearLayout) inflate.findViewById(R.id.menu_bg), 310, 360, true);
        CV_HelperResizer.setSize(imageView, 290, 100, true);
        CV_HelperResizer.setSize(imageView2, 290, 100, true);
        CV_HelperResizer.setSize(imageView3, 290, 100, true);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.clear();
                CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.addAll(CV_AddOtherFilesToHideActivity.this.cv_fileshidden);
                CV_AddOtherFilesToHideActivity cV_AddOtherFilesToHideActivity = CV_AddOtherFilesToHideActivity.this;
                cV_AddOtherFilesToHideActivity.cv_hiddenOtherFilesAdapter = new CV_HiddenOtherFilesAdapter(cV_AddOtherFilesToHideActivity.cv_mContext, CV_AddOtherFilesToHideActivity.this.cv_fileshidden, CV_AddOtherFilesToHideActivity.this);
                CV_AddOtherFilesToHideActivity.this.cv_rcv_otherfiles.setAdapter(CV_AddOtherFilesToHideActivity.this.cv_hiddenOtherFilesAdapter);
                cV_RelativePopupWindow.dismiss();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.clear();
                CV_AddOtherFilesToHideActivity cV_AddOtherFilesToHideActivity = CV_AddOtherFilesToHideActivity.this;
                cV_AddOtherFilesToHideActivity.cv_hiddenOtherFilesAdapter = new CV_HiddenOtherFilesAdapter(cV_AddOtherFilesToHideActivity.cv_mContext, CV_AddOtherFilesToHideActivity.this.cv_fileshidden, CV_AddOtherFilesToHideActivity.this);
                CV_AddOtherFilesToHideActivity.this.cv_rcv_otherfiles.setAdapter(CV_AddOtherFilesToHideActivity.this.cv_hiddenOtherFilesAdapter);
                cV_RelativePopupWindow.dismiss();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.size() == 0) {
                    Toast.makeText(CV_AddOtherFilesToHideActivity.this.cv_mContext, "Please Select At Least One File To Delete", Toast.LENGTH_SHORT).show();
                    return;
                }
                CV_MyHelper.deleteFolder(new File(CV_AddOtherFilesToHideActivity.this.cv_mContext.getApplicationInfo().dataDir + File.separator, "Others"));
                cV_RelativePopupWindow.dismiss();
                Toast.makeText(CV_AddOtherFilesToHideActivity.this.cv_mContext, "File Deleted Successfully", Toast.LENGTH_SHORT).show();
                CV_AddOtherFilesToHideActivity.this.cv_fileshidden.clear();
                CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.clear();
                new Cl_LoadFiles().execute(new Void[0]);
            }
        });
        cV_RelativePopupWindow.setContentView(inflate);
        cV_RelativePopupWindow.setWidth(-2);
        cV_RelativePopupWindow.setHeight(-2);
        cV_RelativePopupWindow.setFocusable(true);
        cV_RelativePopupWindow.setOutsideTouchable(true);
        cV_RelativePopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        cV_RelativePopupWindow.showOnAnchor(this.cv_vieww, 2, 1);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 21 && i2 == -1 && intent != null) {
            ArrayList arrayList = new ArrayList();
            if (intent.getClipData() != null) {
                for (int i3 = 0; i3 < intent.getClipData().getItemCount(); i3++) {
                    arrayList.add(CV_MyHelper.getPath(this.cv_mContext, intent.getClipData().getItemAt(i3).getUri()));
                }
            } else {
                arrayList.add(CV_MyHelper.getPath(this.cv_mContext, intent.getData()));
            }
            hideThem(CV_MyHelper.getOthersFolder(this.cv_mContext), arrayList);
        }
    }

    private void hideThem(String str, ArrayList<String> arrayList) {
        if (arrayList.get(0) != null) {
            new HideIt(str, arrayList).execute(new Void[0]);
        } else {
            Toast.makeText(this.cv_mContext, "Select valid Path", Toast.LENGTH_SHORT).show();
        }
    }

    public void onDeleteClick(int i) {
        new UnHideIt("", this.cv_fileshidden.get(i), true).execute(new Void[0]);
    }

    public class HideIt extends AsyncTask<Void, Void, Void> {
        ArrayList<String> alpath;
        String folpath;

        public HideIt(String str, ArrayList<String> arrayList) {
            this.folpath = str;
            this.alpath = arrayList;
        }

        public void onPreExecute() {
            super.onPreExecute();
            this.alpath.size();
        }

        public Void doInBackground(Void... voidArr) {
            for (int i = 0; i < this.alpath.size(); i++) {
                String str = this.alpath.get(i);
                if (str != null && !str.isEmpty()) {
                    File file = new File(str);
                    File file2 = new File(this.folpath, file.getName());
                    try {
                        CV_MyHelper.moveFile(file, file2);
                        CV_MyHelper.insertData(CV_AddOtherFilesToHideActivity.this.cv_mContext, str, file2.getAbsolutePath());
                        CV_AddOtherFilesToHideActivity.this.cv_fileshidden.add(file2.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    MediaScannerConnection.scanFile(CV_AddOtherFilesToHideActivity.this.cv_mContext, new String[]{file.toString()}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String str, Uri uri) {
                            Objects.toString(uri);
                        }
                    });
                }
            }
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            try {
                CV_AddOtherFilesToHideActivity cV_AddOtherFilesToHideActivity = CV_AddOtherFilesToHideActivity.this;
                cV_AddOtherFilesToHideActivity.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Cl_LoadFiles().execute(new Void[0]);
        }
    }

    public class Cl_LoadFiles extends AsyncTask<Void, Void, Void> {
        private Cl_LoadFiles() {
        }

        public void onPreExecute() {
            super.onPreExecute();
            CV_AddOtherFilesToHideActivity.this.cv_fileshidden.clear();
        }

        public Void doInBackground(Void... voidArr) {
            for (File absolutePath : new File(CV_MyHelper.getOthersFolder(CV_AddOtherFilesToHideActivity.this.cv_mContext)).listFiles()) {
                CV_AddOtherFilesToHideActivity.this.cv_fileshidden.add(absolutePath.getAbsolutePath());
            }
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            CV_AddOtherFilesToHideActivity cV_AddOtherFilesToHideActivity = CV_AddOtherFilesToHideActivity.this;
            cV_AddOtherFilesToHideActivity.cv_hiddenOtherFilesAdapter = new CV_HiddenOtherFilesAdapter(cV_AddOtherFilesToHideActivity.cv_mContext, CV_AddOtherFilesToHideActivity.this.cv_fileshidden, CV_AddOtherFilesToHideActivity.this);
            CV_AddOtherFilesToHideActivity.this.cv_rcv_otherfiles.setAdapter(CV_AddOtherFilesToHideActivity.this.cv_hiddenOtherFilesAdapter);
            if (CV_AddOtherFilesToHideActivity.this.cv_fileshidden.size() == 0) {
                CV_AddOtherFilesToHideActivity.this.cv_txt_nofiles.setVisibility(View.VISIBLE);
                CV_AddOtherFilesToHideActivity.this.cv_rcv_otherfiles.setVisibility(View.GONE);
                return;
            }
            CV_AddOtherFilesToHideActivity.this.cv_txt_nofiles.setVisibility(View.GONE);
            CV_AddOtherFilesToHideActivity.this.cv_rcv_otherfiles.setVisibility(View.VISIBLE);
        }
    }

    public class UnHideIt extends AsyncTask<Void, Void, Void> {
        String alpath;
        String folpath;
        boolean toOriginal;

        public UnHideIt(String str, String str2, boolean z) {
            this.folpath = str;
            this.alpath = str2;
            this.toOriginal = z;
        }

        public void onPreExecute() {
            super.onPreExecute();
            CV_MyHelper.showLog("AAA", "Unhiding Starts : ");
        }

        public Void doInBackground(Void... voidArr) {
            String str = this.alpath;
            File file = new File(str);
            File file2 = new File(CV_MyHelper.getOriginalPath(CV_AddOtherFilesToHideActivity.this.cv_mContext, str));
            try {
                CV_MyHelper.moveFile(file, file2);
                CV_MyHelper.deleteData(CV_AddOtherFilesToHideActivity.this.cv_mContext, str);
                CV_AddOtherFilesToHideActivity.this.cv_fileshidden.remove(file.getAbsolutePath());
                CV_AddOtherFilesToHideActivity.this.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file2)));
            } catch (Exception e) {
                e.printStackTrace();
                CV_MyHelper.showLog("AAA", "Unhiding Error For : " + str);
                CV_MyHelper.showLog("AAA", e.toString());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            CV_MyHelper.showLog("AAA", "Unhiding done for : " + str);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            try {
                CV_AddOtherFilesToHideActivity cV_AddOtherFilesToHideActivity = CV_AddOtherFilesToHideActivity.this;
                cV_AddOtherFilesToHideActivity.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(CV_AddOtherFilesToHideActivity.this.cv_mContext, "File Unhided Successfully", Toast.LENGTH_SHORT).show();
            CV_MyHelper.showLog("AAA", "Unhiding Ends");
            new Cl_LoadFiles().execute(new Void[0]);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_checkpauseoperation = true;
        cv_duplicatefileshidden.clear();
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
