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
import com.example.photovideohidelock.CV_adapter.CV_HiddenContactAdapter;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_models.CV_ContactModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_RelativePopupWindow;
//import com.example.photovideohidelock.CV_workers.AdAdmob;
import com.example.photovideohidelock.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CV_AddContactToHideActivity extends AppCompatActivity {
    public static int cv_add_edit;
    public static Boolean cv_checkpauseoperationn = false;
    public static ArrayList<CV_ContactModel> cv_models = new ArrayList<>();
    ArrayList<CV_ContactModel> cv_contactModels;
    EditText cv_edt_search;
    CV_HiddenContactAdapter cv_hiddenContactAdapter;
    ImageView cv_ic_add;
    ImageView cv_ic_back;
    ImageView cv_ic_close;
    ImageView cv_ic_option;
    ImageView cv_ic_search;
    ImageView cv_ic_search1;
    Activity cv_mContext;
    RecyclerView cv_rcv_contacts;
    LinearLayout cv_search_lay;
    TextView cv_txt_nocontact;
    View cv_vieww;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_add_contact_to_hide);
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_ic_search = (ImageView) findViewById(R.id.ic_search);
        this.cv_ic_option = (ImageView) findViewById(R.id.ic_option);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_contacts);
        this.cv_rcv_contacts = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.cv_mContext));
        this.cv_txt_nocontact = (TextView) findViewById(R.id.txt_nocontact);
        this.cv_ic_add = (ImageView) findViewById(R.id.ic_add);
        this.cv_search_lay = (LinearLayout) findViewById(R.id.search_lay);
        this.cv_ic_search1 = (ImageView) findViewById(R.id.ic_search1);
        this.cv_edt_search = (EditText) findViewById(R.id.edt_search);
        this.cv_ic_close = (ImageView) findViewById(R.id.ic_close);
        this.cv_vieww = findViewById(R.id.vieww);
        this.cv_ic_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.cv_checkpauseoperationn = true;
                CV_AddContactToHideActivity.cv_add_edit = 0;
                CV_AddContactToHideActivity cV_AddContactToHideActivity = CV_AddContactToHideActivity.this;
                CV_Adshow.showinterstitialAd(cV_AddContactToHideActivity, new Intent(cV_AddContactToHideActivity.cv_mContext, CV_AddEditContactActivity.class));
            }
        });
        this.cv_ic_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.this.cv_edt_search.requestFocus();
                CV_MyHelper.showKeyboardFrom(CV_AddContactToHideActivity.this.cv_mContext, CV_AddContactToHideActivity.this.cv_edt_search);
                CV_AddContactToHideActivity.this.cv_ic_search.setVisibility(View.GONE);
                CV_AddContactToHideActivity.this.cv_search_lay.setVisibility(View.VISIBLE);
            }
        });
        this.cv_edt_search.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                CV_AddContactToHideActivity.this.cv_hiddenContactAdapter.cv_filter(CV_AddContactToHideActivity.this.cv_edt_search.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
        this.cv_ic_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.this.cv_edt_search.setText("");
                CV_AddContactToHideActivity.this.cv_ic_search.setVisibility(View.VISIBLE);
                CV_AddContactToHideActivity.this.cv_search_lay.setVisibility(View.GONE);
                CV_MyHelper.hideKeyboardFrom(CV_AddContactToHideActivity.this.cv_mContext, CV_AddContactToHideActivity.this.cv_edt_search);
            }
        });
        this.cv_ic_option.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.this.ShowMoreDialog();
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.this.onBackPressed();
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
        cV_RelativePopupWindow.setContentView(inflate);
        CV_HelperResizer.setSize(imageView, 290, 100, true);
        CV_HelperResizer.setSize(imageView2, 290, 100, true);
        CV_HelperResizer.setSize(imageView3, 290, 100, true);
        cV_RelativePopupWindow.setWidth(-2);
        cV_RelativePopupWindow.setHeight(-2);
        cV_RelativePopupWindow.setFocusable(true);
        cV_RelativePopupWindow.setOutsideTouchable(true);
        cV_RelativePopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        cV_RelativePopupWindow.showOnAnchor(this.cv_vieww, 2, 1);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.cv_models.clear();
                CV_AddContactToHideActivity.cv_models.addAll(CV_AddContactToHideActivity.this.cv_contactModels);
                CV_AddContactToHideActivity cV_AddContactToHideActivity = CV_AddContactToHideActivity.this;
                cV_AddContactToHideActivity.cv_hiddenContactAdapter = new CV_HiddenContactAdapter(cV_AddContactToHideActivity.cv_mContext, CV_AddContactToHideActivity.this.cv_contactModels);
                CV_AddContactToHideActivity.this.cv_rcv_contacts.setAdapter(CV_AddContactToHideActivity.this.cv_hiddenContactAdapter);
                cV_RelativePopupWindow.dismiss();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.cv_models.clear();
                CV_AddContactToHideActivity cV_AddContactToHideActivity = CV_AddContactToHideActivity.this;
                cV_AddContactToHideActivity.cv_hiddenContactAdapter = new CV_HiddenContactAdapter(cV_AddContactToHideActivity.cv_mContext, CV_AddContactToHideActivity.this.cv_contactModels);
                CV_AddContactToHideActivity.this.cv_rcv_contacts.setAdapter(CV_AddContactToHideActivity.this.cv_hiddenContactAdapter);
                cV_RelativePopupWindow.dismiss();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_AddContactToHideActivity.cv_models.size() == 0) {
                    Toast.makeText(CV_AddContactToHideActivity.this.cv_mContext, "Please Select At Least One Contact To Delete", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < CV_AddContactToHideActivity.cv_models.size(); i++) {
                    CV_MyHelper.deleteContactData(CV_AddContactToHideActivity.this.cv_mContext, CV_AddContactToHideActivity.cv_models.get(i).getLookupkey());
                }
                CV_AddContactToHideActivity.cv_models.removeAll(CV_AddContactToHideActivity.this.cv_contactModels);
                cV_RelativePopupWindow.dismiss();
                Toast.makeText(CV_AddContactToHideActivity.this.cv_mContext, "Contacts Deleted Successfully", Toast.LENGTH_SHORT).show();
                CV_AddContactToHideActivity.this.onResume();
            }
        });
    }

    public class LoadContacts extends AsyncTask<Void, Void, Void> {
        private LoadContacts() {
        }

        public void onPreExecute() {
            super.onPreExecute();
            CV_AddContactToHideActivity.this.cv_contactModels = new ArrayList<>();
        }

        public Void doInBackground(Void... voidArr) {
            CV_AddContactToHideActivity cV_AddContactToHideActivity = CV_AddContactToHideActivity.this;
            cV_AddContactToHideActivity.cv_contactModels = CV_MyHelper.getContactData(cV_AddContactToHideActivity.cv_mContext);
            Collections.reverse(CV_AddContactToHideActivity.this.cv_contactModels);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            CV_AddContactToHideActivity.cv_models = new ArrayList<>();
            CV_AddContactToHideActivity cV_AddContactToHideActivity = CV_AddContactToHideActivity.this;
            cV_AddContactToHideActivity.cv_hiddenContactAdapter = new CV_HiddenContactAdapter(cV_AddContactToHideActivity.cv_mContext, CV_AddContactToHideActivity.this.cv_contactModels);
            CV_AddContactToHideActivity.this.cv_rcv_contacts.setAdapter(CV_AddContactToHideActivity.this.cv_hiddenContactAdapter);
            if (CV_AddContactToHideActivity.this.cv_contactModels.size() == 0) {
                CV_AddContactToHideActivity.this.cv_txt_nocontact.setVisibility(View.VISIBLE);
            } else {
                CV_AddContactToHideActivity.this.cv_txt_nocontact.setVisibility(View.GONE);
            }
        }
    }

    public void onResume() {
        super.onResume();
        cv_checkpauseoperationn = false;
        new LoadContacts().execute(new Void[0]);
    }

    public void onBackPressed() {
        super.onBackPressed();
        cv_checkpauseoperationn = true;
        cv_models.clear();
        finish();
    }

    public void onPause() {
        if (!cv_checkpauseoperationn.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
