package com.example.photovideohidelock.CV_adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_activities.CV_AddContactToHideActivity;
import com.example.photovideohidelock.CV_activities.CV_AddEditContactActivity;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_models.CV_ContactModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class CV_HiddenContactAdapter extends RecyclerView.Adapter<CV_HiddenContactAdapter.Holder> {
    ArrayList<String> cv_all_ids = new ArrayList<>();
    ArrayList<CV_ContactModel> cv_contactArrayList;
    ArrayList<CV_ContactModel> cv_dp_contactArrayList;
    Activity cv_mContext;

    public CV_HiddenContactAdapter(Activity activity, ArrayList<CV_ContactModel> arrayList) {
        ArrayList<CV_ContactModel> arrayList2 = new ArrayList<>();
        this.cv_dp_contactArrayList = arrayList2;
        this.cv_mContext = activity;
        this.cv_contactArrayList = arrayList;
        arrayList2.addAll(arrayList);
        for (int i = 0; i < CV_AddContactToHideActivity.cv_models.size(); i++) {
            this.cv_all_ids.add(CV_AddContactToHideActivity.cv_models.get(i).getLookupkey());
        }
    }

    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = null;
        return new Holder(LayoutInflater.from(this.cv_mContext).inflate(R.layout.cv_hidden_contact_adapter_items, (ViewGroup) null));
    }

    public void onBindViewHolder(Holder holder, @SuppressLint("RecyclerView") final int i) {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(holder.cv_lay_contact, 980, 150, true);
        CV_HelperResizer.setSize(holder.cv_ic_call, 100, 100, true);
        CV_HelperResizer.setSize(holder.cv_ic_edit, 60, 60, true);
        CV_HelperResizer.setMargin(holder.cv_lay_contact, 50, 0, 50, 0);
        holder.cv_tv_name.setText(this.cv_contactArrayList.get(i).getName());
        holder.cv_tv_number.setText(this.cv_contactArrayList.get(i).getNumber());
        if (this.cv_contactArrayList.size() <= 0 || CV_AddContactToHideActivity.cv_models.size() <= 0 || this.cv_all_ids.size() <= 0) {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_info_bg));
        } else if (this.cv_all_ids.contains(this.cv_contactArrayList.get(i).getLookupkey())) {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_select_all_frame));
        } else {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_info_bg));
        }
        holder.cv_ic_call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.cv_checkpauseoperationn = true;
                CV_HiddenContactAdapter.this.cv_all_ids.clear();
                CV_AddContactToHideActivity.cv_models.clear();
                CV_MyHelper.openCall(CV_HiddenContactAdapter.this.cv_mContext, CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getNumber());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_AddContactToHideActivity.cv_models.size() == 0) {
                    CV_AddContactToHideActivity.cv_models.add(CV_HiddenContactAdapter.this.cv_contactArrayList.get(i));
                    CV_HiddenContactAdapter.this.cv_all_ids.add(CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getLookupkey());
                    CV_HiddenContactAdapter.this.notifyDataSetChanged();
                    return;
                }
                if (CV_HiddenContactAdapter.this.cv_all_ids.size() <= 0 || !CV_HiddenContactAdapter.this.cv_all_ids.contains(CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getLookupkey())) {
                    CV_HiddenContactAdapter.this.cv_all_ids.add(CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getLookupkey());
                    CV_AddContactToHideActivity.cv_models.add(CV_HiddenContactAdapter.this.cv_contactArrayList.get(i));
                } else {
                    CV_HiddenContactAdapter.this.cv_all_ids.remove(CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getLookupkey());
                    CV_AddContactToHideActivity.cv_models.remove(CV_HiddenContactAdapter.this.cv_contactArrayList.get(i));
                }
                CV_HiddenContactAdapter.this.notifyDataSetChanged();
            }
        });
        holder.cv_ic_edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddContactToHideActivity.cv_checkpauseoperationn = true;
                CV_HiddenContactAdapter.this.cv_all_ids.clear();
                CV_AddContactToHideActivity.cv_models.clear();
                CV_AddContactToHideActivity.cv_add_edit = 1;
                Intent intent = new Intent(CV_HiddenContactAdapter.this.cv_mContext, CV_AddEditContactActivity.class);
//                intent.putExtra(OSOutcomeConstants.OUTCOME_ID, CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getLookupkey());
                intent.putExtra("name", CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getName());
                intent.putExtra("number", CV_HiddenContactAdapter.this.cv_contactArrayList.get(i).getNumber());
                CV_Adshow.showinterstitialAd(CV_HiddenContactAdapter.this.cv_mContext, intent);
            }
        });
    }

    public void cv_filter(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        this.cv_contactArrayList.clear();
        if (lowerCase.length() == 0) {
            this.cv_contactArrayList.addAll(this.cv_dp_contactArrayList);
        } else {
            Iterator<CV_ContactModel> it = this.cv_dp_contactArrayList.iterator();
            while (it.hasNext()) {
                CV_ContactModel next = it.next();
                if (next.getName().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    this.cv_contactArrayList.add(next);
                }
            }
        }
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.cv_contactArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView cv_ic_call;
        ImageView cv_ic_edit;
        LinearLayout cv_lay_contact;
        TextView cv_tv_name;
        TextView cv_tv_number;

        public Holder(View view) {
            super(view);
            this.cv_lay_contact = (LinearLayout) view.findViewById(R.id.lay_contact);
            this.cv_tv_name = (TextView) view.findViewById(R.id.tv_name);
            this.cv_tv_number = (TextView) view.findViewById(R.id.tv_number);
            this.cv_ic_call = (ImageView) view.findViewById(R.id.ic_call);
            this.cv_ic_edit = (ImageView) view.findViewById(R.id.ic_edit);
        }
    }
}
