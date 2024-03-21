package com.example.photovideohidelock.CV_activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_models.CV_AtmModel;
import com.example.photovideohidelock.CV_models.CV_BankModel;
import com.example.photovideohidelock.CV_models.CV_BusinessModel;
import com.example.photovideohidelock.CV_models.CV_CreditCardModel;
import com.example.photovideohidelock.CV_models.CV_ECommerceModel;
import com.example.photovideohidelock.CV_models.CV_EmailModel;
import com.example.photovideohidelock.CV_models.CV_GeneralModel;
import com.example.photovideohidelock.CV_models.CV_IDCardModel;
import com.example.photovideohidelock.CV_models.CV_SocialModel;
import com.example.photovideohidelock.CV_models.CV_WebsiteModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.util.ArrayList;

public class CV_SafePasswordOptionActivity extends AppCompatActivity {
    public static String safe_vault_for;
    public ArrayList<CV_AtmModel> atmModels;
    public ArrayList<CV_BankModel> bankModels;
    public ArrayList<CV_BusinessModel> businessModels;
    Boolean checkpauseoperation = false;
    public ArrayList<CV_CreditCardModel> creditCardModels;
    public ArrayList<CV_ECommerceModel> eCommerceModels;
    public ArrayList<CV_EmailModel> emailModels;
    public ArrayList<CV_GeneralModel> generalModels;
    ImageView ic_atm;
    ImageView ic_back;
    ImageView ic_bankac;
    ImageView ic_business;
    ImageView ic_creditcard;
    ImageView ic_ecommerce;
    ImageView ic_email;
    ImageView ic_general;
    ImageView ic_idcard;
    ImageView ic_social;
    ImageView ic_website;
    public ArrayList<CV_IDCardModel> idCardModels;
    Context mContext;
    public ArrayList<CV_SocialModel> socialModels;
    TextView tv_count;
    TextView tv_count1;
    TextView tv_count2;
    TextView tv_count3;
    TextView tv_count4;
    TextView tv_count5;
    TextView tv_count6;
    TextView tv_count7;
    TextView tv_count8;
    TextView tv_count9;
    public ArrayList<CV_WebsiteModel> websiteModels;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_safe_password_option);
        this.mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        init();
        resize();
    }

    private void init() {
        this.ic_back = (ImageView) findViewById(R.id.ic_back);
        this.ic_atm = (ImageView) findViewById(R.id.ic_atm);
        this.ic_bankac = (ImageView) findViewById(R.id.ic_bankac);
        this.ic_creditcard = (ImageView) findViewById(R.id.ic_creditcard);
        this.ic_email = (ImageView) findViewById(R.id.ic_email);
        this.ic_idcard = (ImageView) findViewById(R.id.ic_idcard);
        this.ic_website = (ImageView) findViewById(R.id.ic_website);
        this.ic_ecommerce = (ImageView) findViewById(R.id.ic_ecommerce);
        this.ic_social = (ImageView) findViewById(R.id.ic_social);
        this.ic_business = (ImageView) findViewById(R.id.ic_business);
        this.ic_general = (ImageView) findViewById(R.id.ic_general);
        this.tv_count = (TextView) findViewById(R.id.tv_count);
        this.tv_count1 = (TextView) findViewById(R.id.tv_count1);
        this.tv_count2 = (TextView) findViewById(R.id.tv_count2);
        this.tv_count3 = (TextView) findViewById(R.id.tv_count3);
        this.tv_count4 = (TextView) findViewById(R.id.tv_count4);
        this.tv_count5 = (TextView) findViewById(R.id.tv_count5);
        this.tv_count6 = (TextView) findViewById(R.id.tv_count6);
        this.tv_count7 = (TextView) findViewById(R.id.tv_count7);
        this.tv_count8 = (TextView) findViewById(R.id.tv_count8);
        this.tv_count9 = (TextView) findViewById(R.id.tv_count9);
        this.ic_atm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "atm";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_bankac.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "bank";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_creditcard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "creditcard";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "email";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_idcard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "idcard";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_website.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "website";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_ecommerce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "commerce";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_social.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = NotificationCompat.CATEGORY_SOCIAL;
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_business.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "business";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_general.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.checkpauseoperation = true;
                CV_SafePasswordOptionActivity.safe_vault_for = "general";
                CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
                CV_Adshow.showinterstitialAd(cV_SafePasswordOptionActivity, new Intent(cV_SafePasswordOptionActivity.mContext, CV_AddSafePasswordToHideActivity.class));
            }
        });
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_SafePasswordOptionActivity.this.onBackPressed();
            }
        });
    }

    private void resize() {
        CV_HelperResizer.getheightandwidth(this.mContext);
        CV_HelperResizer.setSize(this.ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.ic_atm, 465, 300);
        CV_HelperResizer.setSize(this.ic_bankac, 465, 300);
        CV_HelperResizer.setSize(this.ic_creditcard, 465, 300);
        CV_HelperResizer.setSize(this.ic_email, 465, 300);
        CV_HelperResizer.setSize(this.ic_idcard, 465, 300);
        CV_HelperResizer.setSize(this.ic_website, 465, 300);
        CV_HelperResizer.setSize(this.ic_ecommerce, 465, 300);
        CV_HelperResizer.setSize(this.ic_social, 465, 300);
        CV_HelperResizer.setSize(this.ic_business, 465, 300);
        CV_HelperResizer.setSize(this.ic_general, 465, 300);
    }

    private class SetSize extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        private SetSize() {
            this.progressDialog = new ProgressDialog(CV_SafePasswordOptionActivity.this.mContext);
        }

        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setCancelable(false);
            this.progressDialog.setMessage("Please Wait...");
            this.progressDialog.show();
        }

        public Void doInBackground(Void... voidArr) {
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity.atmModels = CV_MyHelper.getATMData(cV_SafePasswordOptionActivity.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity2 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity2.bankModels = CV_MyHelper.getBANKData(cV_SafePasswordOptionActivity2.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity3 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity3.creditCardModels = CV_MyHelper.getCreditCardData(cV_SafePasswordOptionActivity3.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity4 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity4.emailModels = CV_MyHelper.getEmailData(cV_SafePasswordOptionActivity4.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity5 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity5.idCardModels = CV_MyHelper.getIDCardData(cV_SafePasswordOptionActivity5.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity6 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity6.websiteModels = CV_MyHelper.getWebsiteData(cV_SafePasswordOptionActivity6.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity7 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity7.eCommerceModels = CV_MyHelper.getCommerceData(cV_SafePasswordOptionActivity7.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity8 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity8.socialModels = CV_MyHelper.getSocialData(cV_SafePasswordOptionActivity8.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity9 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity9.businessModels = CV_MyHelper.getBusinessData(cV_SafePasswordOptionActivity9.mContext);
            CV_SafePasswordOptionActivity cV_SafePasswordOptionActivity10 = CV_SafePasswordOptionActivity.this;
            cV_SafePasswordOptionActivity10.generalModels = CV_MyHelper.getGeneralData(cV_SafePasswordOptionActivity10.mContext);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            TextView textView = CV_SafePasswordOptionActivity.this.tv_count;
            textView.setText(CV_SafePasswordOptionActivity.this.atmModels.size() + "");
            TextView textView2 = CV_SafePasswordOptionActivity.this.tv_count1;
            textView2.setText(CV_SafePasswordOptionActivity.this.bankModels.size() + "");
            TextView textView3 = CV_SafePasswordOptionActivity.this.tv_count2;
            textView3.setText(CV_SafePasswordOptionActivity.this.creditCardModels.size() + "");
            TextView textView4 = CV_SafePasswordOptionActivity.this.tv_count3;
            textView4.setText(CV_SafePasswordOptionActivity.this.emailModels.size() + "");
            TextView textView5 = CV_SafePasswordOptionActivity.this.tv_count4;
            textView5.setText(CV_SafePasswordOptionActivity.this.idCardModels.size() + "");
            TextView textView6 = CV_SafePasswordOptionActivity.this.tv_count5;
            textView6.setText(CV_SafePasswordOptionActivity.this.websiteModels.size() + "");
            TextView textView7 = CV_SafePasswordOptionActivity.this.tv_count6;
            textView7.setText(CV_SafePasswordOptionActivity.this.eCommerceModels.size() + "");
            TextView textView8 = CV_SafePasswordOptionActivity.this.tv_count7;
            textView8.setText(CV_SafePasswordOptionActivity.this.socialModels.size() + "");
            TextView textView9 = CV_SafePasswordOptionActivity.this.tv_count8;
            textView9.setText(CV_SafePasswordOptionActivity.this.businessModels.size() + "");
            TextView textView10 = CV_SafePasswordOptionActivity.this.tv_count9;
            textView10.setText(CV_SafePasswordOptionActivity.this.generalModels.size() + "");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    SetSize.this.progressDialog.dismiss();
                }
            }, 100);
        }
    }

    public void onResume() {
        super.onResume();
        this.checkpauseoperation = false;
        new SetSize().execute(new Void[0]);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.checkpauseoperation = true;
        finish();
    }

    public void onPause() {
        if (!this.checkpauseoperation.booleanValue()) {
            startActivity(new Intent(this.mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
