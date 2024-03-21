package com.example.photovideohidelock.CV_utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class CV_TinyDB {
    public static int inc_exp;
    private String DEFAULT_APP_IMAGEDATA_DIRECTORY;
    private String lastImagePath = "";
    public SharedPreferences preferences;
    boolean z2;
    boolean z3;

    public CV_TinyDB(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Bitmap getImage(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSavedImagePath() {
        return this.lastImagePath;
    }

    public String putImage(String str, String str2, Bitmap bitmap) {
        if (str == null || str2 == null || bitmap == null) {
            return null;
        }
        this.DEFAULT_APP_IMAGEDATA_DIRECTORY = str;
        String str3 = setupFullPath(str2);
        if (!str3.equals("")) {
            this.lastImagePath = str3;
            saveBitmap(str3, bitmap);
        }
        return str3;
    }

    public boolean putImageWithFullPath(String str, Bitmap bitmap) {
        return (str == null || bitmap == null || !saveBitmap(str, bitmap)) ? false : true;
    }

    private String setupFullPath(String str) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), this.DEFAULT_APP_IMAGEDATA_DIRECTORY);
        if (!isExternalStorageReadable() || !isExternalStorageWritable() || file.exists() || file.mkdirs()) {
            return file.getPath() + '/' + str;
        }
        Log.e("ERROR", "Failed to setup folder");
        return "";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0048, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r3.flush();
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r5.z2 = false;
        r5.z3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0057, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0058, code lost:
        r7.printStackTrace();
        r5.z2 = false;
        r5.z3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0060, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r3.flush();
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:23:0x0042, B:29:0x0049] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean saveBitmap(String r6, Bitmap r7) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L_0x0086
            if (r7 != 0) goto L_0x0007
            goto L_0x0086
        L_0x0007:
            java.io.File r1 = new java.io.File
            r1.<init>(r6)
            boolean r6 = r1.exists()
            if (r6 == 0) goto L_0x0019
            boolean r6 = r1.delete()
            if (r6 != 0) goto L_0x0019
            return r0
        L_0x0019:
            boolean r6 = r1.createNewFile()     // Catch:{ IOException -> 0x001e }
            goto L_0x0023
        L_0x001e:
            r6 = move-exception
            r6.printStackTrace()
            r6 = r0
        L_0x0023:
            r2 = 1
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0072 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0072 }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x0048, all -> 0x0041 }
            r4 = 100
            boolean r7 = r7.compress(r1, r4, r3)     // Catch:{ Exception -> 0x0048, all -> 0x0041 }
            r5.z2 = r7     // Catch:{ Exception -> 0x0048, all -> 0x0041 }
            r3.flush()     // Catch:{ IOException -> 0x003c }
            r3.close()     // Catch:{ IOException -> 0x003c }
            r5.z3 = r2     // Catch:{ IOException -> 0x003c }
            goto L_0x007a
        L_0x003c:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ Exception -> 0x0048, all -> 0x0041 }
            goto L_0x007a
        L_0x0041:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ Exception -> 0x0072 }
            goto L_0x007a
        L_0x0048:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x0060 }
            r3.flush()     // Catch:{ IOException -> 0x0057 }
            r3.close()     // Catch:{ IOException -> 0x0057 }
            r5.z2 = r0     // Catch:{ all -> 0x0060 }
            r5.z3 = r0     // Catch:{ all -> 0x0060 }
            goto L_0x007a
        L_0x0057:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x0060 }
            r5.z2 = r0     // Catch:{ all -> 0x0060 }
            r5.z3 = r0     // Catch:{ all -> 0x0060 }
            return r0
        L_0x0060:
            r7 = move-exception
            r3.flush()     // Catch:{ IOException -> 0x0068 }
            r3.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ Exception -> 0x0072 }
        L_0x006c:
            throw r7     // Catch:{ all -> 0x006d }
        L_0x006d:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ Exception -> 0x0072 }
            goto L_0x007a
        L_0x0072:
            r7 = move-exception
            r7.printStackTrace()
            r5.z2 = r0
            r5.z3 = r0
        L_0x007a:
            if (r6 == 0) goto L_0x0086
            boolean r6 = r5.z2
            if (r6 == 0) goto L_0x0086
            boolean r6 = r5.z3
            if (r6 != 0) goto L_0x0085
            goto L_0x0086
        L_0x0085:
            return r2
        L_0x0086:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.photovideohidelock.CV_utils.CV_TinyDB.saveBitmap(java.lang.String, android.graphics.Bitmap):boolean");
    }

    public int getInt(String str) {
        return this.preferences.getInt(str, 0);
    }

    public ArrayList<Integer> getListInt(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(Integer.parseInt((String) it.next())));
        }
        return arrayList2;
    }

    public long getLong(String str) {
        return this.preferences.getLong(str, 0);
    }

    public float getFloat(String str) {
        return this.preferences.getFloat(str, 0.0f);
    }

    public double getDouble(String str) {
        try {
            return Double.parseDouble(getString(str));
        } catch (NumberFormatException unused) {
            return 0.0d;
        }
    }

    public ArrayList<Double> getListDouble(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
        ArrayList<Double> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Double.valueOf(Double.parseDouble((String) it.next())));
        }
        return arrayList2;
    }

    public ArrayList<Long> getListLong(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
        ArrayList<Long> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Long.valueOf(Long.parseLong((String) it.next())));
        }
        return arrayList2;
    }

    public String getString(String str) {
        return this.preferences.getString(str, "");
    }

    public ArrayList<String> getListString(String str) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
    }

    public boolean getBoolean(String str) {
        return this.preferences.getBoolean(str, false);
    }

    public ArrayList<Boolean> getListBoolean(String str) {
        ArrayList<String> listString = getListString(str);
        ArrayList<Boolean> arrayList = new ArrayList<>();
        Iterator<String> it = listString.iterator();
        while (it.hasNext()) {
            if (it.next().equals("true")) {
                arrayList.add(true);
            } else {
                arrayList.add(false);
            }
        }
        return arrayList;
    }

    public void putInt(String str, int i) {
        checkForNullKey(str);
        this.preferences.edit().putInt(str, i).apply();
    }

    public void putListInt(String str, ArrayList<Integer> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (Integer[]) arrayList.toArray(new Integer[arrayList.size()]))).apply();
    }

    public void putLong(String str, long j) {
        checkForNullKey(str);
        this.preferences.edit().putLong(str, j).apply();
    }

    public void putListLong(String str, ArrayList<Long> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (Long[]) arrayList.toArray(new Long[arrayList.size()]))).apply();
    }

    public void putFloat(String str, float f) {
        checkForNullKey(str);
        this.preferences.edit().putFloat(str, f).apply();
    }

    public void putDouble(String str, double d) {
        checkForNullKey(str);
        putString(str, String.valueOf(d));
    }

    public void putListDouble(String str, ArrayList<Double> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (Double[]) arrayList.toArray(new Double[arrayList.size()]))).apply();
    }

    public void putString(String str, String str2) {
        checkForNullKey(str);
        checkForNullValue(str2);
        this.preferences.edit().putString(str, str2).apply();
    }

    public void putListString(String str, ArrayList<String> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (String[]) arrayList.toArray(new String[arrayList.size()]))).apply();
    }

    public void putBoolean(String str, boolean z) {
        checkForNullKey(str);
        this.preferences.edit().putBoolean(str, z).apply();
    }

    public void putListBoolean(String str, ArrayList<Boolean> arrayList) {
        checkForNullKey(str);
        ArrayList arrayList2 = new ArrayList();
        Iterator<Boolean> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().booleanValue()) {
                arrayList2.add("true");
            } else {
                arrayList2.add("false");
            }
        }
        putListString(str, arrayList2);
    }

    public void remove(String str) {
        this.preferences.edit().remove(str).apply();
    }

    public boolean deleteImage(String str) {
        return new File(str).delete();
    }

    public void clear() {
        this.preferences.edit().clear().apply();
    }

    public Map<String, ?> getAll() {
        return this.preferences.getAll();
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.preferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.preferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    private void checkForNullKey(String str) {
        str.getClass();
    }

    private void checkForNullValue(String str) {
        str.getClass();
    }

    public void putSelectedAppList(String str, ArrayList<String> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("‚‗‚", (String[]) arrayList.toArray(new String[arrayList.size()]))).apply();
    }

    public ArrayList<String> getSelectedAppList(String str) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(this.preferences.getString(str, ""), "‚‗‚")));
    }
}
