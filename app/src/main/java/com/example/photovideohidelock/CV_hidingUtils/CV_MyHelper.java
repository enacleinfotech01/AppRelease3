package com.example.photovideohidelock.CV_hidingUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.example.photovideohidelock.CV_models.CV_AtmModel;
import com.example.photovideohidelock.CV_models.CV_BankModel;
import com.example.photovideohidelock.CV_models.CV_BusinessModel;
import com.example.photovideohidelock.CV_models.CV_ContactModel;
import com.example.photovideohidelock.CV_models.CV_CreditCardModel;
import com.example.photovideohidelock.CV_models.CV_ECommerceModel;
import com.example.photovideohidelock.CV_models.CV_EmailModel;
import com.example.photovideohidelock.CV_models.CV_GeneralModel;
import com.example.photovideohidelock.CV_models.CV_IDCardModel;
import com.example.photovideohidelock.CV_models.CV_NotesModel;
import com.example.photovideohidelock.CV_models.CV_SocialModel;
import com.example.photovideohidelock.CV_models.CV_WebsiteModel;
import com.example.photovideohidelock.R;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

public class CV_MyHelper {
    public static String cfav = "favourite";
    public static String cimage = "image";
    public static String ckey = "id";
    public static String cname = "name";
    public static String cnumber = "number";
    public static String ctable = "contact";
    public static String table = "data";
    public static String tid = "id";
    public static String tmoved = "moved";
    public static String toriginal = "original";

    public static void show(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static void showBitmap(Context context, Bitmap bitmap) {
        Toast toast = new Toast(context);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);
        toast.setView(imageView);
        toast.show();
    }

    public static void showLog(String str) {
        PrintStream printStream = System.out;
        printStream.println("AAA : " + str);
    }

    public static void showLog(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println(str + " : " + str2);
    }

