package xm.ppq.papaquan.sqlit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import rx.Subscriber;

/**
 * Created by sunshaobei on 2017/4/19.
 */

public class PpqSqlite extends SQLiteOpenHelper {
    public static final String CITYSQL ="city";
    public static final String SQLALLTABLE = "allcitytable";
    public static final String SQLPROVINCETABLE = "provincecitytable";
    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String FULLNAME = "fullname";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String PCODE = "pcode";
    public static final String PINYIN = "pinyin";

    public PpqSqlite(Context context) {
        super(context, CITYSQL, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE allcitytable(id INTEGER PRIMARY KEY AUTOINCREMENT,code VARCHAR(20),name VARCHAR(20),fullname VARCHAR(20),pinyin VARCHAR(20),pcode VARCHAR(20))";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE provincecitytable(id INTEGER PRIMARY KEY AUTOINCREMENT,code VARCHAR(20),name VARCHAR(20),fullname VARCHAR(20),pinyin VARCHAR(20),pcode VARCHAR(20))";
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
