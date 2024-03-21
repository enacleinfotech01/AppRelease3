package com.example.photovideohidelock.CV_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_adapter.CV_HiddenSafePasswordAdapter;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_interfaces.CV_SafePasswordDeleteInterface;
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

public class CV_AddSafePasswordToHideActivity extends AppCompatActivity implements CV_SafePasswordDeleteInterface {
    public static ArrayList<CV_AtmModel> cv_atmModels;
    public static ArrayList<CV_BankModel> cv_bankModels;
    public static ArrayList<CV_BusinessModel> cv_businessModels;
    public static Boolean cv_check_pause_op = false;
    public static ArrayList<CV_CreditCardModel> cv_creditCardModels;
    public static ArrayList<CV_ECommerceModel> cv_eCommerceModels;
    public static ArrayList<CV_EmailModel> cv_emailModels;
    public static ArrayList<CV_GeneralModel> cv_generalModels;
    public static ArrayList<CV_IDCardModel> cv_idCardModels;
    public static int cv_safe_add_edit;
    public static ArrayList<CV_SocialModel> cv_socialModels;
    public static ArrayList<CV_WebsiteModel> cv_websiteModels;
    private FrameLayout adContainerView;
    CV_HiddenSafePasswordAdapter cv_hiddenSafePasswordAdapter;
    ImageView cv_ic_add;
    ImageView cv_ic_back;
    Activity cv_mContext;
    RecyclerView cv_rcv_list;
    TextView cv_tv_title;
    TextView cv_txt_nodetail;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_add_safe_password_to_hide);
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_rcv_list = (RecyclerView) findViewById(R.id.rcv_list);
        this.cv_txt_nodetail = (TextView) findViewById(R.id.txt_nodetail);
        this.cv_ic_add = (ImageView) findViewById(R.id.ic_add);
        this.cv_tv_title = (TextView) findViewById(R.id.tv_title);
        this.cv_rcv_list.setLayoutManager(new GridLayoutManager(this.cv_mContext, 2));
        this.cv_ic_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordToHideActivity.cv_check_pause_op = true;
                CV_AddSafePasswordToHideActivity.cv_safe_add_edit = 0;
                CV_AddSafePasswordToHideActivity cV_AddSafePasswordToHideActivity = CV_AddSafePasswordToHideActivity.this;
                CV_Adshow.showinterstitialAd(cV_AddSafePasswordToHideActivity, new Intent(cV_AddSafePasswordToHideActivity.cv_mContext, CV_AddSafePasswordDetailActivity.class));
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordToHideActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_add, 150, 150, true);
    }

    public void onResume() {
        super.onResume();
        cv_check_pause_op = false;
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("atm")) {
            this.cv_tv_title.setText("ATM");
            this.cv_txt_nodetail.setText("There is no detail in ATM Vault.");
            ArrayList<CV_AtmModel> aTMData = CV_MyHelper.getATMData(this.cv_mContext);
            cv_atmModels = aTMData;
            if (aTMData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("bank")) {
            this.cv_tv_title.setText("Bank Account");
            this.cv_txt_nodetail.setText("There is no detail in Bank Account.");
            ArrayList<CV_BankModel> bANKData = CV_MyHelper.getBANKData(this.cv_mContext);
            cv_bankModels = bANKData;
            if (bANKData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("creditcard")) {
            this.cv_tv_title.setText("Credit Card");
            this.cv_txt_nodetail.setText("There is no detail in Credit Card.");
            ArrayList<CV_CreditCardModel> creditCardData = CV_MyHelper.getCreditCardData(this.cv_mContext);
            cv_creditCardModels = creditCardData;
            if (creditCardData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("email")) {
            this.cv_tv_title.setText("Email");
            this.cv_txt_nodetail.setText("There is no detail in Email Account.");
            ArrayList<CV_EmailModel> emailData = CV_MyHelper.getEmailData(this.cv_mContext);
            cv_emailModels = emailData;
            if (emailData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("idcard")) {
            this.cv_tv_title.setText("ID Card");
            this.cv_txt_nodetail.setText("There is no detail in ID Card.");
            ArrayList<CV_IDCardModel> iDCardData = CV_MyHelper.getIDCardData(this.cv_mContext);
            cv_idCardModels = iDCardData;
            if (iDCardData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("website")) {
            this.cv_tv_title.setText("Website");
            this.cv_txt_nodetail.setText("There is no detail in Website.");
            ArrayList<CV_WebsiteModel> websiteData = CV_MyHelper.getWebsiteData(this.cv_mContext);
            cv_websiteModels = websiteData;
            if (websiteData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("commerce")) {
            this.cv_tv_title.setText("E-Commerce");
            this.cv_txt_nodetail.setText("There is no detail in E-Commerce.");
            ArrayList<CV_ECommerceModel> commerceData = CV_MyHelper.getCommerceData(this.cv_mContext);
            cv_eCommerceModels = commerceData;
            if (commerceData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase(NotificationCompat.CATEGORY_SOCIAL)) {
            this.cv_tv_title.setText("Social");
            this.cv_txt_nodetail.setText("There is no detail in Social.");
            ArrayList<CV_SocialModel> socialData = CV_MyHelper.getSocialData(this.cv_mContext);
            cv_socialModels = socialData;
            if (socialData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("business")) {
            this.cv_tv_title.setText("Business");
            this.cv_txt_nodetail.setText("There is no detail in Business.");
            ArrayList<CV_BusinessModel> businessData = CV_MyHelper.getBusinessData(this.cv_mContext);
            cv_businessModels = businessData;
            if (businessData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("general")) {
            this.cv_tv_title.setText("General");
            this.cv_txt_nodetail.setText("There is no detail in General Purpose.");
            ArrayList<CV_GeneralModel> generalData = CV_MyHelper.getGeneralData(this.cv_mContext);
            cv_generalModels = generalData;
            if (generalData.size() > 0) {
                this.cv_txt_nodetail.setVisibility(View.GONE);
            }
        }
        CV_HiddenSafePasswordAdapter cV_HiddenSafePasswordAdapter = new CV_HiddenSafePasswordAdapter(this.cv_mContext, this);
        this.cv_hiddenSafePasswordAdapter = cV_HiddenSafePasswordAdapter;
        this.cv_rcv_list.setAdapter(cV_HiddenSafePasswordAdapter);
    }

    public void onDeleteClick(int i) {
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("atm")) {
            CV_MyHelper.deleteATMData(this.cv_mContext, cv_atmModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "ATM CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("bank")) {
            CV_MyHelper.deleteBankData(this.cv_mContext, cv_bankModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "Bank CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("creditcard")) {
            CV_MyHelper.deleteCreditCardData(this.cv_mContext, cv_creditCardModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "Credit Card CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("email")) {
            CV_MyHelper.deleteEmailData(this.cv_mContext, cv_emailModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "Email CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("idcard")) {
            CV_MyHelper.deleteIDCardData(this.cv_mContext, cv_idCardModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "ID Card CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("website")) {
            CV_MyHelper.deleteWebsiteData(this.cv_mContext, cv_websiteModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "Website CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("commerce")) {
            CV_MyHelper.deleteECommerceData(this.cv_mContext, cv_eCommerceModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "E-Commerce CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase(NotificationCompat.CATEGORY_SOCIAL)) {
            CV_MyHelper.deleteSocialData(this.cv_mContext, cv_socialModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "Social CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("business")) {
            CV_MyHelper.deleteBusinessData(this.cv_mContext, cv_businessModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "Business CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("general")) {
            CV_MyHelper.deleteGeneralData(this.cv_mContext, cv_generalModels.get(i).getId());
            Toast.makeText(this.cv_mContext, "General CV_Data Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
        onResume();
    }

    public void onBackPressed() {
        super.onBackPressed();
        cv_check_pause_op = true;
        finish();
    }

    public void onPause() {
        if (!cv_check_pause_op.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
