package com.example.photovideohidelock.CV_adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.photovideohidelock.CV_activities.CV_AppLockActivity;
import com.example.photovideohidelock.CV_models.CV_AppList_model;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_TinyDB;
import com.example.photovideohidelock.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CV_Applist_adapter extends RecyclerView.Adapter<CV_Applist_adapter.ViewHolder> {
    /* access modifiers changed from: private */
    public ArrayList<CV_AppList_model> cv_listStorage;
    Activity cv_mContext;

    public CV_Applist_adapter(Activity activity, ArrayList<CV_AppList_model> arrayList) {
        this.cv_mContext = activity;
        this.cv_listStorage = arrayList;
        Collections.sort(arrayList, new Comparator<CV_AppList_model>() {
            public int compare(CV_AppList_model cV_AppList_model, CV_AppList_model cV_AppList_model2) {
                return cV_AppList_model.getName().compareToIgnoreCase(cV_AppList_model2.getName());
            }
        });
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.cv_mContext).inflate(R.layout.cv_applist_adapter_items, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(viewHolder.cv_lay_apps, 980, 150, true);
        CV_HelperResizer.setSize(viewHolder.cv_ic_app_icon, 100, 100, true);
        CV_HelperResizer.setSize(viewHolder.cv_ic_select, 60, 60, true);
        CV_HelperResizer.setMargin(viewHolder.cv_lay_apps, 50, 0, 50, 0);
        final String pkgName = this.cv_listStorage.get(i).getPkgName();
        if (CV_AppLockActivity.cv_selected_app_list.contains(pkgName)) {
            viewHolder.cv_ic_select.setImageResource(R.drawable.cv_app_select);
        } else {
            viewHolder.cv_ic_select.setImageResource(R.drawable.cv_app_deselect);
        }
        viewHolder.cv_ic_select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_TinyDB cV_TinyDB = new CV_TinyDB(CV_Applist_adapter.this.cv_mContext);
                if (cV_TinyDB.getInt(CV_Applist_adapter.this.cv_mContext.getResources().getString(R.string.pattern_lock_key)) == 0) {
                    String string = cV_TinyDB.getString(CV_Applist_adapter.this.cv_mContext.getResources().getString(R.string.apppassword));
                    if (string == null || string.isEmpty()) {
                        Toast.makeText(CV_Applist_adapter.this.cv_mContext, "Please Set APP Password first", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (CV_AppLockActivity.cv_selected_app_list.contains(pkgName)) {
                        CV_AppLockActivity.cv_selected_app_list.remove(((CV_AppList_model) CV_Applist_adapter.this.cv_listStorage.get(i)).getPkgName());
                    } else {
                        CV_AppLockActivity.cv_selected_app_list.add(((CV_AppList_model) CV_Applist_adapter.this.cv_listStorage.get(i)).getPkgName());
                    }
                    cV_TinyDB.putSelectedAppList(CV_Applist_adapter.this.cv_mContext.getResources().getString(R.string.app_key), CV_AppLockActivity.cv_selected_app_list);
                    CV_Applist_adapter.this.notifyDataSetChanged();
                    return;
                }
                if (CV_AppLockActivity.cv_selected_app_list.contains(pkgName)) {
                    CV_AppLockActivity.cv_selected_app_list.remove(((CV_AppList_model) CV_Applist_adapter.this.cv_listStorage.get(i)).getPkgName());
                } else {
                    CV_AppLockActivity.cv_selected_app_list.add(((CV_AppList_model) CV_Applist_adapter.this.cv_listStorage.get(i)).getPkgName());
                }
                cV_TinyDB.putSelectedAppList(CV_Applist_adapter.this.cv_mContext.getResources().getString(R.string.app_key), CV_AppLockActivity.cv_selected_app_list);
                CV_Applist_adapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.cv_tv_appname.setText(this.cv_listStorage.get(i).getName());
        viewHolder.cv_ic_app_icon.setImageDrawable(this.cv_listStorage.get(i).getIcon());
    }

    public int getItemCount() {
        return this.cv_listStorage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cv_ic_app_icon;
        ImageView cv_ic_select;
        LinearLayout cv_lay_apps;
        TextView cv_tv_appname;

        public ViewHolder(View view) {
            super(view);
            this.cv_lay_apps = (LinearLayout) view.findViewById(R.id.lay_apps);
            this.cv_ic_app_icon = (ImageView) view.findViewById(R.id.ic_app_icon);
            this.cv_tv_appname = (TextView) view.findViewById(R.id.tv_appname);
            this.cv_ic_select = (ImageView) view.findViewById(R.id.ic_select);
        }
    }
}
