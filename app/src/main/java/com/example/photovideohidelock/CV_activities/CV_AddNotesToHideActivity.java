package com.example.photovideohidelock.CV_activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_adapter.CV_HiddenNotesAdapter;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_interfaces.CV_NoteDeleteInterface;
import com.example.photovideohidelock.CV_models.CV_NotesModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_RelativePopupWindow;
//import com.example.photovideohidelock.CV_workers.AdAdmob;
import com.example.photovideohidelock.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CV_AddNotesToHideActivity extends AppCompatActivity implements CV_NoteDeleteInterface {
    public static int cv_add_edit_notes;
    public static Boolean cv_checkpauseoperation = false;
    public static ArrayList<CV_NotesModel> cv_notesModelArrayList = new ArrayList<>();
    EditText cv_edt_search;
    CV_HiddenNotesAdapter cv_hiddenNotesAdapter;
    ImageView cv_ic_add;
    ImageView cv_ic_back;
    ImageView cv_ic_close;
    ImageView cv_ic_option;
    ImageView cv_ic_search;
    ImageView cv_ic_search1;
    Activity cv_mContext;
    RecyclerView cv_rcv_notes;
    LinearLayout cv_search_lay;
    TextView cv_txt_nonote;
    View cv_vieww;
    ArrayList<CV_NotesModel> notesModels;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_add_notes_to_hide);
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_rcv_notes = (RecyclerView) findViewById(R.id.rcv_notes);
        this.cv_txt_nonote = (TextView) findViewById(R.id.txt_nonote);
        this.cv_ic_add = (ImageView) findViewById(R.id.ic_add);
        this.cv_ic_search = (ImageView) findViewById(R.id.ic_search);
        this.cv_search_lay = (LinearLayout) findViewById(R.id.search_lay);
        this.cv_ic_search1 = (ImageView) findViewById(R.id.ic_search1);
        this.cv_edt_search = (EditText) findViewById(R.id.edt_search);
        this.cv_ic_close = (ImageView) findViewById(R.id.ic_close);
        this.cv_ic_option = (ImageView) findViewById(R.id.ic_option);
        this.cv_vieww = findViewById(R.id.vieww);
        this.cv_rcv_notes.setLayoutManager(new LinearLayoutManager(this.cv_mContext));
        this.cv_ic_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddNotesToHideActivity.cv_checkpauseoperation = true;
                CV_AddNotesToHideActivity.cv_add_edit_notes = 0;
                CV_AddNotesToHideActivity cV_AddNotesToHideActivity = CV_AddNotesToHideActivity.this;
                CV_Adshow.showinterstitialAd(cV_AddNotesToHideActivity, new Intent(cV_AddNotesToHideActivity.cv_mContext, CV_AddEditNotesActivity.class));
            }
        });
        this.cv_ic_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddNotesToHideActivity.this.cv_edt_search.requestFocus();
                CV_MyHelper.showKeyboardFrom(CV_AddNotesToHideActivity.this.cv_mContext, CV_AddNotesToHideActivity.this.cv_edt_search);
                CV_AddNotesToHideActivity.this.cv_ic_search.setVisibility(View.GONE);
                CV_AddNotesToHideActivity.this.cv_search_lay.setVisibility(View.VISIBLE);
            }
        });
        this.cv_edt_search.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                CV_AddNotesToHideActivity.this.cv_hiddenNotesAdapter.cv_filter(CV_AddNotesToHideActivity.this.cv_edt_search.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
        this.cv_ic_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddNotesToHideActivity.this.cv_edt_search.setText("");
                CV_AddNotesToHideActivity.this.cv_ic_search.setVisibility(View.VISIBLE);
                CV_AddNotesToHideActivity.this.cv_search_lay.setVisibility(View.GONE);
                CV_MyHelper.hideKeyboardFrom(CV_AddNotesToHideActivity.this.cv_mContext, CV_AddNotesToHideActivity.this.cv_edt_search);
            }
        });
        this.cv_ic_option.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddNotesToHideActivity.this.ShowMoreDialog();
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddNotesToHideActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_close, 40, 40, true);
        CV_HelperResizer.setSize(this.cv_ic_search, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_ic_search1, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_ic_option, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_ic_add, 150, 150, true);
        CV_HelperResizer.setSize(this.cv_search_lay, 770, 90);
    }

    /* access modifiers changed from: private */
    public void ShowMoreDialog() {
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
                CV_AddNotesToHideActivity.cv_notesModelArrayList.clear();
                CV_AddNotesToHideActivity.cv_notesModelArrayList.addAll(CV_AddNotesToHideActivity.this.notesModels);
                CV_AddNotesToHideActivity cV_AddNotesToHideActivity = CV_AddNotesToHideActivity.this;
                cV_AddNotesToHideActivity.cv_hiddenNotesAdapter = new CV_HiddenNotesAdapter(cV_AddNotesToHideActivity.cv_mContext, CV_AddNotesToHideActivity.this.notesModels, CV_AddNotesToHideActivity.this);
                CV_AddNotesToHideActivity.this.cv_rcv_notes.setAdapter(CV_AddNotesToHideActivity.this.cv_hiddenNotesAdapter);
                cV_RelativePopupWindow.dismiss();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddNotesToHideActivity.cv_notesModelArrayList.clear();
                CV_AddNotesToHideActivity cV_AddNotesToHideActivity = CV_AddNotesToHideActivity.this;
                cV_AddNotesToHideActivity.cv_hiddenNotesAdapter = new CV_HiddenNotesAdapter(cV_AddNotesToHideActivity.cv_mContext, CV_AddNotesToHideActivity.this.notesModels, CV_AddNotesToHideActivity.this);
                CV_AddNotesToHideActivity.this.cv_rcv_notes.setAdapter(CV_AddNotesToHideActivity.this.cv_hiddenNotesAdapter);
                cV_RelativePopupWindow.dismiss();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_AddNotesToHideActivity.cv_notesModelArrayList.size() == 0) {
                    Toast.makeText(CV_AddNotesToHideActivity.this.cv_mContext, "Please Select At Least One Note To Delete", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < CV_AddNotesToHideActivity.cv_notesModelArrayList.size(); i++) {
                    CV_MyHelper.deleteNotesData(CV_AddNotesToHideActivity.this.cv_mContext, CV_AddNotesToHideActivity.cv_notesModelArrayList.get(i).getId());
                }
                CV_AddNotesToHideActivity.cv_notesModelArrayList.removeAll(CV_AddNotesToHideActivity.this.notesModels);
                cV_RelativePopupWindow.dismiss();
                Toast.makeText(CV_AddNotesToHideActivity.this.cv_mContext, "Notes Deleted Successfully", Toast.LENGTH_SHORT).show();
                CV_AddNotesToHideActivity.this.onResume();
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

    public void onDeleteClick(String str) {
        CV_MyHelper.deleteNotesData(this.cv_mContext, str);
        onResume();
    }

    public class LoadNotes extends AsyncTask<Void, Void, Void> {
        private LoadNotes() {
        }

        public void onPreExecute() {
            super.onPreExecute();
            CV_AddNotesToHideActivity.this.notesModels = new ArrayList<>();
        }

        public Void doInBackground(Void... voidArr) {
            CV_AddNotesToHideActivity cV_AddNotesToHideActivity = CV_AddNotesToHideActivity.this;
            cV_AddNotesToHideActivity.notesModels = CV_MyHelper.getNotesData(cV_AddNotesToHideActivity.cv_mContext);
            Collections.reverse(CV_AddNotesToHideActivity.this.notesModels);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            CV_AddNotesToHideActivity cV_AddNotesToHideActivity = CV_AddNotesToHideActivity.this;
            cV_AddNotesToHideActivity.cv_hiddenNotesAdapter = new CV_HiddenNotesAdapter(cV_AddNotesToHideActivity.cv_mContext, CV_AddNotesToHideActivity.this.notesModels, CV_AddNotesToHideActivity.this);
            CV_AddNotesToHideActivity.this.cv_rcv_notes.setAdapter(CV_AddNotesToHideActivity.this.cv_hiddenNotesAdapter);
            if (CV_AddNotesToHideActivity.this.notesModels.size() == 0) {
                CV_AddNotesToHideActivity.this.cv_txt_nonote.setVisibility(View.VISIBLE);
            } else {
                CV_AddNotesToHideActivity.this.cv_txt_nonote.setVisibility(View.GONE);
            }
        }
    }

    public void onResume() {
        super.onResume();
        cv_checkpauseoperation = false;
        new LoadNotes().execute(new Void[0]);
    }

    public void onBackPressed() {
        super.onBackPressed();
        cv_checkpauseoperation = true;
        cv_notesModelArrayList.clear();
        finish();
    }

    public void onPause() {
        if (!cv_checkpauseoperation.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
