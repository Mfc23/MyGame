package com.example.mygame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLControl extends SQLiteOpenHelper {

    private static final String TAG = "SQLControl";

    private static final String Table1 = "LoginInfo";
    private static final String userName = "UserName";
    private static final String passWord = "password";

    public SQLControl(Context context) {
        super(context, Table1, null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createLogin =  "USES " + Table1 + "FOR CREATION OF LOGIN";
        db.execSQL(createLogin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop if login exists for" + Table1);
        onCreate(db);
    }
}