    public static Bitmap getBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.destroyDrawingCache();
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static void rate(Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName())));
        } catch (Exception unused) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static void share(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", "Have a look at " + context.getString(R.string.app_name) + " app ");
            intent.putExtra("android.intent.extra.TEXT", "Hey Check out this new " + context.getString(R.string.app_name) + " App - " + context.getString(R.string.app_name) + " \nAvailable on Google play store,You can also download it from \"https://play.google.com/store/apps/details?id=" + context.getPackageName() + "\"");
            context.startActivity(Intent.createChooser(intent, "Share via"));
        } catch (Exception unused) {
        }
    }

    public static void shareImage(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + str));
        context.startActivity(Intent.createChooser(intent, "Share Image"));
    }

    public static void shareText(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        context.startActivity(Intent.createChooser(intent, "Share Result"));
    }

    public static int getDisplayHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getDisplayWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static ArrayList<Bitmap> getAssetFolderImage(Context context, String str) {
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        try {
            ArrayList arrayList2 = new ArrayList(Arrays.asList(context.getAssets().list(str)));
            for (int i = 0; i < arrayList2.size(); i++) {
                AssetManager assets = context.getAssets();
                InputStream open = assets.open(str + "/" + ((String) arrayList2.get(i)));
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inDensity = 100;
                arrayList.add(((BitmapDrawable) Drawable.createFromResourceStream((Resources) null, (TypedValue) null, open, (String) null, options)).getBitmap());
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public static Bitmap getBitmapResize(Context context, Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width >= height) {
            int i3 = (height * i) / width;
            if (i3 > i2) {
                i = (i * i2) / i3;
            } else {
                i2 = i3;
            }
        } else {
            int i4 = (width * i2) / height;
            if (i4 > i) {
                i2 = (i2 * i) / i4;
            } else {
                i = i4;
            }
        }
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }

    public static void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    public static String getImagesFolder(Context context) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault", "Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getOthersFolder(Context context) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault", "Others");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getAppFolder(Context context) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), ".Calculator Vault");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getVideoFolder(Context context) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault", "Video");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getAppImagesFolder(Context context) {
        File file = new File(getAppFolder(context), "Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getAppOthersFolder(Context context) {
        File file = new File(getAppFolder(context), "Others");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getAppVideoFolder(Context context) {
        File file = new File(getAppFolder(context), "Video");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static void copyFile(File file, File file2) throws IOException {
        FileChannel channel = new FileInputStream(file).getChannel();
        FileChannel channel2 = new FileOutputStream(file2).getChannel();
        try {
            channel.transferTo(0, channel.size(), channel2);
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
        }
    }

    public static void moveFile(File file, File file2) throws Throwable {
        FileChannel channel = null;
        try {
            FileChannel channel2 = new FileInputStream(file).getChannel();
            channel = new FileOutputStream(file2).getChannel();
            channel2.transferTo(0, channel2.size(), channel);
            file.delete();
            if (channel2 != null) {
                channel2.close();
            }
            if (channel != null) {
                channel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            if (channel != null) {
                channel.close();
            }
            throw th;
        }
    }

    public static void deleteFolder(File file) {
        try {
            if (file.isDirectory()) {
                for (String file2 : file.list()) {
                    File file3 = new File(file, file2);
                    if (file3.isDirectory()) {
                        Log.d("DeleteRecursive", "Recursive Call" + file3.getPath());
                        deleteFolder(file3);
                    } else {
                        Log.d("DeleteRecursive", "Delete File" + file3.getPath());
                        if (!file3.delete()) {
                            Log.d("DeleteRecursive", "DELETE FAIL");
                        }
                    }
                }
            }
            file.delete();
        } catch (Exception unused) {
        }
    }

    public static void insertData(Context context, String str, String str2) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(toriginal, str);
        contentValues.put(tmoved, str2);
        cV_MyDatabaseHelper.cv_database.insert(table, (String) null, contentValues);
        cV_MyDatabaseHelper.close();
    }

    public static void deleteData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        SQLiteDatabase sQLiteDatabase = cV_MyDatabaseHelper.cv_database;
        String str2 = table;
        StringBuilder sb = new StringBuilder();
        sb.append(tmoved);
        sb.append("=?");
        Toast.makeText(context, "sb2" + sb.toString(), Toast.LENGTH_SHORT).show();
        sQLiteDatabase.delete(str2, sb.toString(), new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static String getOriginalPath(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        String str2 = table;
        String[] strArr = {toriginal};
        Cursor query = cV_MyDatabaseHelper.query(true, str2, strArr, tmoved + "=?", new String[]{str}, (String) null, (String) null, (String) null, (String) null);
        String str3 = "";
        if (query != null) {
            if (query.moveToNext()) {
                do {
                    str3 = query.getString(0);
                } while (query.moveToNext());
            }
            query.close();
        }
        cV_MyDatabaseHelper.close();
        return str3;
    }

    public static ArrayList<CV_ContactModel> getContactData(Context context) {
        ArrayList<CV_ContactModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM contact");
            while (data.moveToNext()) {
                arrayList.add(new CV_ContactModel(data.getString(0), data.getString(1), data.getString(2)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_AtmModel> getATMData(Context context) {
        ArrayList<CV_AtmModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM atm");
            while (data.moveToNext()) {
                arrayList.add(new CV_AtmModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9), data.getString(10), data.getString(11), data.getString(12), data.getString(13)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_BankModel> getBANKData(Context context) {
        ArrayList<CV_BankModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM bank");
            while (data.moveToNext()) {
                arrayList.add(new CV_BankModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9), data.getString(10), data.getString(11), data.getString(12), data.getString(13), data.getString(14), data.getString(15), data.getString(16), data.getString(17), data.getString(18)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_CreditCardModel> getCreditCardData(Context context) {
        ArrayList<CV_CreditCardModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM creditcard");
            while (data.moveToNext()) {
                arrayList.add(new CV_CreditCardModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9), data.getString(10), data.getString(11), data.getString(12), data.getString(13), data.getString(14), data.getString(15), data.getString(16)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_EmailModel> getEmailData(Context context) {
        ArrayList<CV_EmailModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM email");
            while (data.moveToNext()) {
                arrayList.add(new CV_EmailModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9), data.getString(10), data.getString(11), data.getString(12), data.getString(13), data.getString(14), data.getString(15), data.getString(16)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_IDCardModel> getIDCardData(Context context) {
        ArrayList<CV_IDCardModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM idcard");
            while (data.moveToNext()) {
                arrayList.add(new CV_IDCardModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9), data.getString(10), data.getString(11), data.getString(12), data.getString(13), data.getString(14), data.getString(15), data.getString(16), data.getString(17), data.getString(18), data.getString(19), data.getString(20)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_WebsiteModel> getWebsiteData(Context context) {
        ArrayList<CV_WebsiteModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM website");
            while (data.moveToNext()) {
                arrayList.add(new CV_WebsiteModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_SocialModel> getSocialData(Context context) {
        ArrayList<CV_SocialModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM social");
            while (data.moveToNext()) {
                arrayList.add(new CV_SocialModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_ECommerceModel> getCommerceData(Context context) {
        ArrayList<CV_ECommerceModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM ecommerce");
            while (data.moveToNext()) {
                arrayList.add(new CV_ECommerceModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9), data.getString(10), data.getString(11), data.getString(12), data.getString(13)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_BusinessModel> getBusinessData(Context context) {
        ArrayList<CV_BusinessModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM business");
            while (data.moveToNext()) {
                arrayList.add(new CV_BusinessModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8), data.getString(9), data.getString(10), data.getString(11), data.getString(12)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_GeneralModel> getGeneralData(Context context) {
        ArrayList<CV_GeneralModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM general");
            while (data.moveToNext()) {
                arrayList.add(new CV_GeneralModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static ArrayList<CV_NotesModel> getNotesData(Context context) {
        ArrayList<CV_NotesModel> arrayList = new ArrayList<>();
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
            Cursor data = cV_MyDatabaseHelper.getData("SELECT * FROM notes");
            while (data.moveToNext()) {
                arrayList.add(new CV_NotesModel(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4)));
            }
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.close();
        return arrayList;
    }

    public static void insertContactData(Context context, CV_ContactModel cV_ContactModel) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ckey, cV_ContactModel.getLookupkey());
        contentValues.put(cname, cV_ContactModel.getName());
        contentValues.put(cnumber, cV_ContactModel.getNumber());
        cV_MyDatabaseHelper.cv_database.insert(ctable, (String) null, contentValues);
        cV_MyDatabaseHelper.close();
    }

    public static void deleteContactData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        SQLiteDatabase sQLiteDatabase = cV_MyDatabaseHelper.cv_database;
        String str2 = ctable;
        sQLiteDatabase.delete(str2, ckey + "=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteNotesData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("notes", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteATMData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("atm", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteBankData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("bank", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteCreditCardData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("creditcard", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteEmailData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("email", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteIDCardData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("idcard", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteWebsiteData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("website", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteECommerceData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("ecommerce", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteSocialData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete(NotificationCompat.CATEGORY_SOCIAL, "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteBusinessData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("business", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static void deleteGeneralData(Context context, String str) {
        CV_MyDatabaseHelper cV_MyDatabaseHelper = new CV_MyDatabaseHelper(context);
        try {
            cV_MyDatabaseHelper.createDatabase();
            cV_MyDatabaseHelper.openDatabase();
        } catch (Exception e) {
            showLog("Database : " + e.toString());
        }
        cV_MyDatabaseHelper.cv_database.delete("general", "id=?", new String[]{str});
        cV_MyDatabaseHelper.close();
    }

    public static boolean isMyServiceRunning(Context context, Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static byte[] getContactPhoto(Context context, String str) {
        byte[] blob;
        Cursor query = context.getContentResolver().query(Uri.withAppendedPath(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, str), "photo"), new String[]{"data15"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        try {
            if (query.moveToFirst() && (blob = query.getBlob(0)) != null) {
                return blob;
            }
            query.close();
            query.close();
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    public static void shareContact(Context context, String str, String str2) {
        Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, str2);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/x-vcard");
        intent.putExtra("android.intent.extra.STREAM", withAppendedPath);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        context.startActivity(intent);
    }

    public static void deleteContact(Context context, String str) {
        context.getContentResolver().delete(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, str), (String) null, (String[]) null);
    }

    @SuppressLint("Range")
    public static boolean deleteContact(Context context, String str, String str2) {
        Cursor query = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return false;
        }
        try {
            if (query.moveToFirst()) {
                while (!query.getString(query.getColumnIndex("display_name")).equalsIgnoreCase(str2)) {
                    query.moveToNext();
                }
                context.getContentResolver().delete(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, query.getString(query.getColumnIndex("lookup"))), (String) null, (String[]) null);
                query.close();
                return true;
            }
        } catch (Exception e) {
            showLog("EEE", e.toString());
        } catch (Throwable th) {
            query.close();
            throw th;
        }
        query.close();
        return false;
    }

    public static void setFavouriteContact(Context context, String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("starred", Integer.valueOf(z ? 1 : 0));
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        contentResolver.update(uri, contentValues, "_id=" + str, (String[]) null);
    }

    public static void sendMessage(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.fromParts("sms", str, (String) null));
        context.startActivity(intent);
    }

    public static void openWhatsapp(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("smsto:" + str));
        intent.setPackage("com.whatsapp");
        intent.putExtra("chat", true);
        context.startActivity(intent);
    }

    public static void openCall(Context context, String str) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel: " + str));
        context.startActivity(intent);
    }

    public static void copyToClipboard(Context context, String str) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager == null) {
            show(context, "Error while text copy");
        } else if (!str.equals("")) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(Constants.ScionAnalytics.PARAM_LABEL, str));
            showLog("Text Copied");
            show(context, "Text Copied");
        } else {
            show(context, "No text to copy");
        }
    }

    public static byte[] getImageBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap getImage(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboardFrom(Context context, View view) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (!DocumentsContract.isDocumentUri(context, uri)) {
//            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
//                if (isGooglePhotosUri(uri)) {
//                    return uri.getLastPathSegment();
//                }
//                return getDataColumn(context, uri, (String) null, (String[]) null);
//            }
             if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            if (!TextUtils.isEmpty(documentId)) {
                try {
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), (String) null, (String[]) null);
                } catch (NumberFormatException e) {
                    Log.i("AAA", e.getMessage());
                    return null;
                }
            }
        } else if (isMediaDocument(uri)) {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
            String str = split2[0];
            if ("image".equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, (String) null);
        if (query == null) {
            return null;
        }
        try {
            if (!query.moveToFirst()) {
                return null;
            }
            String string = query.getString(query.getColumnIndexOrThrow("_data"));
            if (query != null) {
                query.close();
            }
            return string;
        } catch (IllegalArgumentException unused) {
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
