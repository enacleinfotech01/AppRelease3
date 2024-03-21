package com.example.photovideohidelock.CV_hidingUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CV_MyDatabaseHelper extends SQLiteOpenHelper {
    private static String DBname = "hidden.db";
    String DB_PATH = null;
    SQLiteDatabase cv_database;
    Context cv_mycontext;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CV_MyDatabaseHelper(Context context) {
        super(context, DBname, (SQLiteDatabase.CursorFactory) null, 10);
        SQLiteDatabase.CursorFactory cursorFactory = null;
        this.DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.cv_mycontext = context;
    }

    public void createDatabase() throws IOException {
        if (!cv_checkDatabase().booleanValue()) {
            getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean cv_checkDatabase() {
        File file = new File(this.DB_PATH + DBname);
        try {
            if (file.exists()) {
                CV_MyHelper.showLog("exists");
            } else {
                CV_MyHelper.showLog("Not exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Boolean.valueOf(file.exists());
    }

    private void copyDatabase() throws IOException {
        InputStream open = this.cv_mycontext.getAssets().open(DBname);
        FileOutputStream fileOutputStream = new FileOutputStream(this.DB_PATH + DBname);
        byte[] bArr = new byte[10];
        while (true) {
            int read = open.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                open.close();
                return;
            }
        }
    }

    public void openDatabase() throws SQLException {
        try {
            this.cv_database = SQLiteDatabase.openDatabase(this.DB_PATH + DBname, (SQLiteDatabase.CursorFactory) null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        } catch (Exception e) {
            CV_MyHelper.show(this.cv_mycontext, e.toString());
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        sQLiteDatabase.disableWriteAheadLogging();
    }

    public synchronized void close() {
        SQLiteDatabase sQLiteDatabase = this.cv_database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        super.close();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i > i2) {
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertData(String str, String str2, String str3) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS contact (id TEXT NOT NULL UNIQUE,name TEXT NOT NULL,number TEXT NOT NULL)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO contact VALUES (?, ?, ?)");
        compileStatement.clearBindings();
        compileStatement.bindString(1, str);
        compileStatement.bindString(2, str2);
        compileStatement.bindString(3, str3);
        compileStatement.executeInsert();
    }

    public void updateData(String str, String str2, String str3) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        SQLiteStatement compileStatement = writableDatabase.compileStatement("UPDATE contact SET name=?, number=? WHERE id=?");
        compileStatement.bindString(1, str2);
        compileStatement.bindString(2, str3);
        compileStatement.bindString(3, str);
        compileStatement.execute();
        writableDatabase.close();
    }

    public void updateNoteData(String str, String str2, String str3) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        SQLiteStatement compileStatement = writableDatabase.compileStatement("UPDATE notes SET title=?, note=? WHERE id=?");
        compileStatement.bindString(1, str2);
        compileStatement.bindString(2, str3);
        compileStatement.bindString(3, str);
        compileStatement.execute();
        writableDatabase.close();
    }

    public void insertAtmData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS atm (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,bank_name TEXT,pin TEXT,holder_name TEXT,card_number TEXT,acc_number TEXT,ph_number TEXT,url TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO atm VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        String str15 = str;
        compileStatement.bindString(1, str);
        String str16 = str2;
        compileStatement.bindString(2, str2);
        String str17 = str3;
        compileStatement.bindString(3, str3);
        String str18 = str4;
        compileStatement.bindString(4, str4);
        String str19 = str5;
        compileStatement.bindString(5, str5);
        String str20 = str6;
        compileStatement.bindString(6, str6);
        String str21 = str7;
        compileStatement.bindString(7, str7);
        String str22 = str8;
        compileStatement.bindString(8, str8);
        String str23 = str9;
        compileStatement.bindString(9, str9);
        String str24 = str10;
        compileStatement.bindString(10, str10);
        String str25 = str11;
        compileStatement.bindString(11, str11);
        String str26 = str12;
        compileStatement.bindString(12, str12);
        compileStatement.bindString(13, str13);
        compileStatement.bindString(14, str14);
        compileStatement.executeInsert();
    }

    public void insertBankData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE  IF NOT EXISTS bank (id INTEGER PRIMARY KEY AUTOINCREMENT,bank_name TEXT,acc_number TEXT,acc_holder TEXT,acc_type TEXT,pin TEXT,email TEXT,mother_name TEXT,surname TEXT,sort_code TEXT,swift_code TEXT,routing_number TEXT,pin_two TEXT,url TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO bank VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        String str20 = str;
        compileStatement.bindString(1, str);
        String str21 = str2;
        compileStatement.bindString(2, str2);
        String str22 = str3;
        compileStatement.bindString(3, str3);
        String str23 = str4;
        compileStatement.bindString(4, str4);
        String str24 = str5;
        compileStatement.bindString(5, str5);
        String str25 = str6;
        compileStatement.bindString(6, str6);
        String str26 = str7;
        compileStatement.bindString(7, str7);
        String str27 = str8;
        compileStatement.bindString(8, str8);
        String str28 = str9;
        compileStatement.bindString(9, str9);
        String str29 = str10;
        compileStatement.bindString(10, str10);
        String str30 = str11;
        compileStatement.bindString(11, str11);
        String str31 = str12;
        compileStatement.bindString(12, str12);
        compileStatement.bindString(13, str13);
        compileStatement.bindString(14, str14);
        compileStatement.bindString(15, str15);
        compileStatement.bindString(16, str16);
        compileStatement.bindString(17, str17);
        compileStatement.bindString(18, str18);
        compileStatement.bindString(19, str19);
        compileStatement.executeInsert();
    }

    public void insertCreditCardData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  creditcard (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,card_number TEXT,expiry_date TEXT,cvc TEXT,issue_date TEXT,pin TEXT,start_date TEXT,ph_number TEXT,url TEXT,username TEXT,password TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO creditcard VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        String str18 = str;
        compileStatement.bindString(1, str);
        String str19 = str2;
        compileStatement.bindString(2, str2);
        String str20 = str3;
        compileStatement.bindString(3, str3);
        String str21 = str4;
        compileStatement.bindString(4, str4);
        String str22 = str5;
        compileStatement.bindString(5, str5);
        String str23 = str6;
        compileStatement.bindString(6, str6);
        String str24 = str7;
        compileStatement.bindString(7, str7);
        String str25 = str8;
        compileStatement.bindString(8, str8);
        String str26 = str9;
        compileStatement.bindString(9, str9);
        String str27 = str10;
        compileStatement.bindString(10, str10);
        String str28 = str11;
        compileStatement.bindString(11, str11);
        String str29 = str12;
        compileStatement.bindString(12, str12);
        compileStatement.bindString(13, str13);
        compileStatement.bindString(14, str14);
        compileStatement.bindString(15, str15);
        compileStatement.bindString(16, str16);
        compileStatement.bindString(17, str17);
        compileStatement.executeInsert();
    }

    public void insertEmailData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  email (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,type TEXT,userid TEXT,password TEXT,website TEXT,server TEXT,port TEXT,ssl TEXT,method TEXT,provider TEXT,note TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO email VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        String str18 = str;
        compileStatement.bindString(1, str);
        String str19 = str2;
        compileStatement.bindString(2, str2);
        String str20 = str3;
        compileStatement.bindString(3, str3);
        String str21 = str4;
        compileStatement.bindString(4, str4);
        String str22 = str5;
        compileStatement.bindString(5, str5);
        String str23 = str6;
        compileStatement.bindString(6, str6);
        String str24 = str7;
        compileStatement.bindString(7, str7);
        String str25 = str8;
        compileStatement.bindString(8, str8);
        String str26 = str9;
        compileStatement.bindString(9, str9);
        String str27 = str10;
        compileStatement.bindString(10, str10);
        String str28 = str11;
        compileStatement.bindString(11, str11);
        String str29 = str12;
        compileStatement.bindString(12, str12);
        compileStatement.bindString(13, str13);
        compileStatement.bindString(14, str14);
        compileStatement.bindString(15, str15);
        compileStatement.bindString(16, str16);
        compileStatement.bindString(17, str17);
        compileStatement.executeInsert();
    }

    public void insertIDCardData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS idcard (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,country TEXT,email TEXT,idnumber TEXT,first_name TEXT,last_name TEXT,dob TEXT,dateissue TEXT,expirydate TEXT,address TEXT,sex TEXT,eyecolor TEXT,haircolor TEXT,height TEXT,weight TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO idcard VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        String str22 = str;
        compileStatement.bindString(1, str);
        String str23 = str2;
        compileStatement.bindString(2, str2);
        String str24 = str3;
        compileStatement.bindString(3, str3);
        String str25 = str4;
        compileStatement.bindString(4, str4);
        String str26 = str5;
        compileStatement.bindString(5, str5);
        String str27 = str6;
        compileStatement.bindString(6, str6);
        String str28 = str7;
        compileStatement.bindString(7, str7);
        String str29 = str8;
        compileStatement.bindString(8, str8);
        String str30 = str9;
        compileStatement.bindString(9, str9);
        String str31 = str10;
        compileStatement.bindString(10, str10);
        String str32 = str11;
        compileStatement.bindString(11, str11);
        String str33 = str12;
        compileStatement.bindString(12, str12);
        compileStatement.bindString(13, str13);
        compileStatement.bindString(14, str14);
        compileStatement.bindString(15, str15);
        compileStatement.bindString(16, str16);
        compileStatement.bindString(17, str17);
        compileStatement.bindString(18, str18);
        compileStatement.bindString(19, str19);
        compileStatement.bindString(20, str20);
        compileStatement.bindString(21, str21);
        compileStatement.executeInsert();
    }

    public void insertWebsiteData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS website (id INTEGER PRIMARY KEY AUTOINCREMENT,web_name TEXT,username TEXT,password TEXT,note TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO website VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        compileStatement.bindString(1, str);
        compileStatement.bindString(2, str2);
        compileStatement.bindString(3, str3);
        compileStatement.bindString(4, str4);
        compileStatement.bindString(5, str5);
        compileStatement.bindString(6, str6);
        compileStatement.bindString(7, str7);
        compileStatement.bindString(8, str8);
        compileStatement.bindString(9, str9);
        compileStatement.bindString(10, str10);
        compileStatement.executeInsert();
    }

    public void insertCommerceData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS ecommerce (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,first_name TEXT,username TEXT,password TEXT,email TEXT,ph_number TEXT,address TEXT,city TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO ecommerce VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        String str15 = str;
        compileStatement.bindString(1, str);
        String str16 = str2;
        compileStatement.bindString(2, str2);
        String str17 = str3;
        compileStatement.bindString(3, str3);
        String str18 = str4;
        compileStatement.bindString(4, str4);
        String str19 = str5;
        compileStatement.bindString(5, str5);
        String str20 = str6;
        compileStatement.bindString(6, str6);
        String str21 = str7;
        compileStatement.bindString(7, str7);
        String str22 = str8;
        compileStatement.bindString(8, str8);
        String str23 = str9;
        compileStatement.bindString(9, str9);
        String str24 = str10;
        compileStatement.bindString(10, str10);
        String str25 = str11;
        compileStatement.bindString(11, str11);
        String str26 = str12;
        compileStatement.bindString(12, str12);
        compileStatement.bindString(13, str13);
        compileStatement.bindString(14, str14);
        compileStatement.executeInsert();
    }

    public void insertSocialData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS social (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,username TEXT,password TEXT,url TEXT,note TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO social VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        compileStatement.bindString(1, str);
        compileStatement.bindString(2, str2);
        compileStatement.bindString(3, str3);
        compileStatement.bindString(4, str4);
        compileStatement.bindString(5, str5);
        compileStatement.bindString(6, str6);
        compileStatement.bindString(7, str7);
        compileStatement.bindString(8, str8);
        compileStatement.bindString(9, str9);
        compileStatement.executeInsert();
    }

    public void insertBusinessData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS business (id INTEGER PRIMARY KEY AUTOINCREMENT,company TEXT,status TEXT,date TEXT,response TEXT,p_rating TEXT,rating TEXT,rewards TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO business VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        String str14 = str;
        compileStatement.bindString(1, str);
        String str15 = str2;
        compileStatement.bindString(2, str2);
        String str16 = str3;
        compileStatement.bindString(3, str3);
        String str17 = str4;
        compileStatement.bindString(4, str4);
        String str18 = str5;
        compileStatement.bindString(5, str5);
        String str19 = str6;
        compileStatement.bindString(6, str6);
        String str20 = str7;
        compileStatement.bindString(7, str7);
        String str21 = str8;
        compileStatement.bindString(8, str8);
        String str22 = str9;
        compileStatement.bindString(9, str9);
        String str23 = str10;
        compileStatement.bindString(10, str10);
        String str24 = str11;
        compileStatement.bindString(11, str11);
        String str25 = str12;
        compileStatement.bindString(12, str12);
        compileStatement.bindString(13, str13);
        compileStatement.executeInsert();
    }

    public void insertNotesData(String str, String str2, String str3, String str4, String str5) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE  IF NOT EXISTS notes (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,note TEXT,date TEXT,time TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO notes VALUES (?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        compileStatement.bindString(1, str);
        compileStatement.bindString(2, str2);
        compileStatement.bindString(3, str3);
        compileStatement.bindString(4, str4);
        compileStatement.bindString(5, str5);
        compileStatement.executeInsert();
    }

    public void insertGeneralData(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            createDatabase();
            openDatabase();
        } catch (Exception e) {
            CV_MyHelper.showLog("Database : " + e.toString());
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE  IF NOT EXISTS general (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        SQLiteStatement compileStatement = writableDatabase.compileStatement("INSERT INTO general VALUES (?, ?, ?, ?, ?, ?, ?)");
        compileStatement.clearBindings();
        compileStatement.bindString(1, str);
        compileStatement.bindString(2, str2);
        compileStatement.bindString(3, str3);
        compileStatement.bindString(4, str4);
        compileStatement.bindString(5, str5);
        compileStatement.bindString(6, str6);
        compileStatement.bindString(7, str7);
        compileStatement.executeInsert();
    }

    public Cursor getData(String str) {
        return getReadableDatabase().rawQuery(str, (String[]) null);
    }

    public Cursor query(Boolean bool, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        Log.d("DATA", str + "" + strArr);
        return this.cv_database.query(bool.booleanValue(), str, strArr, str2, strArr2, str3, (String) null, (String) null, (String) null);
    }

    public long getProfilesCount(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        long queryNumEntries = DatabaseUtils.queryNumEntries(readableDatabase, str);
        readableDatabase.close();
        return queryNumEntries;
    }

    public void CreateAllTables() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  data ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, original TEXT NOT NULL, moved TEXT NOT NULL)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  contact (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,number TEXT NOT NULL)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  general (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  notes (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,note TEXT,date TEXT,time TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  business (id INTEGER PRIMARY KEY AUTOINCREMENT,company TEXT,status TEXT,date TEXT,response TEXT,p_rating TEXT,rating TEXT,rewards TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  social (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,username TEXT,password TEXT,url TEXT,note TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  ecommerce (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,first_name TEXT,username TEXT,password TEXT,email TEXT,ph_number TEXT,address TEXT,city TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  website (id INTEGER PRIMARY KEY AUTOINCREMENT,web_name TEXT,username TEXT,password TEXT,note TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  idcard (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,country TEXT,email TEXT,idnumber TEXT,first_name TEXT,last_name TEXT,dob TEXT,dateissue TEXT,expirydate TEXT,address TEXT,sex TEXT,eyecolor TEXT,haircolor TEXT,height TEXT,weight TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  email (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,type TEXT,userid TEXT,password TEXT,website TEXT,server TEXT,port TEXT,ssl TEXT,method TEXT,provider TEXT,note TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  creditcard (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,card_number TEXT,expiry_date TEXT,cvc TEXT,issue_date TEXT,pin TEXT,start_date TEXT,ph_number TEXT,url TEXT,username TEXT,password TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  bank (id INTEGER PRIMARY KEY AUTOINCREMENT,bank_name TEXT,acc_number TEXT,acc_holder TEXT,acc_type TEXT,pin TEXT,email TEXT,mother_name TEXT,surname TEXT,sort_code TEXT,swift_code TEXT,routing_number TEXT,pin_two TEXT,url TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
        writableDatabase.execSQL("CREATE TABLE IF NOT EXISTS  atm (id INTEGER PRIMARY KEY AUTOINCREMENT,card_name TEXT,bank_name TEXT,pin TEXT,holder_name TEXT,card_number TEXT,acc_number TEXT,ph_number TEXT,url TEXT,custom_one TEXT,custom_two TEXT,custom_three TEXT,custom_four TEXT,custom_five TEXT)");
    }
}
