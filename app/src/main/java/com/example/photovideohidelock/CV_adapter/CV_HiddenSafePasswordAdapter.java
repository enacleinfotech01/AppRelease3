package com.example.photovideohidelock.CV_adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_activities.CV_AddSafePasswordDetailActivity;
import com.example.photovideohidelock.CV_activities.CV_AddSafePasswordToHideActivity;
import com.example.photovideohidelock.CV_activities.CV_SafePasswordOptionActivity;
import com.example.photovideohidelock.CV_interfaces.CV_SafePasswordDeleteInterface;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

public class CV_HiddenSafePasswordAdapter extends RecyclerView.Adapter<CV_HiddenSafePasswordAdapter.Holder> {
    Activity cv_mContext;
    CV_SafePasswordDeleteInterface cv_passwordDeleteInterface;

    public CV_HiddenSafePasswordAdapter(Activity activity, CV_SafePasswordDeleteInterface cV_SafePasswordDeleteInterface) {
        this.cv_mContext = activity;
        this.cv_passwordDeleteInterface = cV_SafePasswordDeleteInterface;
    }

    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = null;
        return new Holder(LayoutInflater.from(this.cv_mContext).inflate(R.layout.cv_hidden_safe_password_adapter_items, (ViewGroup) null));
    }

    public void onBindViewHolder(Holder holder, @SuppressLint("RecyclerView") final int i) {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(holder.cv_rel_main, 465, 300, true);
        CV_HelperResizer.setSize(holder.cv_ic_delete, 60, 60, true);
        CV_HelperResizer.setMargin(holder.cv_rel_main, 10, 50, 0, 0);
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("atm")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_atmdetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("bank")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_bankdetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("creditcard")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_creditdetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("email")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_emaildetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("idcard")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_iddetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("website")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_webdetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("commerce")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_commdetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase(NotificationCompat.CATEGORY_SOCIAL)) {
                holder.cv_ic_thumb.setImageResource(R.drawable.cv_socialdetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("business")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_businessdetail_state_pressed);
        } else if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("general")) {
            holder.cv_ic_thumb.setImageResource(R.drawable.cv_gendetail_state_pressed);
        }
        TextView textView = holder.cv_tv_count;
        textView.setText("" + (i + 1));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddSafePasswordToHideActivity.cv_check_pause_op = true;
                CV_AddSafePasswordToHideActivity.cv_safe_add_edit = 1;
                Intent intent = new Intent(CV_HiddenSafePasswordAdapter.this.cv_mContext, CV_AddSafePasswordDetailActivity.class);
                intent.putExtra("pos", i);
                CV_Adshow.showinterstitialAd(CV_HiddenSafePasswordAdapter.this.cv_mContext, intent);
            }
        });
        holder.cv_ic_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_HiddenSafePasswordAdapter.this.cv_passwordDeleteInterface != null) {
                    CV_HiddenSafePasswordAdapter.this.cv_passwordDeleteInterface.onDeleteClick(i);
                }
            }
        });
    }

    public int getItemCount() {
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("atm")) {
            return CV_AddSafePasswordToHideActivity.cv_atmModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("bank")) {
            return CV_AddSafePasswordToHideActivity.cv_bankModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("creditcard")) {
            return CV_AddSafePasswordToHideActivity.cv_creditCardModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("email")) {
            return CV_AddSafePasswordToHideActivity.cv_emailModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("idcard")) {
            return CV_AddSafePasswordToHideActivity.cv_idCardModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("website")) {
            return CV_AddSafePasswordToHideActivity.cv_websiteModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("commerce")) {
            return CV_AddSafePasswordToHideActivity.cv_eCommerceModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase(NotificationCompat.CATEGORY_SOCIAL)) {
            return CV_AddSafePasswordToHideActivity.cv_socialModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("business")) {
            return CV_AddSafePasswordToHideActivity.cv_businessModels.size();
        }
        if (CV_SafePasswordOptionActivity.safe_vault_for.equalsIgnoreCase("general")) {
            return CV_AddSafePasswordToHideActivity.cv_generalModels.size();
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView cv_ic_delete;
        ImageView cv_ic_thumb;
        RelativeLayout cv_rel_main;
        TextView cv_tv_count;

        public Holder(View view) {
            super(view);
            this.cv_rel_main = (RelativeLayout) view.findViewById(R.id.rel_main);
            this.cv_ic_thumb = (ImageView) view.findViewById(R.id.ic_thumb);
            this.cv_tv_count = (TextView) view.findViewById(R.id.tv_count);
            this.cv_ic_delete = (ImageView) view.findViewById(R.id.ic_delete);
        }
    }
}
