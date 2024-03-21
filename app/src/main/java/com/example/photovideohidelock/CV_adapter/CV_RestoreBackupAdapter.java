package com.example.photovideohidelock.CV_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.photovideohidelock.CV_activities.CV_BackupRestoreActivity;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;
import java.io.File;
import java.util.ArrayList;

public class CV_RestoreBackupAdapter extends BaseAdapter {
    LayoutInflater cv_inflater = null;
    Context cv_mContext;
    ArrayList<String> cv_restore_listStrings;

    public long getItemId(int i) {
        return (long) i;
    }

    public CV_RestoreBackupAdapter(Context context, ArrayList<String> arrayList) {
        this.cv_mContext = context;
        this.cv_restore_listStrings = arrayList;
        this.cv_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return this.cv_restore_listStrings.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            ViewGroup viewGroup2 = null;
            view = this.cv_inflater.inflate(R.layout.cv_restore_backup_adapter_items, (ViewGroup) null);
        }
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        ImageView imageView = (ImageView) view.findViewById(R.id.ic_delete);
        CV_HelperResizer.setSize((LinearLayout) view.findViewById(R.id.lay_restore), 750, 140, true);
        CV_HelperResizer.setSize(imageView, 60, 60, true);
        ((TextView) view.findViewById(R.id.tv_name)).setText(new File(this.cv_restore_listStrings.get(i)).getName());
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                File file = new File(CV_RestoreBackupAdapter.this.cv_restore_listStrings.get(i));
                if (file.exists()) {
                    file.delete();
                    CV_BackupRestoreActivity.cv_dialog.dismiss();
                    Toast.makeText(CV_RestoreBackupAdapter.this.cv_mContext, "Backup Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
