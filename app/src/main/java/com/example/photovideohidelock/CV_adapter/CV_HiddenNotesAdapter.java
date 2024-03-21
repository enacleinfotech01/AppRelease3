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
import com.example.photovideohidelock.CV_activities.CV_AddEditNotesActivity;
import com.example.photovideohidelock.CV_activities.CV_AddNotesToHideActivity;
import com.example.photovideohidelock.CV_interfaces.CV_NoteDeleteInterface;
import com.example.photovideohidelock.CV_models.CV_NotesModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class CV_HiddenNotesAdapter extends RecyclerView.Adapter<CV_HiddenNotesAdapter.Holder> {
    ArrayList<String> cv_all_ids = new ArrayList<>();
    ArrayList<CV_NotesModel> cv_contactArrayList;
    CV_NoteDeleteInterface cv_deleteInterface;
    ArrayList<CV_NotesModel> cv_dp_contactArrayList;
    Activity cv_mContext;

    public CV_HiddenNotesAdapter(Activity activity, ArrayList<CV_NotesModel> arrayList, CV_NoteDeleteInterface cV_NoteDeleteInterface) {
        ArrayList<CV_NotesModel> arrayList2 = new ArrayList<>();
        this.cv_dp_contactArrayList = arrayList2;
        this.cv_mContext = activity;
        this.cv_deleteInterface = cV_NoteDeleteInterface;
        this.cv_contactArrayList = arrayList;
        arrayList2.addAll(arrayList);
        for (int i = 0; i < CV_AddNotesToHideActivity.cv_notesModelArrayList.size(); i++) {
            this.cv_all_ids.add(CV_AddNotesToHideActivity.cv_notesModelArrayList.get(i).getId());
        }
    }

    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = null;
        return new Holder(LayoutInflater.from(this.cv_mContext).inflate(R.layout.cv_hidden_notes_adapter_items, (ViewGroup) null));
    }

    public void onBindViewHolder(Holder holder, @SuppressLint("RecyclerView") final int i) {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(holder.cv_lay_contact, 980, 150, true);
        CV_HelperResizer.setSize(holder.cv_ic_delete, 60, 60, true);
        CV_HelperResizer.setSize(holder.cv_ic_edit, 60, 60, true);
        CV_HelperResizer.setSize(holder.cv_ic_note, 80, 80, true);
        CV_HelperResizer.setMargin(holder.cv_lay_contact, 50, 0, 50, 0);
        holder.cv_tv_name.setText(this.cv_contactArrayList.get(i).getTitle());
        holder.cv_tv_date.setText(this.cv_contactArrayList.get(i).getDate());
        holder.cv_tv_time.setText(this.cv_contactArrayList.get(i).getTime());
        if (this.cv_contactArrayList.size() <= 0 || CV_AddNotesToHideActivity.cv_notesModelArrayList.size() <= 0 || this.cv_all_ids.size() <= 0) {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_info_bg));
        } else if (this.cv_all_ids.contains(this.cv_contactArrayList.get(i).getId())) {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_select_all_frame));
        } else {
            holder.cv_lay_contact.setBackground(this.cv_mContext.getResources().getDrawable(R.drawable.cv_info_bg));
        }
        holder.cv_ic_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_HiddenNotesAdapter.this.cv_deleteInterface != null) {
                    CV_AddNotesToHideActivity.cv_checkpauseoperation = true;
                    CV_HiddenNotesAdapter.this.cv_deleteInterface.onDeleteClick(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getId());
                }
            }
        });
        holder.cv_ic_edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddNotesToHideActivity.cv_checkpauseoperation = true;
                CV_HiddenNotesAdapter.this.cv_all_ids.clear();
                CV_AddNotesToHideActivity.cv_notesModelArrayList.clear();
                CV_AddNotesToHideActivity.cv_add_edit_notes = 1;
                Intent intent = new Intent(CV_HiddenNotesAdapter.this.cv_mContext, CV_AddEditNotesActivity.class);
//                intent.putExtra(OSOutcomeConstants.OUTCOME_ID, CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getId());
//                intent.putExtra(OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE, CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getTitle());
                intent.putExtra("notes", CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getNote());
                CV_Adshow.showinterstitialAd(CV_HiddenNotesAdapter.this.cv_mContext, intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_AddNotesToHideActivity.cv_notesModelArrayList.size() == 0) {
                    CV_AddNotesToHideActivity.cv_notesModelArrayList.add(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i));
                    CV_HiddenNotesAdapter.this.cv_all_ids.add(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getId());
                    CV_HiddenNotesAdapter.this.notifyDataSetChanged();
                    return;
                }
                if (CV_HiddenNotesAdapter.this.cv_all_ids.size() <= 0 || !CV_HiddenNotesAdapter.this.cv_all_ids.contains(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getId())) {
                    CV_HiddenNotesAdapter.this.cv_all_ids.add(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getId());
                    CV_AddNotesToHideActivity.cv_notesModelArrayList.add(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i));
                } else {
                    CV_HiddenNotesAdapter.this.cv_all_ids.remove(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i).getId());
                    CV_AddNotesToHideActivity.cv_notesModelArrayList.remove(CV_HiddenNotesAdapter.this.cv_contactArrayList.get(i));
                }
                CV_HiddenNotesAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void cv_filter(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        this.cv_contactArrayList.clear();
        if (lowerCase.length() == 0) {
            this.cv_contactArrayList.addAll(this.cv_dp_contactArrayList);
        } else {
            Iterator<CV_NotesModel> it = this.cv_dp_contactArrayList.iterator();
            while (it.hasNext()) {
                CV_NotesModel next = it.next();
                if (next.getTitle().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
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
        ImageView cv_ic_edit;
        ImageView cv_ic_note;
        LinearLayout cv_lay_contact;
        TextView cv_tv_date;
        TextView cv_tv_name;
        TextView cv_tv_time;

        public Holder(View view) {
            super(view);
            this.cv_lay_contact = (LinearLayout) view.findViewById(R.id.lay_contact);
            this.cv_tv_name = (TextView) view.findViewById(R.id.tv_name);
            this.cv_tv_date = (TextView) view.findViewById(R.id.tv_date);
            this.cv_ic_delete = (ImageView) view.findViewById(R.id.ic_delete);
            this.cv_ic_note = (ImageView) view.findViewById(R.id.ic_note);
            this.cv_tv_time = (TextView) view.findViewById(R.id.tv_time);
            this.cv_ic_edit = (ImageView) view.findViewById(R.id.ic_edit);
        }
    }
}
