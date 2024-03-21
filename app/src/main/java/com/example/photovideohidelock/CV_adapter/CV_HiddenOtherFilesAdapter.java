package com.example.photovideohidelock.CV_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.photovideohidelock.CV_activities.CV_AddOtherFilesToHideActivity;
import com.example.photovideohidelock.CV_interfaces.CV_OtherFilesDeleteInterface;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class CV_HiddenOtherFilesAdapter extends RecyclerView.Adapter<CV_HiddenOtherFilesAdapter.Holder> {
    ArrayList<String> cv_all_ids;
    ArrayList<String> cv_contactArrayList;
    CV_OtherFilesDeleteInterface cv_deleteInterface;
    ArrayList<String> cv_dp_contactArrayList;
    Context cv_mContext;

    public CV_HiddenOtherFilesAdapter(Context context, ArrayList<String> arrayList, CV_OtherFilesDeleteInterface cV_OtherFilesDeleteInterface) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.cv_dp_contactArrayList = arrayList2;
        this.cv_mContext = context;
        this.cv_deleteInterface = cV_OtherFilesDeleteInterface;
        this.cv_contactArrayList = arrayList;
        arrayList2.addAll(arrayList);
        ArrayList<String> arrayList3 = new ArrayList<>();
        this.cv_all_ids = arrayList3;
        arrayList3.addAll(CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden);
    }

    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = null;
        return new Holder(LayoutInflater.from(this.cv_mContext).inflate(R.layout.cv_hidden_files_adapter_items, (ViewGroup) null));
    }

    public void onBindViewHolder(Holder holder, @SuppressLint("RecyclerView") final int i) {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(holder.cv_lay_contact, 980, 150, true);
        CV_HelperResizer.setSize(holder.cv_ic_delete, 60, 60, true);
        CV_HelperResizer.setSize(holder.cv_ic_note, 80, 80, true);
        CV_HelperResizer.setMargin(holder.cv_lay_contact, 50, 0, 50, 0);
        holder.cv_tv_name.setText(new File(this.cv_contactArrayList.get(i)).getName());
        if (this.cv_contactArrayList.size() <= 0 || CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.size() <= 0 || this.cv_all_ids.size() <= 0) {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_info_bg));
        } else if (this.cv_all_ids.contains(this.cv_contactArrayList.get(i))) {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_select_all_frame));
        } else {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_info_bg));
        }
        holder.cv_ic_delete.setVisibility(View.VISIBLE);
        holder.cv_ic_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_HiddenOtherFilesAdapter.this.cv_deleteInterface != null) {
                    CV_HiddenOtherFilesAdapter.this.cv_deleteInterface.onDeleteClick(i);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.size() == 0) {
                    CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.add(CV_HiddenOtherFilesAdapter.this.cv_contactArrayList.get(i));
                    CV_HiddenOtherFilesAdapter.this.cv_all_ids.add(CV_HiddenOtherFilesAdapter.this.cv_contactArrayList.get(i));
                    CV_HiddenOtherFilesAdapter.this.notifyDataSetChanged();
                    return;
                }
                if (CV_HiddenOtherFilesAdapter.this.cv_all_ids.size() <= 0 || !CV_HiddenOtherFilesAdapter.this.cv_all_ids.contains(CV_HiddenOtherFilesAdapter.this.cv_contactArrayList.get(i))) {
                    CV_HiddenOtherFilesAdapter.this.cv_all_ids.add(CV_HiddenOtherFilesAdapter.this.cv_contactArrayList.get(i));
                    CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.add(CV_HiddenOtherFilesAdapter.this.cv_contactArrayList.get(i));
                } else {
                    CV_HiddenOtherFilesAdapter.this.cv_all_ids.remove(CV_HiddenOtherFilesAdapter.this.cv_contactArrayList.get(i));
                    CV_AddOtherFilesToHideActivity.cv_duplicatefileshidden.remove(CV_HiddenOtherFilesAdapter.this.cv_contactArrayList.get(i));
                }
                CV_HiddenOtherFilesAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void cv_filter(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        this.cv_contactArrayList.clear();
        if (lowerCase.length() == 0) {
            this.cv_contactArrayList.addAll(this.cv_dp_contactArrayList);
        } else {
            Iterator<String> it = this.cv_dp_contactArrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next.toLowerCase(Locale.getDefault()).contains(lowerCase)) {
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
        ImageView cv_ic_delete;
        ImageView cv_ic_note;
        LinearLayout cv_lay_contact;
        TextView cv_tv_name;

        public Holder(View view) {
            super(view);
            this.cv_lay_contact = (LinearLayout) view.findViewById(R.id.lay_contact);
            this.cv_tv_name = (TextView) view.findViewById(R.id.tv_name);
            this.cv_ic_delete = (ImageView) view.findViewById(R.id.ic_delete);
            this.cv_ic_note = (ImageView) view.findViewById(R.id.ic_note);
        }
    }
}
