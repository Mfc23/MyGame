package com.example.mygame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLControl extends SQLiteOpenHelper {

    private static final String TAG = "SQLControl";

    private static final String Table1 = "LoginInfo";
    private static final String userName = "UserName";
    private static final String passWord = "password";

    public SQLControl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
