package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyDatabaseHelper;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;
import java.net.URL;
import java.util.regex.Pattern;

public class CV_AddSafePasswordDetailActivity extends AppCompatActivity {
    private FrameLayout cv_adContainerView;
    ImageView cv_ic_back;
    ImageView cv_ic_done;
    Context cv_mContext;
    CV_MyDatabaseHelper cv_myDatabaseHelper;
    Boolean cv_pause_opearion = false;
    TextView cv_tv_title;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("atm")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_atm);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("bank")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_bank_account);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("creditcard")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_credit_card);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("email")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_email);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("idcard")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_idcard);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("website")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_website);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("commerce")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_commerce);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase(NotificationCompat.CATEGORY_SOCIAL)) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_social);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("business")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_business);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("general")) {
            setContentView((int) R.layout.cv_activity_add_safe_password_detail_general);
        }
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_tv_title = (TextView) findViewById(R.id.tv_title);
        this.cv_ic_done = (ImageView) findViewById(R.id.ic_done);
        this.cv_myDatabaseHelper = new CV_MyDatabaseHelper(this.cv_mContext);
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("atm")) {
            this.cv_tv_title.setText("ATM");
            cv_forATM();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("bank")) {
            this.cv_tv_title.setText("Bank Account");
            cv_forBankAccount();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("creditcard")) {
            this.cv_tv_title.setText("Credit Card");
            cv_forCreditCard();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("email")) {
            this.cv_tv_title.setText("Email");
            cv_forEmail();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("idcard")) {
            this.cv_tv_title.setText("ID Card");
            cv_forIDCard();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("website")) {
            this.cv_tv_title.setText("Website");
            cv_forWebsite();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("commerce")) {
            this.cv_tv_title.setText("E-Commerce");
            forCommerce();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase(NotificationCompat.CATEGORY_SOCIAL)) {
            this.cv_tv_title.setText("Social");
            cv_forSocial();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("business")) {
            this.cv_tv_title.setText("Business");
            cv_forBusiness();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("general")) {
            this.cv_tv_title.setText("General");
            cv_forGeneral();
        }
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordDetailActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_done, 100, 50);
    }

    private void cv_forATM() {
        CV_AddSafePasswordDetailActivity cV_AddSafePasswordDetailActivity = this;
        final EditText editText = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_cardname);
        final EditText editText2 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_bankname);
        final EditText editText3 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_pin);
        final EditText editText4 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_holdername);
        final EditText editText5 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_cardnumber);
        final EditText editText6 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_accnumber);
        final EditText editText7 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_phnumber);
        final EditText editText8 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_url);
        final EditText editText9 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom1);
        final EditText editText10 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom2);
        final EditText editText11 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom3);
        final EditText editText12 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom4);
        final EditText editText13 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText, 980, 100);
        CV_HelperResizer.setSize(editText2, 980, 100);
        CV_HelperResizer.setSize(editText3, 980, 100);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setSize(editText10, 980, 100);
        CV_HelperResizer.setSize(editText11, 980, 100);
        CV_HelperResizer.setSize(editText12, 980, 100);
        CV_HelperResizer.setSize(editText13, 980, 100);
        CV_HelperResizer.setMargin(editText, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText2, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText3, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText10, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText11, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText12, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText13, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getCard_name());
            editText2.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getBank_name());
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getPin());
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getHolder_name());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getCard_number());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getAcc_number());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getPh_number());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getUrl());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getCustom1());
            editText10.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getCustom2());
            editText11.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getCustom3());
            editText12.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getCustom4());
            editText13.setText(CV_AddSafePasswordToHideActivity.cv_atmModels.get(intExtra).getCustom5());
            editText.setEnabled(false);
            editText2.setEnabled(false);
            editText3.setEnabled(false);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
            editText7.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
            editText10.setEnabled(false);
            editText11.setEnabled(false);
            editText12.setEnabled(false);
            editText13.setEnabled(false);
            cV_AddSafePasswordDetailActivity = this;
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.GONE);
        } else {
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.VISIBLE);
        }
        View.OnClickListener r0 = null;
        View.OnClickListener r16 = r0;
        ImageView imageView = cV_AddSafePasswordDetailActivity.cv_ic_done;
         r0 = new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordDetailActivity.this.cv_pause_opearion = true;
                if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Card Name", Toast.LENGTH_SHORT).show();
                } else if (editText2.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Bank Name", Toast.LENGTH_SHORT).show();
                } else if (editText3.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Pin", Toast.LENGTH_SHORT).show();
                } else if (editText4.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Holder Name", Toast.LENGTH_SHORT).show();
                } else if (editText5.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Card Number", Toast.LENGTH_SHORT).show();
                } else if (editText6.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Account Number", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else if (editText8.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter URL", Toast.LENGTH_SHORT).show();
                } else if (editText3.getText().toString().trim().length() != 6) {
                    editText3.setError("Please Enter Valid Pin Number");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Pin Number", Toast.LENGTH_LONG).show();
                } else if (editText5.getText().toString().trim().length() != 15) {
                    editText5.setError("Please Enter Valid Card Number");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Card Number", Toast.LENGTH_LONG).show();
                } else if (editText6.getText().toString().trim().length() != 14) {
                    editText6.setError("Please Enter Valid Account Number");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Account Number", Toast.LENGTH_LONG).show();
                } else if (!CV_AddSafePasswordDetailActivity.this.isValidMobile(editText7.getText().toString().trim())) {
                    editText7.setError("Please Enter Valid Number");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Number", Toast.LENGTH_LONG).show();
                } else if (!CV_AddSafePasswordDetailActivity.this.IsValidUrl(editText8.getText().toString().trim())) {
                    editText8.setError("Please Enter Valid URL");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid URL", Toast.LENGTH_LONG).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(CV_AddSafePasswordDetailActivity.this.cv_mContext, editText13);
                    CV_AddSafePasswordDetailActivity.this.cv_myDatabaseHelper.insertAtmData(String.valueOf(System.currentTimeMillis()), editText.getText().toString().trim(), editText2.getText().toString(), editText3.getText().toString().trim(), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim(), editText10.getText().toString().trim(), editText11.getText().toString().trim(), editText12.getText().toString().trim(), editText13.getText().toString().trim());
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    CV_AddSafePasswordDetailActivity.this.finish();
                }
            }
        };
        imageView.setOnClickListener(r0);
    }

    private void cv_forBankAccount() {
        EditText editText;
        EditText editText2;
        EditText editText3;
        CV_AddSafePasswordDetailActivity cV_AddSafePasswordDetailActivity;
        final EditText editText4 = (EditText) findViewById(R.id.edt_bankname);
        final EditText editText5 = (EditText) findViewById(R.id.edt_accnumber);
        final EditText editText6 = (EditText) findViewById(R.id.edt_holdername);
        final EditText editText7 = (EditText) findViewById(R.id.edt_acctype);
        final EditText editText8 = (EditText) findViewById(R.id.edt_pin);
        final EditText editText9 = (EditText) findViewById(R.id.edt_email);
        final EditText editText10 = (EditText) findViewById(R.id.edt_mothername);
        final EditText editText11 = (EditText) findViewById(R.id.edt_surname);
        final EditText editText12 = (EditText) findViewById(R.id.edt_sortcode);
        final EditText editText13 = (EditText) findViewById(R.id.edt_swiftcode);
        final EditText editText14 = (EditText) findViewById(R.id.edt_routnum);
        final EditText editText15 = (EditText) findViewById(R.id.edt_pin2);
        EditText editText16 = (EditText) findViewById(R.id.edt_url);
        EditText editText17 = (EditText) findViewById(R.id.edt_custom1);
        EditText editText18 = (EditText) findViewById(R.id.edt_custom2);
        EditText editText19 = (EditText) findViewById(R.id.edt_custom3);
        EditText editText20 = (EditText) findViewById(R.id.edt_custom4);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setSize(editText10, 980, 100);
        CV_HelperResizer.setSize(editText11, 980, 100);
        CV_HelperResizer.setSize(editText12, 980, 100);
        CV_HelperResizer.setSize(editText13, 980, 100);
        CV_HelperResizer.setSize(editText14, 980, 100);
        CV_HelperResizer.setSize(editText15, 980, 100);
        CV_HelperResizer.setSize(editText16, 980, 100);
        CV_HelperResizer.setSize(editText17, 980, 100);
        EditText editText21 = editText17;
        CV_HelperResizer.setSize(editText18, 980, 100);
        CV_HelperResizer.setSize(editText19, 980, 100);
        CV_HelperResizer.setSize(editText20, 980, 100);
        EditText editText22 = (EditText) findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText22, 980, 100);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText10, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText11, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText12, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText13, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText14, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText15, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText16, 0, 15, 0, 0);
        EditText editText23 = editText16;
        CV_HelperResizer.setMargin(editText21, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText18, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText19, 0, 15, 0, 0);
        EditText editText24 = editText20;
        CV_HelperResizer.setMargin(editText24, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText22, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            editText = editText22;
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getBank_name());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getAcc_number());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getHolder_name());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getAcc_type());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getPin());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getEmailID());
            editText10.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getMother_name());
            editText11.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getSurname());
            editText12.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getSort_code());
            editText13.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getSwift_code());
            editText14.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getRoute_code());
            editText15.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getPin2());
            editText23.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getUrl());
            editText21.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getCustom1());
            editText18.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getCustom2());
            editText3 = editText19;
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getCustom3());
            editText24.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getCustom4());
            editText.setText(CV_AddSafePasswordToHideActivity.cv_bankModels.get(intExtra).getCustom5());
            this.cv_ic_done.setVisibility(View.GONE);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
            editText7.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
            editText10.setEnabled(false);
            editText11.setEnabled(false);
            editText12.setEnabled(false);
            editText13.setEnabled(false);
            editText14.setEnabled(false);
            editText15.setEnabled(false);
            editText23.setEnabled(false);
            editText21.setEnabled(false);
            editText18.setEnabled(false);
            editText3.setEnabled(false);
            editText24.setEnabled(false);
            editText.setEnabled(false);
            editText2 = editText24;
            cV_AddSafePasswordDetailActivity = this;
        } else {
            editText = editText22;
            editText2 = editText24;
            editText3 = editText19;
            cV_AddSafePasswordDetailActivity = this;
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.VISIBLE);
        }
        final EditText editText25 = editText18;
        EditText editText26 = editText2;
        final EditText editText27 = editText3;
        final EditText editText28 = editText;
        View.OnClickListener r0 = null;
        View.OnClickListener r22 = r0;
        ImageView imageView = cV_AddSafePasswordDetailActivity.cv_ic_done;
        final EditText editText29 = editText23;
        final EditText editText30 = editText21;
        final EditText editText31 = editText26;
         r0 = new View.OnClickListener() {
            final /* synthetic */ CV_AddSafePasswordDetailActivity this$0;

            {
                CV_AddSafePasswordDetailActivity r3 = null;
                this.this$0 = r3;
            }

            public void onClick(View view) {
                this.this$0.cv_pause_opearion = true;
                if (editText4.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Bank Name", Toast.LENGTH_SHORT).show();
                } else if (editText5.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Account Number", Toast.LENGTH_SHORT).show();
                } else if (editText6.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Account Holder", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Account Type", Toast.LENGTH_SHORT).show();
                } else if (editText8.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Pin", Toast.LENGTH_SHORT).show();
                } else if (editText9.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
                } else if (editText10.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Mother Name", Toast.LENGTH_SHORT).show();
                } else if (editText11.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Surname", Toast.LENGTH_SHORT).show();
                } else if (editText12.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Sort Code", Toast.LENGTH_SHORT).show();
                } else if (editText13.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Swift Code", Toast.LENGTH_SHORT).show();
                } else if (editText14.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Route Code", Toast.LENGTH_SHORT).show();
                } else if (editText15.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Pin 2 Code", Toast.LENGTH_SHORT).show();
                } else if (editText29.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter URL", Toast.LENGTH_SHORT).show();
                } else if (editText8.getText().toString().trim().length() != 6) {
                    editText8.setError("Please Enter Valid Pin Number");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Pin Number", Toast.LENGTH_LONG).show();
                } else if (editText15.getText().toString().trim().length() != 6) {
                    editText15.setError("Please Enter Valid Pin Number");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Pin Number", Toast.LENGTH_LONG).show();
                } else if (editText5.getText().toString().trim().length() != 14) {
                    editText5.setError("Please Enter Valid Account Number");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Account Number", Toast.LENGTH_LONG).show();
                } else if (!this.this$0.isValidEmail(editText9.getText().toString().trim())) {
                    editText9.setError("Please Enter Valid Email ID");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Email ID", Toast.LENGTH_LONG).show();
                } else if (editText12.getText().toString().trim().length() != 6) {
                    editText12.setError("Please Enter Valid Sort Code");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Sort Code", Toast.LENGTH_LONG).show();
                } else if (editText13.getText().toString().trim().length() != 8) {
                    editText13.setError("Please Enter Valid Swift Code");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Swift Code", Toast.LENGTH_LONG).show();
                } else if (editText14.getText().toString().trim().length() != 9) {
                    editText14.setError("Please Enter Valid Route Code");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Route Code", Toast.LENGTH_LONG).show();
                } else if (!this.this$0.IsValidUrl(editText29.getText().toString().trim())) {
                    editText29.setError("Please Enter Valid URL");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid URL", Toast.LENGTH_LONG).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(this.this$0.cv_mContext, editText28);
                    this.this$0.cv_myDatabaseHelper.insertBankData(String.valueOf(System.currentTimeMillis()), editText4.getText().toString(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim(), editText10.getText().toString().trim(), editText11.getText().toString().trim(), editText12.getText().toString().trim(), editText13.getText().toString().trim(), editText14.getText().toString().trim(), editText15.getText().toString().trim(), editText29.getText().toString().trim(), editText30.getText().toString().trim(), editText25.getText().toString().trim(), editText27.getText().toString().trim(), editText31.getText().toString().trim(), editText28.getText().toString().trim());
                    Toast.makeText(this.this$0.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    this.this$0.finish();
                }
            }
        };
        imageView.setOnClickListener(r22);
    }

    private void cv_forCreditCard() {
        EditText editText;
        EditText editText2;
        EditText editText3;
        CV_AddSafePasswordDetailActivity cV_AddSafePasswordDetailActivity;
        final EditText editText4 = (EditText) findViewById(R.id.edt_cardname);
        final EditText editText5 = (EditText) findViewById(R.id.edt_accnumber);
        final EditText editText6 = (EditText) findViewById(R.id.edt_expdate);
        final EditText editText7 = (EditText) findViewById(R.id.edt_cvc);
        final EditText editText8 = (EditText) findViewById(R.id.edt_issuedate);
        final EditText editText9 = (EditText) findViewById(R.id.edt_pin);
        final EditText editText10 = (EditText) findViewById(R.id.edt_startdate);
        final EditText editText11 = (EditText) findViewById(R.id.edt_phnumber);
        final EditText editText12 = (EditText) findViewById(R.id.edt_url);
        final EditText editText13 = (EditText) findViewById(R.id.edt_username);
        final EditText editText14 = (EditText) findViewById(R.id.edt_password);
        final EditText editText15 = (EditText) findViewById(R.id.edt_custom1);
        EditText editText16 = (EditText) findViewById(R.id.edt_custom2);
        EditText editText17 = (EditText) findViewById(R.id.edt_custom3);
        EditText editText18 = (EditText) findViewById(R.id.edt_custom4);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setSize(editText10, 980, 100);
        CV_HelperResizer.setSize(editText11, 980, 100);
        CV_HelperResizer.setSize(editText12, 980, 100);
        CV_HelperResizer.setSize(editText13, 980, 100);
        CV_HelperResizer.setSize(editText14, 980, 100);
        CV_HelperResizer.setSize(editText15, 980, 100);
        CV_HelperResizer.setSize(editText16, 980, 100);
        CV_HelperResizer.setSize(editText17, 980, 100);
        EditText editText19 = editText17;
        CV_HelperResizer.setSize(editText18, 980, 100);
        EditText editText20 = (EditText) findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText20, 980, 100);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText10, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText11, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText12, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText13, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText14, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText15, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText16, 0, 15, 0, 0);
        EditText editText21 = editText16;
        CV_HelperResizer.setMargin(editText19, 0, 15, 0, 0);
        EditText editText22 = editText18;
        CV_HelperResizer.setMargin(editText22, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText20, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            editText = editText20;
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCard_name());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCard_number());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getExp_date());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCvc());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getIssue_date());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getPin());
            editText10.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getStart_date());
            editText11.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getPh_number());
            editText12.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getUrl());
            editText13.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getUsername());
            editText14.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getPassword());
            editText15.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCustom1());
            editText21.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCustom2());
            editText3 = editText19;
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCustom3());
            editText22.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCustom4());
            editText.setText(CV_AddSafePasswordToHideActivity.cv_creditCardModels.get(intExtra).getCustom5());
            this.cv_ic_done.setVisibility(View.GONE);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
            editText7.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
            editText10.setEnabled(false);
            editText11.setEnabled(false);
            editText12.setEnabled(false);
            editText13.setEnabled(false);
            editText14.setEnabled(false);
            editText15.setEnabled(false);
            editText21.setEnabled(false);
            editText3.setEnabled(false);
            editText22.setEnabled(false);
            editText.setEnabled(false);
            editText2 = editText22;
            cV_AddSafePasswordDetailActivity = this;
        } else {
            editText = editText20;
            editText2 = editText22;
            editText3 = editText19;
            cV_AddSafePasswordDetailActivity = this;
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.VISIBLE);
        }
        EditText editText23 = editText2;
        View.OnClickListener r0 = null;
        View.OnClickListener r20 = r0;
        EditText editText24 = editText21;
        EditText editText25 = editText3;
        final EditText editText26 = editText24;
        ImageView imageView = cV_AddSafePasswordDetailActivity.cv_ic_done;
        final EditText editText27 = editText;
        final EditText editText28 = editText25;
        final EditText editText29 = editText23;
         r0 = new View.OnClickListener() {
            final /* synthetic */ CV_AddSafePasswordDetailActivity this$0;

            {
                CV_AddSafePasswordDetailActivity r3 = null;
                this.this$0 = r3;
            }

            public void onClick(View view) {
                this.this$0.cv_pause_opearion = true;
                if (editText4.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Card Name", Toast.LENGTH_SHORT).show();
                } else if (editText5.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Card Number", Toast.LENGTH_SHORT).show();
                } else if (editText6.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Expiry Date", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter CVC Code", Toast.LENGTH_SHORT).show();
                } else if (editText8.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Issue Date", Toast.LENGTH_SHORT).show();
                } else if (editText9.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter PIN", Toast.LENGTH_SHORT).show();
                } else if (editText10.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Start Date", Toast.LENGTH_SHORT).show();
                } else if (editText11.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else if (editText12.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter URL", Toast.LENGTH_SHORT).show();
                } else if (editText13.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Username", Toast.LENGTH_SHORT).show();
                } else if (editText14.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (editText5.getText().toString().trim().length() != 16) {
                    editText5.setError("Please Enter Valid Card Number");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Card Number", Toast.LENGTH_LONG).show();
                } else if (editText6.getText().toString().trim().length() != 8) {
                    editText6.setError("Please Enter Valid Expiry Date");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Expiry Date", Toast.LENGTH_LONG).show();
                } else if (editText7.getText().toString().trim().length() != 3) {
                    editText7.setError("Please Enter Valid CVC");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid CVC", Toast.LENGTH_LONG).show();
                } else if (editText8.getText().toString().trim().length() != 8) {
                    editText8.setError("Please Enter Valid Issuing Date");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Issuing Date", Toast.LENGTH_LONG).show();
                } else if (editText9.getText().toString().trim().length() != 6) {
                    editText9.setError("Please Enter Valid PIN");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid PIN", Toast.LENGTH_LONG).show();
                } else if (editText10.getText().toString().trim().length() != 8) {
                    editText10.setError("Please Enter Valid Start Date");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Start Date", Toast.LENGTH_LONG).show();
                } else if (!this.this$0.isValidMobile(editText11.getText().toString().trim())) {
                    editText11.setError("Please Enter Valid Number");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Number", Toast.LENGTH_LONG).show();
                } else if (!this.this$0.IsValidUrl(editText12.getText().toString().trim())) {
                    editText12.setError("Please Enter Valid URL");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid URL", Toast.LENGTH_LONG).show();
                } else if (editText14.getText().toString().trim().length() != 6) {
                    editText14.setError("Please Enter Valid Password");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Password", Toast.LENGTH_LONG).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(this.this$0.cv_mContext, editText27);
                    this.this$0.cv_myDatabaseHelper.insertCreditCardData(String.valueOf(System.currentTimeMillis()), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim(), editText10.getText().toString().trim(), editText11.getText().toString().trim(), editText12.getText().toString().trim(), editText13.getText().toString().trim(), editText14.getText().toString().trim(), editText15.getText().toString().trim(), editText26.getText().toString().trim(), editText28.getText().toString().trim(), editText29.getText().toString().trim(), editText27.getText().toString().trim());
                    Toast.makeText(this.this$0.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    this.this$0.finish();
                }
            }
        };
        imageView.setOnClickListener(r20);
    }

    private void cv_forEmail() {
        EditText editText;
        EditText editText2;
        EditText editText3;
        CV_AddSafePasswordDetailActivity cV_AddSafePasswordDetailActivity;
        final EditText editText4 = (EditText) findViewById(R.id.edt_title);
        final EditText editText5 = (EditText) findViewById(R.id.edt_type);
        final EditText editText6 = (EditText) findViewById(R.id.edt_userid);
        final EditText editText7 = (EditText) findViewById(R.id.edt_password);
        final EditText editText8 = (EditText) findViewById(R.id.edt_url);
        final EditText editText9 = (EditText) findViewById(R.id.edt_server);
        final EditText editText10 = (EditText) findViewById(R.id.edt_port);
        final EditText editText11 = (EditText) findViewById(R.id.edt_ssl);
        final EditText editText12 = (EditText) findViewById(R.id.edt_authmethod);
        final EditText editText13 = (EditText) findViewById(R.id.edt_provider);
        final EditText editText14 = (EditText) findViewById(R.id.edt_note);
        final EditText editText15 = (EditText) findViewById(R.id.edt_custom1);
        EditText editText16 = (EditText) findViewById(R.id.edt_custom2);
        EditText editText17 = (EditText) findViewById(R.id.edt_custom3);
        EditText editText18 = (EditText) findViewById(R.id.edt_custom4);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setSize(editText10, 980, 100);
        CV_HelperResizer.setSize(editText11, 980, 100);
        CV_HelperResizer.setSize(editText12, 980, 100);
        CV_HelperResizer.setSize(editText13, 980, 100);
        CV_HelperResizer.setSize(editText14, 980, 100);
        CV_HelperResizer.setSize(editText15, 980, 100);
        CV_HelperResizer.setSize(editText16, 980, 100);
        CV_HelperResizer.setSize(editText17, 980, 100);
        EditText editText19 = editText17;
        CV_HelperResizer.setSize(editText18, 980, 100);
        EditText editText20 = (EditText) findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText20, 980, 100);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText10, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText11, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText12, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText13, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText14, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText15, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText16, 0, 15, 0, 0);
        EditText editText21 = editText16;
        CV_HelperResizer.setMargin(editText19, 0, 15, 0, 0);
        EditText editText22 = editText18;
        CV_HelperResizer.setMargin(editText22, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText20, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            editText = editText20;
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getTitle());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getType());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getUserID());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getPassword());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getWebsite());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getServer());
            editText10.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getPort());
            editText11.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getSsl());
            editText12.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getAuthmethod());
            editText13.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getProvider());
            editText14.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getNote());
            editText15.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getCustom1());
            editText21.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getCustom2());
            editText3 = editText19;
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getCustom3());
            editText22.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getCustom4());
            editText.setText(CV_AddSafePasswordToHideActivity.cv_emailModels.get(intExtra).getCustom5());
            this.cv_ic_done.setVisibility(View.GONE);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
            editText7.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
            editText10.setEnabled(false);
            editText11.setEnabled(false);
            editText12.setEnabled(false);
            editText13.setEnabled(false);
            editText14.setEnabled(false);
            editText15.setEnabled(false);
            editText21.setEnabled(false);
            editText3.setEnabled(false);
            editText22.setEnabled(false);
            editText.setEnabled(false);
            editText2 = editText22;
            cV_AddSafePasswordDetailActivity = this;
        } else {
            editText = editText20;
            editText2 = editText22;
            editText3 = editText19;
            cV_AddSafePasswordDetailActivity = this;
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.VISIBLE);
        }
        EditText editText23 = editText2;
        View.OnClickListener r0 = null;
        View.OnClickListener r20 = r0;
        EditText editText24 = editText21;
        EditText editText25 = editText3;
        final EditText editText26 = editText24;
        ImageView imageView = cV_AddSafePasswordDetailActivity.cv_ic_done;
        final EditText editText27 = editText;
        final EditText editText28 = editText25;
        final EditText editText29 = editText23;
         r0 = new View.OnClickListener() {
            final /* synthetic */ CV_AddSafePasswordDetailActivity this$0;

            {
                CV_AddSafePasswordDetailActivity r3 = null;
                this.this$0 = r3;
            }

            public void onClick(View view) {
                this.this$0.cv_pause_opearion = true;
                if (editText4.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Title", Toast.LENGTH_SHORT).show();
                } else if (editText5.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Type", Toast.LENGTH_SHORT).show();
                } else if (editText6.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter user ID", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (editText8.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter URL", Toast.LENGTH_SHORT).show();
                } else if (editText9.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Server", Toast.LENGTH_SHORT).show();
                } else if (editText10.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Port", Toast.LENGTH_SHORT).show();
                } else if (editText11.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter SSL", Toast.LENGTH_SHORT).show();
                } else if (editText12.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Authentication Method", Toast.LENGTH_SHORT).show();
                } else if (editText13.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Provider", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().length() != 6) {
                    editText7.setError("Please Enter Valid Password");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Password", Toast.LENGTH_LONG).show();
                } else if (!this.this$0.IsValidUrl(editText8.getText().toString().trim())) {
                    editText8.setError("Please Enter Valid URL");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid URL", Toast.LENGTH_LONG).show();
                } else if (editText10.getText().toString().trim().length() != 4) {
                    editText10.setError("Please Enter Valid Port Number");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Port Number", Toast.LENGTH_LONG).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(this.this$0.cv_mContext, editText27);
                    this.this$0.cv_myDatabaseHelper.insertEmailData(String.valueOf(System.currentTimeMillis()), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim(), editText10.getText().toString().trim(), editText11.getText().toString().trim(), editText12.getText().toString().trim(), editText13.getText().toString().trim(), editText14.getText().toString().trim(), editText15.getText().toString().trim(), editText26.getText().toString().trim(), editText28.getText().toString().trim(), editText29.getText().toString().trim(), editText27.getText().toString().trim());
                    Toast.makeText(this.this$0.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    this.this$0.finish();
                }
            }
        };
        imageView.setOnClickListener(r20);
    }

    private void cv_forIDCard() {
        EditText editText;
        EditText editText2;
        EditText editText3;
        CV_AddSafePasswordDetailActivity cV_AddSafePasswordDetailActivity;
        final EditText editText4 = (EditText) findViewById(R.id.edt_cardname);
        final EditText editText5 = (EditText) findViewById(R.id.edt_country);
        final EditText editText6 = (EditText) findViewById(R.id.edt_email);
        final EditText editText7 = (EditText) findViewById(R.id.edt_number);
        final EditText editText8 = (EditText) findViewById(R.id.edt_firstname);
        final EditText editText9 = (EditText) findViewById(R.id.edt_lastname);
        final EditText editText10 = (EditText) findViewById(R.id.edt_dob);
        final EditText editText11 = (EditText) findViewById(R.id.edt_issuedate);
        final EditText editText12 = (EditText) findViewById(R.id.edt_expdate);
        final EditText editText13 = (EditText) findViewById(R.id.edt_address);
        final EditText editText14 = (EditText) findViewById(R.id.edt_sex);
        final EditText editText15 = (EditText) findViewById(R.id.edt_eyes);
        EditText editText16 = (EditText) findViewById(R.id.edt_hair);
        EditText editText17 = (EditText) findViewById(R.id.edt_height);
        final EditText editText18 = (EditText) findViewById(R.id.edt_weight);
        EditText editText19 = (EditText) findViewById(R.id.edt_custom1);
        EditText editText20 = (EditText) findViewById(R.id.edt_custom2);
        EditText editText21 = (EditText) findViewById(R.id.edt_custom3);
        EditText editText22 = (EditText) findViewById(R.id.edt_custom4);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setSize(editText10, 980, 100);
        CV_HelperResizer.setSize(editText11, 980, 100);
        CV_HelperResizer.setSize(editText12, 980, 100);
        CV_HelperResizer.setSize(editText13, 980, 100);
        CV_HelperResizer.setSize(editText14, 980, 100);
        CV_HelperResizer.setSize(editText15, 980, 100);
        CV_HelperResizer.setSize(editText16, 980, 100);
        CV_HelperResizer.setSize(editText17, 980, 100);
        EditText editText23 = editText17;
        CV_HelperResizer.setSize(editText18, 980, 100);
        CV_HelperResizer.setSize(editText19, 980, 100);
        CV_HelperResizer.setSize(editText20, 980, 100);
        CV_HelperResizer.setSize(editText21, 980, 100);
        CV_HelperResizer.setSize(editText22, 980, 100);
        EditText editText24 = (EditText) findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText24, 980, 100);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText10, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText11, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText12, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText13, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText14, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText15, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText16, 0, 15, 0, 0);
        EditText editText25 = editText16;
        EditText editText26 = editText23;
        CV_HelperResizer.setMargin(editText26, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText26, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText19, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText20, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText21, 0, 15, 0, 0);
        EditText editText27 = editText22;
        CV_HelperResizer.setMargin(editText27, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText24, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            editText = editText24;
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getCard_name());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getCountry());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getEmail());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getIdnumber());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getFirstname());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getLastname());
            editText10.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getDob());
            editText11.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getIssuedate());
            editText12.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getExpdate());
            editText13.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getAddress());
            editText14.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getSex());
            editText15.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getEyes());
            editText25.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getHair());
            editText23.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getHeight());
            editText18.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getWeight());
            editText19.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getCustom1());
            editText20.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getCustom2());
            editText3 = editText21;
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getCustom3());
            editText27.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getCustom4());
            editText.setText(CV_AddSafePasswordToHideActivity.cv_idCardModels.get(intExtra).getCustom5());
            this.cv_ic_done.setVisibility(View.GONE);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
            editText7.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
            editText10.setEnabled(false);
            editText11.setEnabled(false);
            editText12.setEnabled(false);
            editText13.setEnabled(false);
            editText14.setEnabled(false);
            editText15.setEnabled(false);
            editText25.setEnabled(false);
            editText23.setEnabled(false);
            editText18.setEnabled(false);
            editText19.setEnabled(false);
            editText20.setEnabled(false);
            editText3.setEnabled(false);
            editText27.setEnabled(false);
            editText.setEnabled(false);
            editText2 = editText27;
            cV_AddSafePasswordDetailActivity = this;
        } else {
            editText = editText24;
            editText2 = editText27;
            editText3 = editText21;
            cV_AddSafePasswordDetailActivity = this;
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.VISIBLE);
        }
        final EditText editText28 = editText20;
        final EditText editText29 = editText19;
        final EditText editText30 = editText;
        EditText editText31 = editText2;
        final EditText editText32 = editText3;
        ImageView imageView = cV_AddSafePasswordDetailActivity.cv_ic_done;
        final EditText editText33 = editText25;
        View.OnClickListener r0 = null;
        View.OnClickListener r25 = r0;
        final EditText editText34 = editText23;
        final EditText editText35 = editText31;
         r0 = new View.OnClickListener() {
            final /* synthetic */ CV_AddSafePasswordDetailActivity this$0;

            {
                CV_AddSafePasswordDetailActivity r3 = null;
                this.this$0 = r3;
            }

            public void onClick(View view) {
                this.this$0.cv_pause_opearion = true;
                if (editText4.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Card Name", Toast.LENGTH_SHORT).show();
                } else if (editText5.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Country", Toast.LENGTH_SHORT).show();
                } else if (editText6.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter ID Number", Toast.LENGTH_SHORT).show();
                } else if (editText8.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (editText9.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (editText10.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Date Of Birth", Toast.LENGTH_SHORT).show();
                } else if (editText11.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Issuing Date", Toast.LENGTH_SHORT).show();
                } else if (editText12.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Expiry Date", Toast.LENGTH_SHORT).show();
                } else if (editText13.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Address", Toast.LENGTH_SHORT).show();
                } else if (editText14.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Sex", Toast.LENGTH_SHORT).show();
                } else if (editText15.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Eyes Color", Toast.LENGTH_SHORT).show();
                } else if (editText33.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Hair Color", Toast.LENGTH_SHORT).show();
                } else if (editText34.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Height", Toast.LENGTH_SHORT).show();
                } else if (editText18.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Weight", Toast.LENGTH_SHORT).show();
                } else if (!this.this$0.isValidEmail(editText6.getText().toString().trim())) {
                    editText6.setError("Please Enter Valid Email");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().length() != 6) {
                    editText7.setError("Please Enter Valid ID Number");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid ID Number", Toast.LENGTH_SHORT).show();
                } else if (editText10.getText().toString().trim().length() != 8) {
                    editText10.setError("Please Enter Valid Date Of Birth");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Date Of Birth", Toast.LENGTH_SHORT).show();
                } else if (editText11.getText().toString().trim().length() != 8) {
                    editText11.setError("Please Enter Valid Date Of Issue");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Date Of Issue", Toast.LENGTH_SHORT).show();
                } else if (editText12.getText().toString().trim().length() != 8) {
                    editText12.setError("Please Enter Valid Date Of Expiry");
                    Toast.makeText(this.this$0.cv_mContext, "Please Enter Valid Date Of Expiry", Toast.LENGTH_SHORT).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(this.this$0.cv_mContext, editText30);
                    this.this$0.cv_myDatabaseHelper.insertIDCardData(String.valueOf(System.currentTimeMillis()), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim(), editText10.getText().toString().trim(), editText11.getText().toString().trim(), editText12.getText().toString().trim(), editText13.getText().toString().trim(), editText14.getText().toString().trim(), editText15.getText().toString().trim(), editText33.getText().toString().trim(), editText34.getText().toString().trim(), editText18.getText().toString().trim(), editText29.getText().toString().trim(), editText28.getText().toString().trim(), editText32.getText().toString().trim(), editText35.getText().toString().trim(), editText30.getText().toString().trim());
                    Toast.makeText(this.this$0.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    this.this$0.finish();
                }
            }
        };
        imageView.setOnClickListener(r25);
    }

    private void cv_forWebsite() {
        final EditText editText = (EditText) findViewById(R.id.edt_name);
        final EditText editText2 = (EditText) findViewById(R.id.edt_username);
        final EditText editText3 = (EditText) findViewById(R.id.edt_password);
        final EditText editText4 = (EditText) findViewById(R.id.edt_note);
        final EditText editText5 = (EditText) findViewById(R.id.edt_custom1);
        final EditText editText6 = (EditText) findViewById(R.id.edt_custom2);
        final EditText editText7 = (EditText) findViewById(R.id.edt_custom3);
        final EditText editText8 = (EditText) findViewById(R.id.edt_custom4);
        final EditText editText9 = (EditText) findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText, 980, 100);
        CV_HelperResizer.setSize(editText2, 980, 100);
        CV_HelperResizer.setSize(editText3, 980, 100);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setMargin(editText, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText2, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText3, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getName());
            editText2.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getUsername());
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getPassword());
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getNote());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getCustom1());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getCustom2());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getCustom3());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getCustom4());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_websiteModels.get(intExtra).getCustom5());
            this.cv_ic_done.setVisibility(View.GONE);
            editText.setEnabled(false);
            editText2.setEnabled(false);
            editText3.setEnabled(false);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
            editText7.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
        } else {
            this.cv_ic_done.setVisibility(View.VISIBLE);
        }
        this.cv_ic_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordDetailActivity.this.cv_pause_opearion = true;
                if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Website Name", Toast.LENGTH_SHORT).show();
                } else if (editText2.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Username", Toast.LENGTH_SHORT).show();
                } else if (editText3.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (editText3.getText().toString().length() != 6) {
                    editText3.setError("Please Enter Valid Password");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(CV_AddSafePasswordDetailActivity.this.cv_mContext, editText9);
                    CV_AddSafePasswordDetailActivity.this.cv_myDatabaseHelper.insertWebsiteData(String.valueOf(System.currentTimeMillis()), editText.getText().toString().trim(), editText2.getText().toString().trim(), editText3.getText().toString().trim(), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim());
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    CV_AddSafePasswordDetailActivity.this.finish();
                }
            }
        });
    }

    private void forCommerce() {
        CV_AddSafePasswordDetailActivity cV_AddSafePasswordDetailActivity = this;
        final EditText editText = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_cardname);
        final EditText editText2 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_firstname);
        final EditText editText3 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_username);
        final EditText editText4 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_password);
        final EditText editText5 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_email);
        final EditText editText6 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_phnumber);
        final EditText editText7 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_address);
        final EditText editText8 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_city);
        final EditText editText9 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom1);
        final EditText editText10 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom2);
        final EditText editText11 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom3);
        final EditText editText12 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom4);
        final EditText editText13 = (EditText) cV_AddSafePasswordDetailActivity.findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText, 980, 100);
        CV_HelperResizer.setSize(editText2, 980, 100);
        CV_HelperResizer.setSize(editText3, 980, 100);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setSize(editText10, 980, 100);
        CV_HelperResizer.setSize(editText11, 980, 100);
        CV_HelperResizer.setSize(editText12, 980, 100);
        CV_HelperResizer.setSize(editText13, 980, 100);
        CV_HelperResizer.setMargin(editText, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText2, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText3, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText10, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText11, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText12, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText13, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getCard_name());
            editText2.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getFirst_name());
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getUsername());
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getPassword());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getEmailID());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getPh_number());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getAddress());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getCity());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getCustom1());
            editText10.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getCustom2());
            editText11.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getCustom3());
            editText12.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getCustom4());
            editText13.setText(CV_AddSafePasswordToHideActivity.cv_eCommerceModels.get(intExtra).getCustom5());
            cV_AddSafePasswordDetailActivity = this;
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.GONE);
            editText.setEnabled(false);
            editText2.setEnabled(false);
            editText3.setEnabled(false);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText7.setEnabled(false);
            editText6.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
            editText10.setEnabled(false);
            editText11.setEnabled(false);
            editText12.setEnabled(false);
            editText13.setEnabled(false);
        } else {
            cV_AddSafePasswordDetailActivity.cv_ic_done.setVisibility(View.VISIBLE);
        }
        View.OnClickListener r0 = null;
        View.OnClickListener r16 = r0;
        ImageView imageView = cV_AddSafePasswordDetailActivity.cv_ic_done;
         r0 = new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordDetailActivity.this.cv_pause_opearion = true;
                if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Card Name", Toast.LENGTH_SHORT).show();
                } else if (editText2.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (editText3.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Username", Toast.LENGTH_SHORT).show();
                } else if (editText4.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (editText5.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
                } else if (editText6.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else if (editText7.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Address", Toast.LENGTH_SHORT).show();
                } else if (editText8.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter City", Toast.LENGTH_SHORT).show();
                } else if (editText4.getText().toString().length() != 6) {
                    editText4.setError("Please Enter Valid Password");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                } else if (!CV_AddSafePasswordDetailActivity.this.isValidEmail(editText5.getText().toString().trim())) {
                    editText5.setError("Please Enter Valid Email");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (!CV_AddSafePasswordDetailActivity.this.isValidMobile(editText6.getText().toString().trim())) {
                    editText6.setError("Please Enter Valid Phone Number");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(CV_AddSafePasswordDetailActivity.this.cv_mContext, editText13);
                    CV_AddSafePasswordDetailActivity.this.cv_myDatabaseHelper.insertCommerceData(String.valueOf(System.currentTimeMillis()), editText.getText().toString().trim(), editText2.getText().toString().trim(), editText3.getText().toString().trim(), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim(), editText10.getText().toString().trim(), editText11.getText().toString().trim(), editText12.getText().toString().trim(), editText13.getText().toString().trim());
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    CV_AddSafePasswordDetailActivity.this.finish();
                }
            }
        };
        imageView.setOnClickListener(r0);
    }

    private void cv_forSocial() {
        final EditText editText = (EditText) findViewById(R.id.edt_title);
        final EditText editText2 = (EditText) findViewById(R.id.edt_username);
        final EditText editText3 = (EditText) findViewById(R.id.edt_password);
        final EditText editText4 = (EditText) findViewById(R.id.edt_website);
        final EditText editText5 = (EditText) findViewById(R.id.edt_note);
        final EditText editText6 = (EditText) findViewById(R.id.edt_custom1);
        final EditText editText7 = (EditText) findViewById(R.id.edt_custom2);
        final EditText editText8 = (EditText) findViewById(R.id.edt_custom3);
        CV_HelperResizer.setSize(editText, 980, 100);
        CV_HelperResizer.setSize(editText2, 980, 100);
        CV_HelperResizer.setSize(editText3, 980, 100);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setMargin(editText, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText2, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText3, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getName());
            editText2.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getUsername());
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getPassword());
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getWebsite());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getNote());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getCustom1());
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getCustom2());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_socialModels.get(intExtra).getCustom3());
            this.cv_ic_done.setVisibility(View.GONE);
            editText.setEnabled(false);
            editText2.setEnabled(false);
            editText3.setEnabled(false);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
            editText7.setEnabled(false);
            editText8.setEnabled(false);
        } else {
            this.cv_ic_done.setVisibility(View.VISIBLE);
        }
        this.cv_ic_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordDetailActivity.this.cv_pause_opearion = true;
                if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Title", Toast.LENGTH_SHORT).show();
                } else if (editText2.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Username", Toast.LENGTH_SHORT).show();
                } else if (editText3.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (editText4.toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Website Name", Toast.LENGTH_SHORT).show();
                } else if (editText3.getText().toString().length() != 6) {
                    editText3.setError("Please Enter Valid Password");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(CV_AddSafePasswordDetailActivity.this.cv_mContext, editText8);
                    CV_AddSafePasswordDetailActivity.this.cv_myDatabaseHelper.insertSocialData(String.valueOf(System.currentTimeMillis()), editText.getText().toString().trim(), editText2.getText().toString().trim(), editText3.getText().toString().trim(), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim());
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    CV_AddSafePasswordDetailActivity.this.finish();
                }
            }
        });
    }

    private void cv_forBusiness() {
        final EditText editText = (EditText) findViewById(R.id.edt_company);
        final EditText editText2 = (EditText) findViewById(R.id.edt_status);
        final EditText editText3 = (EditText) findViewById(R.id.edt_date);
        final EditText editText4 = (EditText) findViewById(R.id.edt_response);
        final EditText editText5 = (EditText) findViewById(R.id.edt_pro_rating);
        final EditText editText6 = (EditText) findViewById(R.id.edt_rating);
        final EditText editText7 = (EditText) findViewById(R.id.edt_rewards);
        final EditText editText8 = (EditText) findViewById(R.id.edt_custom1);
        final EditText editText9 = (EditText) findViewById(R.id.edt_custom2);
        final EditText editText10 = (EditText) findViewById(R.id.edt_custom3);
        final EditText editText11 = (EditText) findViewById(R.id.edt_custom4);
        final EditText editText12 = (EditText) findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText, 980, 100);
        CV_HelperResizer.setSize(editText2, 980, 100);
        CV_HelperResizer.setSize(editText3, 980, 100);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setSize(editText7, 980, 100);
        CV_HelperResizer.setSize(editText8, 980, 100);
        CV_HelperResizer.setSize(editText9, 980, 100);
        CV_HelperResizer.setSize(editText10, 980, 100);
        CV_HelperResizer.setSize(editText11, 980, 100);
        CV_HelperResizer.setSize(editText12, 980, 100);
        CV_HelperResizer.setMargin(editText7, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText3, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText2, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText8, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText9, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText10, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText11, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText12, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            int intExtra = getIntent().getIntExtra("pos", Toast.LENGTH_SHORT);
            editText7.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getRewards());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getRating());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getPro_rating());
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getResponse());
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getDate());
            editText2.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getStatus());
            editText.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getCompany());
            editText8.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getCustom1());
            editText9.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getCustom2());
            editText10.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getCustom3());
            editText11.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getCustom3());
            editText12.setText(CV_AddSafePasswordToHideActivity.cv_businessModels.get(intExtra).getCustom3());
            this.cv_ic_done.setVisibility(View.GONE);
            editText7.setEnabled(false);
            editText6.setEnabled(false);
            editText5.setEnabled(false);
            editText4.setEnabled(false);
            editText3.setEnabled(false);
            editText2.setEnabled(false);
            editText.setEnabled(false);
            editText8.setEnabled(false);
            editText9.setEnabled(false);
            editText10.setEnabled(false);
            editText11.setEnabled(false);
            editText12.setEnabled(false);
        } else {
            this.cv_ic_done.setVisibility(View.VISIBLE);
        }
        ImageView imageView = this.cv_ic_done;
        View.OnClickListener r0 = null;
        View.OnClickListener r14 = r0;
         r0 = new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordDetailActivity.this.cv_pause_opearion = true;
                if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Company Name", Toast.LENGTH_SHORT).show();
                } else if (editText2.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Business Status", Toast.LENGTH_SHORT).show();
                } else if (editText3.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Established Date", Toast.LENGTH_SHORT).show();
                } else if (editText4.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Media Response", Toast.LENGTH_SHORT).show();
                } else if (editText5.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Product Rating", Toast.LENGTH_SHORT).show();
                } else if (editText6.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Rating", Toast.LENGTH_SHORT).show();
                } else if (editText7.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Rewards", Toast.LENGTH_SHORT).show();
                } else if (editText3.getText().toString().trim().length() != 8) {
                    editText3.setError("Please Enter Valid Date");
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Valid Date", Toast.LENGTH_SHORT).show();
                } else {
                    CV_MyHelper.hideKeyboardFrom(CV_AddSafePasswordDetailActivity.this.cv_mContext, editText10);
                    CV_AddSafePasswordDetailActivity.this.cv_myDatabaseHelper.insertBusinessData(String.valueOf(System.currentTimeMillis()), editText.getText().toString().trim(), editText2.getText().toString().trim(), editText3.getText().toString().trim(), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim(), editText7.getText().toString().trim(), editText8.getText().toString().trim(), editText9.getText().toString().trim(), editText10.getText().toString().trim(), editText11.getText().toString().trim(), editText12.getText().toString().trim());
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    CV_AddSafePasswordDetailActivity.this.finish();
                }
            }
        };
        imageView.setOnClickListener(r14);
    }

    private void cv_forGeneral() {
        final EditText editText = (EditText) findViewById(R.id.edt_title);
        final EditText editText2 = (EditText) findViewById(R.id.edt_custom1);
        final EditText editText3 = (EditText) findViewById(R.id.edt_custom2);
        final EditText editText4 = (EditText) findViewById(R.id.edt_custom3);
        final EditText editText5 = (EditText) findViewById(R.id.edt_custom4);
        final EditText editText6 = (EditText) findViewById(R.id.edt_custom5);
        CV_HelperResizer.setSize(editText, 980, 100);
        CV_HelperResizer.setSize(editText2, 980, 100);
        CV_HelperResizer.setSize(editText3, 980, 100);
        CV_HelperResizer.setSize(editText4, 980, 100);
        CV_HelperResizer.setSize(editText5, 980, 100);
        CV_HelperResizer.setSize(editText6, 980, 100);
        CV_HelperResizer.setMargin(editText, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText2, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText3, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText4, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText5, 0, 15, 0, 0);
        CV_HelperResizer.setMargin(editText6, 0, 15, 0, 0);
        if (CV_AddSafePasswordToHideActivity.cv_safe_add_edit == 1) {
            int intExtra = getIntent().getIntExtra("pos", 0);
            editText.setText(CV_AddSafePasswordToHideActivity.cv_generalModels.get(intExtra).getTitle());
            editText2.setText(CV_AddSafePasswordToHideActivity.cv_generalModels.get(intExtra).getCustom1());
            editText3.setText(CV_AddSafePasswordToHideActivity.cv_generalModels.get(intExtra).getCustom2());
            editText4.setText(CV_AddSafePasswordToHideActivity.cv_generalModels.get(intExtra).getCustom3());
            editText5.setText(CV_AddSafePasswordToHideActivity.cv_generalModels.get(intExtra).getCustom4());
            editText6.setText(CV_AddSafePasswordToHideActivity.cv_generalModels.get(intExtra).getCustom5());
            this.cv_ic_done.setVisibility(View.GONE);
            editText.setEnabled(false);
            editText2.setEnabled(false);
            editText3.setEnabled(false);
            editText4.setEnabled(false);
            editText5.setEnabled(false);
            editText6.setEnabled(false);
        } else {
            this.cv_ic_done.setVisibility(View.VISIBLE);
        }
        this.cv_ic_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordDetailActivity.this.cv_pause_opearion = true;
                if (editText.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Please Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                CV_MyHelper.hideKeyboardFrom(CV_AddSafePasswordDetailActivity.this.cv_mContext, editText4);
                CV_AddSafePasswordDetailActivity.this.cv_myDatabaseHelper.insertGeneralData(String.valueOf(System.currentTimeMillis()), editText.getText().toString().trim(), editText2.getText().toString().trim(), editText3.getText().toString().trim(), editText4.getText().toString().trim(), editText5.getText().toString().trim(), editText6.getText().toString().trim());
                Toast.makeText(CV_AddSafePasswordDetailActivity.this.cv_mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();
                CV_AddSafePasswordDetailActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isValidMobile(String str) {
        return !Pattern.matches("[a-zA-Z]+", str) && str.length() == 10;
    }

    /* access modifiers changed from: private */
    public boolean IsValidUrl(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        boolean matches = Patterns.WEB_URL.matcher(charSequence).matches();
        if (matches) {
            return matches;
        }
        String str = charSequence + "";
        if (!URLUtil.isNetworkUrl(str)) {
            return matches;
        }
        try {
            new URL(str);
            return true;
        } catch (Exception unused) {
            return matches;
        }
    }

    /* access modifiers changed from: private */
    public boolean isValidEmail(String str) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$", 2).matcher(str).matches();
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_pause_opearion = true;
        finish();
    }

    public void onResume() {
        super.onResume();
        this.cv_pause_opearion = false;
    }

    public void onPause() {
        if (!this.cv_pause_opearion.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
